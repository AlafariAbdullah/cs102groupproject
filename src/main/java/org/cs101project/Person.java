package org.cs101project;

public class Person{
    private String firstName;
    private String surName;
    private String userName;
    private String password;
    private String birthDate;
    public Person(String n, String s, String u, String p, String bd){
        this.password = Cypher.encryptSubstitution(p);
    }
}