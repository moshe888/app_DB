package com.example.my_p_application;

public class Person {
    public String id;
    public String name;
    public String phone;
    public String email;

    public Person( String id,String name, String phone, String email) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
    }

    public Person() {
    }
}
