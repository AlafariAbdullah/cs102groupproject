/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.cs101project;

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
            choice = userInput.next();

        }
        if (choice.equalsIgnoreCase("Sign in") || choice.equalsIgnoreCase("1")) {

        } else if (choice.equalsIgnoreCase("Sign up") || choice.equalsIgnoreCase("2")) {

        } else if (choice.equalsIgnoreCase("Exit") || choice.equalsIgnoreCase("3")) {
            System.exit(1);
        }


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
        
        } else if (type.equals("3")) {
            System.out.print("Department: ");
            String dept = userInput.nextLine();
            System.out.print("Office number: ");
            String office = userInput.nextLine();
            System.out.print("Job description: ");
            String job = userInput.nextLine();
            System.out.println("Support & Services account created for " + fname + " " + lname);
        
        } else {
            System.out.println("Invalid choice");
        }

        System.out.println("(Sign up is complete)");
    }

    
    public static boolean includes(String[] arr, String str) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
