package org.cs101project;

import org.cs101project.Employee;

public class SupportEmployee extends Employee {

    private String jobDescription;

    public SupportEmployee(String jobDescription, String department, String officeNumber, String firstName, String surname, String username, String password, String dateOfBirth) {
        super(department, officeNumber, firstName, surname, username, password, dateOfBirth);
        this.jobDescription = jobDescription;
    }

}
