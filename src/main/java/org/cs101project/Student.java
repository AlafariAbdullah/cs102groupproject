package org.cs101project;

public class Student extends Person {

    private String status;
    private String major;
    private Award[] Awards;

    public Student(String fname,String lname,String username,String password,String birthDate,String status, String major,Award[] Awards){
        super(fname, lname,username,password,birthDate, "Student");
        this.status = status;
        this.major = major;
        this.Awards = Awards;
    }
    @Override
    public String toString(){
        String awards = "Awards: \n";
        for (Award a: this.Awards){
            awards = awards + "\n" + a.toString();
        }
        return super.toString() + "," + this.getStatus() + "," + this.getMajor() + "\n" + awards;
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
        return this.Awards;
    }

    public void setAwards(Award[] Awards) {
        this.Awards = Awards;
    }

}
