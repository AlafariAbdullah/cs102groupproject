package org.cs101project;

public class Person {

    private String firstName;
    private String surName;
    private String userName;
    private String password;
    private String birthDate;
    private String type;

    public Person(String name, String surname, String username, String password, String birthDate, String type) {
       this.setFirstName(name);
        this.setSurName(surname);
        this.setUserName(username);
        this.setPassword(password);
        this.setBirthDate(birthDate);
        this.setType(type);
    }

    // To handle importing existing users without re-encrypting the passowrd
    public Person(String name, String surname, String username, String password, String birthDate, String type, boolean isEncrypted) {
        this.firstName = name;
        this.surName = surname;
        this.userName = username;
        this.password = password;
        this.birthDate = birthDate;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.getType() + "," + this.getFirstName() + "," + this.getSurName() + "," + this.getUserName() + "," + this.getPassword() + "," + this.getBirthDate();
    }
    public String toDisplayString() {
        return "Name: " + getFirstName() + " " + getSurName() +
               "\nUsername: " + getUserName() +
               "\nDate of Birth: " + getBirthDate();
    }
    public String getFirstName() {
        return this.firstName;
    }

    public String getType() {
        return this.type;
    }

    public void setFirstName(String firstName) {
        if (firstName == null)
            throw new IllegalArgumentException("First name cannot be null");
        else{
        firstName = firstName.trim();
        if (firstName.isEmpty())
            throw new IllegalArgumentException("First name cannot be empty");
            this.firstName = firstName;
    }
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        if (surName == null)
            throw new IllegalArgumentException("Surname cannot be null");
        else{
            surName = surName.trim();
            if (surName.isEmpty())
                throw new IllegalArgumentException("Surname cannot be empty");
            this.surName = surName;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName == null)
            throw new IllegalArgumentException("Username cannot be null");
        else{
            userName = userName.trim();
        if (userName.isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");
        else{
            if (!Character.isLetter(userName.charAt(0))) {
                throw new IllegalArgumentException("Username must start with a letter.");
            }
        for(char c: userName.toCharArray()){
            if (!Character.isLetterOrDigit(c)) {
                throw new IllegalArgumentException("Invalid character: " + c);
            }        
        } 
            this.userName = userName;
    }
        }
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

    public void setType(String type) {
        this.type = type;
    }

}
