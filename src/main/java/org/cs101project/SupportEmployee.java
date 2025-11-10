package org.cs101project;

public class SupportEmployee extends Employee {

    private String jobDescription;

    public SupportEmployee(String fname,String lname,String username,String password,String birthDate, String type,String department, String officeNum, String jobDesc){
        super(fname, lname, username, password, birthDate, type, department,officeNum);
        this.jobDescription = jobDesc;
    }
    @Override
    public String toString(){
        return super.toString() + "," + this.getJobDescription();
    }
    public String getJobDescription(){
        return this.jobDescription;
    }
    public void setJobDescription(String jobDesc){
        this.jobDescription = jobDesc;
    }
}
