package org.cs101project;

public class Faculty extends Employee {

    private String rank; // (lecturer, assistant professor, associate professor or professor)
    private String specialisation;

    public Faculty(String fname,String lname,String username,String password,String birthDate,String department, String officeNum, String rank, String specialization){
        super(fname, lname, username, password, birthDate, "Faculty", department,officeNum);
        this.rank = rank;
        this.specialisation = specialization;
    }
    public Faculty(String fname,String lname,String username,String password,String birthDate,String department, String officeNum, String rank, String specialization, boolean isEncrypted){
        super(fname, lname, username, password, birthDate, "Faculty", department,officeNum, true);
        this.rank = rank;
        this.specialisation = specialization;
    }
    @Override
    public String toString(){
        return super.toString() + "," + this.getRank() +","+this.getSpecialisation();
    }
    public String getRank(){
        return this.rank;
    }
    public String getSpecialisation(){
        return this.specialisation;
    }
    public void setRank(String newRank){
        this.rank = newRank;
    }
    public void SetSpecialisation(String newSpecialisation){
        this.specialisation = newSpecialisation;
    }

}
