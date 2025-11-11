package org.cs101project;

import java.util.ArrayList;
public class Student extends Person {
    
    private String status;
    private String major;
    private ArrayList<Award> Awards;

    public Student(String fname,String lname,String username,String password,String birthDate,String status, String major,ArrayList<Award> Awards){
        super(fname, lname,username,password,birthDate, "Student");
        this.status = status;
        this.major = major;
        if (Awards != null)
            this.Awards = Awards;
        else
            this.Awards = new ArrayList<Award>();
    }
    // to handle importing from file without ruining the password that's already encrypted
    public Student(String fname,String lname,String username,String password,String birthDate,String status, String major,ArrayList<Award> Awards, boolean isEncrypted){
        super(fname, lname,username,password,birthDate, "Student", true);
        this.status = status;
        this.major = major;
        if (Awards != null)
            this.Awards = Awards;
        else
            this.Awards = new ArrayList<Award>();
    }

    @Override
    public String toString(){
        String awards = "Awards:";
        for (Award a: this.Awards){
            awards = awards + "\n" + a.toString();
        }
        return super.toString() + "," + this.getStatus() + "," + this.getMajor() + ",\n" + awards;
    }
    // Modify a single Award:
    public void setAward(int index, Award newAward){
        Awards = this.getAwards();
        if (index >= Awards.size())
            throw new IllegalStateException("Index outOfBound");
        this.getAwards().set(index,newAward);
    }
    public void addAward(Award newAward){
        this.Awards.addLast(newAward);
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

    public ArrayList<Award> getAwards() {
        return this.Awards;
    }

    public void setAwards(ArrayList<Award> Awards) {
        this.Awards = Awards;
    }

}
