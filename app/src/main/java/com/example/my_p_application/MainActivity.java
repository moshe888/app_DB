
package com.example.my_p_application;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.util.Log;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public EditText emailT ;
    private EditText passT  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        emailT = findViewById(R.id.emailText);
        passT = findViewById(R.id.passwordText);

    }
    public void login(){

        String email = emailT.getText().toString();
        String password = passT.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,"login ok",Toast.LENGTH_LONG).show();
//                             read();

                        } else {
                            Toast.makeText(MainActivity.this,"login fail",Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }

    public void register(){

        String email = emailT.getText().toString();
        String password = passT.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,"register ok",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this,"register fail",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    public void write(Person p) {

//    Person p1 = new Person("moshe","1234" , "333555444","sa@gmail.com");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(p.id);

        myRef.setValue(p);
    }
    Person per = new Person();

    public Person read(String id) {
         FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(id);
        // Read from the database
         myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                 per = dataSnapshot.getValue(Person.class);
                try {
                   Log.d("result" , per.name);

               } catch (Exception e) {
                   Toast.makeText(MainActivity.this,"person fail",Toast.LENGTH_LONG).show();
                   e.printStackTrace();
               }
            }

            @Override
            public void onCancelled(DatabaseError error) {
              }
        });
        return per;
    }


}