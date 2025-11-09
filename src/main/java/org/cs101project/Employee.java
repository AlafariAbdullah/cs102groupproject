package org.cs101project;

public class Employee extends Person {

    private String department;
    private String officeNumber;

    public Employee(String department, String officeNumber, String firstName, String surname, String username, String password, String dateOfBirth) {
        super(firstName, surname, username, password, dateOfBirth);
        this.department = department;
        this.officeNumber = officeNumber;
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
