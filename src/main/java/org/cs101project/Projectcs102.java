package org.cs101project;

import java.io.FileWriter;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Projectcs102 {

    public static void main(String[] args) {
        // System.out.println("Hi");    
        Scanner userInput = new Scanner(System.in);

        // Main menu
        String[] possibleInput = {"Sing in", "Sign up", "Exit", "1", "2", "3"};
        String choice = null;
        while (choice == null || !includes(possibleInput, choice)) {
            System.out.println("Welcome! \n Type (1) OR (Sign in) to sign into your account. \n Type (2) OR (Sign up) to regiser. \n Type (3) OR (Exit) to quit the app.");
            System.out.print("Enter your choice: ");
            choice = userInput.nextLine();

        }
        if (choice.equalsIgnoreCase("Sign in") || choice.equalsIgnoreCase("1")) {
            System.out.println("");
            System.out.println("-------(Sign in)-------");
            System.out.println("");
            System.out.print("Enter username: ");
            String loginUsername = userInput.nextLine();
            System.out.print("Enter password: ");
            String loginPassword = userInput.nextLine();
            System.out.println("");
            boolean success = signIn(loginUsername, loginPassword);

            if (success) {
                System.out.println("Access granted!");
            } else {
                System.out.println("Access denied.");
            }
        } else if (choice.equalsIgnoreCase("Sign up") || choice.equalsIgnoreCase("2")) {
            System.out.println("");
            System.out.println("-------(Sign up)-------");

            String type = "";
            do{
                if (!type.equals(""))
                    System.out.println("Invalid Input");
                System.out.println("\n Type (1) If you are a student.\n Type (2) If you are a faculty member.\n Type (3) If you are a support employee.\n Type (4) to exit");
                System.out.print("Enter your choice: ");
                type = userInput.nextLine();
                if (type.equals("4"))
                System.exit(1);
            }
            while (!type.equals("1") && !type.equals("2") && !type.equals("3"));
        

            System.out.println("");
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


            switch (type){
                case "1": {
                    type = "Student";
                    System.out.print("Status (Freshman, Sophomore, etc): ");
                    String status = userInput.nextLine();
                    System.out.print("Major: ");
                    String major = userInput.nextLine();

                    // Sample Data!!! Awards logic should be done
                    Award[] Awards = new Award[5];
                    for (int i = 0; i < Awards.length; i++) {
                        Awards[i] = new Award("CCNA","12/2/2024","CISCO");
                    }
                    System.out.print("Awards:");
                    //  TO DOO AWARDS IS TO DO

                    System.out.println("Student account created for " + fname + " " + lname);
                    saveUserToFile(new Student(fname,lname,username,password, Date, type, status, major, Awards));                    
                    break;
                }    
                case "2": {
                    type = "Faculty";
                    System.out.print("Department: ");
                    String dept = userInput.nextLine();
                    System.out.print("Office number: ");
                    String office = userInput.nextLine();
                    System.out.print("Rank: ");
                    String rank = userInput.nextLine();
                    System.out.print("Specialization: ");
                    String spec = userInput.nextLine();
                    System.out.println("Faculty account created for " + fname + " " + lname);
                    saveUserToFile(new Faculty(fname, lname, username, password, Date, type, dept, office, rank, spec));
                    break;
    
                }
                case "3": {
                    type = "SupportEmployee" ;
                    System.out.print("Department: ");
                    String dept = userInput.nextLine();
                    System.out.print("Office number: ");
                    String office = userInput.nextLine();
                    System.out.print("Job description: ");
                    String job = userInput.nextLine();
                    System.out.println("Support & Services account created for " + fname + " " + lname);
                    saveUserToFile(new SupportEmployee(fname, lname, username, password, Date, type, dept, office, job));
                    break;
                }
            }
            
        }
            else if (choice.equalsIgnoreCase("Exit") || choice.equalsIgnoreCase("3")) {
                System.exit(1);

            System.out.println("(Sign up is complete)");
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






    public static void saveUserToFile(Student student) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("users.txt", true))) {

            pw.println(student.toString() + ";");


        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public static void saveUserToFile(Faculty faculty) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("users.txt", true))) {

            pw.println(faculty.toString() + ";");


        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public static void saveUserToFile(SupportEmployee support) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("users.txt", true))) {

            pw.println(support.toString() + ";");


        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }





    public static boolean signIn(String username, String password) {
        password = Cipher.encryptSubstitution(password);
        HashMap<String, String> usernamePassword = new HashMap<>();
        StringBuilder block = new StringBuilder();
        try (Scanner sc = new Scanner(new File("users.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) continue;
                block.append(line).append("\n");
                if (line.endsWith(";")){
                    String userData = block.toString();
                    userData.substring(0,userData.length()-1).trim();
                    String[] fields = userData.split(",");
                    usernamePassword.put(fields[2],fields[3]);
                    block.setLength(0);
                }
            }
        } 
        catch (Exception e) {
            System.out.println("Error reading users file: " + e.getMessage());
            return false; // WIP here should not sign in and continue loop in main
        }
        System.out.println(usernamePassword);
        return usernamePassword.containsKey(username) && usernamePassword.get(username).equals(password);

    
    }
}
