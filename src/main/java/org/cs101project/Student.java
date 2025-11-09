package org.cs101project;

public class Student extends Person {

    private String status;
    private String major;
    private Award[] Awards;

    public Student(String status, String major, Award[] Awards, String firstName, String surname, String username, String password, String dateOfBirth) {
        super(firstName, surname, username, password, dateOfBirth);
        this.status = status;
        this.major = major;
        this.Awards = Awards;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Award[] getAwards() {
        return Awards;
    }

    public void setAwards(Award[] Awards) {
        this.Awards = Awards;
    }

}
