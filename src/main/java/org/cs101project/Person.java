package org.cs101project;

public class Person {

    private String firstName;
    private String surName;
    private String userName;
    private String password;
    private String birthDate;
    private String type;

    public Person(String name, String surname, String username, String password, String birthDate, String type) {
        this.firstName = name;
        this.surName = surname;
        this.userName = username;
        this.password = Cipher.encryptSubstitution(password);
        this.birthDate = birthDate;
        this.type = type;
    }
    @Override
    public String toString(){
        return this.getFirstName() + "," + this.getSurName() + "," + this.getUserName() + "," + this.getPassword() + "," + this.getBirthDate();
    }
    public String getFirstName() {
        return this.firstName;
    }

    public String getType(){
        return this.type;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Cipher.encryptSubstitution(password);
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public void setType(String type){
        this.type = type;
    }

}
