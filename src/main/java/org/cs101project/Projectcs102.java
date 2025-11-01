/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.cs101project;

import java.io.File;
import java.io.PrintWriter;
/**
 *
 * @author Abdullah
 */
import java.util.Scanner;

public class Projectcs102 {

    public static void main(String[] args) {
        // System.out.println("Hi");    
        Scanner userInput = new Scanner(System.in);

        // Main menu
        String[] possibleInput = {"Sing in", "Sign up", "Exit", "1", "2", "3"};
        String choice = null;
        while (choice == null || !includes(possibleInput, choice)) {
            System.out.println("Welcome! \n Type (Sign in) OR (1) to sign into your account \n or type (Sign up) OR (2 ) to regiser \n or type (Exit) (3) to quit the app");
            choice = userInput.nextLine();

        }
        if (choice.equalsIgnoreCase("Sign in") || choice.equalsIgnoreCase("1")) {

        } else if (choice.equalsIgnoreCase("Sign up") || choice.equalsIgnoreCase("2")) {
        System.out.println("Type (1) if you are a student, (2) if you are a faculty"
                + "member, and (3) if you are a support employee");
        System.out.print("Enter your choice: ");
        String type = userInput.nextLine();

        System.out.print("First name: ");
        String fname = userInput.nextLine();
        System.out.print("Last name: ");
        String lname = userInput.nextLine();
        System.out.print("Username: ");
        String username = userInput.nextLine();
        System.out.print("Date of birth: ");
        String Date = userInput.nextLine();
        System.out.print("Password: ");
        String password = userInput.nextLine();

        if (type.equals("1")) {
            System.out.print("Status (Freshman, Sophomore, etc): ");
            String status = userInput.nextLine();
            System.out.print("Major: ");
            String major = userInput.nextLine();
            System.out.println("Student account created for " + fname + " " + lname);
            saveUserToFile(type, fname, lname, username, Date, password,
               status, major, null, null, null, null, null); 
            //Null fields mean this parameter is irrelevant for this person type (student for example)
        
        } else if (type.equals("2")) {
            System.out.print("Department: ");
            String dept = userInput.nextLine();
            System.out.print("Office number: ");
            String office = userInput.nextLine();
            System.out.print("Rank: ");
            String rank = userInput.nextLine();
            System.out.print("Specialization: ");
            String spec = userInput.nextLine();
            System.out.println("Faculty account created for " + fname + " " + lname);
            saveUserToFile(type, fname, lname, username, Date, password,
               null, null, dept, office, rank, spec, null);
        
        } else if (type.equals("3")) {
            System.out.print("Department: ");
            String dept = userInput.nextLine();
            System.out.print("Office number: ");
            String office = userInput.nextLine();
            System.out.print("Job description: ");
            String job = userInput.nextLine();
            System.out.println("Support & Services account created for " + fname + " " + lname);
            saveUserToFile(type, fname, lname, username, Date, password,
               null, null, dept, office, null, null, job);
        
        } else {
            System.out.println("Invalid choice");
        }
            
        System.out.println("(Sign up is complete)");
    }
        
         else if (choice.equalsIgnoreCase("Exit") || choice.equalsIgnoreCase("3")) {
            System.exit(1);
        }
    }

       
    
    public static boolean includes(String[] arr, String str) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
    
    public static void saveUserToFile(
        String type,
        String fname,
        String lname,
        String username,
        String dob,
        String password,
        String status,
        String major,
        String department,
        String office,
        String rank,
        String specialization,
        String job
) {
    try (PrintWriter pw = new PrintWriter(new File("users.txt"))) {

        // Write all data in one line separated by commas, end with semicolon
        pw.print(type + "," +
                fname + "," +
                lname + "," +
                username + "," +
                dob + "," +
                password + "," +
                status + "," +
                major + "," +
                department + "," +
                office + "," +
                rank + "," +
                specialization + "," +
                job + ";");

        pw.println(); // new line for next user

    } catch (Exception e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }
}
}
