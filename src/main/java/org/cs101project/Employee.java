package org.cs101project;

public class Employee extends Person {

    private String department;
    private String officeNumber;

    public Employee(String fname,String lname,String username,String password,String birthDate, String type,String department, String officeNum){
        super(fname, lname,username,password,birthDate, type);
        this.department = department;
        this.officeNumber = officeNum;
    }
    @Override
    public String toString(){
        return super.toString() + ","+this.getDepartment()+","+this.getOfficeNumber();
    }
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

}
