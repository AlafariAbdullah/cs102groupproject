package org.cs101project;

import org.cs101project.Employee;

public class Faculty extends Employee {

    private String rank;
    private String specialisation;

    public Faculty(String rank, String specialisation, String department, String officeNumber, String firstName, String surname, String username, String password, String dateOfBirth) {
        super(department, officeNumber, firstName, surname, username, password, dateOfBirth);
        this.rank = rank;
        this.specialisation = specialisation;
    }

}
