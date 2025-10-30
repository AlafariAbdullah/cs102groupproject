/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cs101project;

import org.cs101project.Employee;

/**
 *
 * @author Abdelrahman
 */
public class SupportEmployee extends Employee {
    private String jobDescription;

    public SupportEmployee(String jobDescription, String department, String officeNumber, String firstName, String surname, String username, String password, String dateOfBirth) {
        super(department, officeNumber, firstName, surname, username, password, dateOfBirth);
        this.jobDescription = jobDescription;
    }
    
    
}
