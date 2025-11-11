package org.cs101project;

import java.io.FileWriter;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
public class Projectcs102 {
    private static HashMap<String,String> userPass = new HashMap<String,String>();
    public static void main(String[] args) {
        // System.out.println("Hi");    
        Scanner userInput = new Scanner(System.in);

        LinkedList<Person> Persons = getPersonsHistory();
        getUserPassFromPersons(Persons);

        System.out.println(userPass);

        // Main menu
        String[] possibleInput = {"Sing in", "Sign up", "Exit", "1", "2", "3"};
        String choice = null;
        while (choice == null || !includes(possibleInput, choice)) {
            System.out.println("Welcome! \n Type (1) OR (Sign in) to sign into your account. \n Type (2) OR (Sign up) to regiser. \n Type (3) OR (Exit) to quit the app.");
            System.out.print("Enter your choice: ");
            choice = userInput.nextLine();

        }
        if (choice.equalsIgnoreCase("Sign in") || choice.equalsIgnoreCase("1")) {
            boolean success = false;
            int attempts = 0;
            do {
            System.out.println("");
            System.out.println("-------(Sign in)-------");
            System.out.println("");
            System.out.print("Enter username: ");
            String loginUsername = userInput.nextLine();
            System.out.print("Enter password: ");
            String loginPassword = userInput.nextLine();
            System.out.println("");
            attempts ++;
            success = signIn(loginUsername, loginPassword);
            if (!success) System.out.println("Access denied. "+ "attempts: " + (attempts));
            } while (success == false && attempts < 3);

            if (success && attempts<3) {
                // Program to Display/Edit information
                System.out.println("Access granted!");











            } else {
                System.out.println("Access denied and attempts past 3.");
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
           
           String username = "";
           do{
            if (usernameExists(username))
                System.out.println("Username already exists! Try again!");
            System.out.print("Username: ");
            username = userInput.nextLine();
           }while (usernameExists(username));

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
                    ArrayList<Award> Awards = new ArrayList<Award>();
                    System.out.println("Awards: Do you have any Awards?");
                    if (userInput.nextLine().equalsIgnoreCase("yes")){
                        System.out.println("How many Awards do you have? ");
                        int awardsNum = userInput.nextInt();
                        userInput.nextLine(); //To remove buffer \n
                        for (int i = 0; i < awardsNum; i++) {
                            System.out.println("Award #"+(i+1));
                            System.out.print("Name: ");
                            String name = userInput.nextLine();
                            System.out.print("Date: ");
                            String date = userInput.nextLine();
                            System.out.print("Issuer: ");
                            String issuer = userInput.nextLine();
                            Awards.add(new Award(name, date,issuer));
                        }
                    }
                    else{}
                    //  TO DOO AWARDS IS TO DO

                    System.out.println("Student account created for " + fname + " " + lname);
                    saveUserToFile(new Student(fname,lname,username,password, Date, status, major, Awards));                    
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
                    saveUserToFile(new Faculty(fname, lname, username, password, Date, dept, office, rank, spec));
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
                    saveUserToFile(new SupportEmployee(fname, lname, username, password, Date, dept, office, job));
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






    public static void saveUserToFile(Person person) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("users.txt", true))) {

            pw.println(person.toString() + ";");


        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void getUserPassFromPersons(LinkedList<Person> Persons){
        for (Person person : Persons) {
            userPass.put(person.getUserName(),person.getPassword());
        }


    }
   public static boolean usernameExists(String username){
        return userPass.containsKey(username);
   }

    public static LinkedList<Person> getPersonsHistory(){
        LinkedList<Person> Persons = new LinkedList<Person>();
        StringBuilder block = new StringBuilder();
        try (Scanner sc = new Scanner(new File("users.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) continue;
                block.append(line).append("\n");
                if (line.endsWith(";")){
                    String userData = block.toString();
                    userData = userData.substring(0,userData.length()-1).trim();
                    userData = userData.replace(";","");
                    String[] fields = userData.split(",");
                    block.setLength(0);


                    if (fields[0].equals("Student")){
                        ArrayList<Award> awards = new ArrayList<Award>();
                        String awardsBlock = fields[8];
                        awardsBlock = awardsBlock.replace("Awards:\n", "").trim();
                        String[] awardLines = awardsBlock.split("\n");
                        if (awardLines.length > 1){
                            for (String awardLine: awardLines){
                                String[] awardFields = awardLine.split("-") ;
                                awards.add(new Award(awardFields[0],awardFields[1],awardFields[2]));
                            }
                        }

                        Persons.add(new Student(fields[1],fields[2],fields[3],fields[4],
                        fields[5],fields[6],fields[7], awards, true));
                        
                    }
                    else if(fields[0].equals("Faculty")){
                        Persons.add(
                            new Faculty(fields[1],fields[2],fields[3],fields[4],fields[5],fields[6],fields[7],fields[8],fields[9],true));
                    }
                    else if (fields[0].equals("Support Employee")){
                        Persons.add(
                            new SupportEmployee(fields[1],fields[2],fields[3],fields[4],fields[5],fields[6],fields[7],fields[8],true));
                    }
                    

                }
            }
        }
        catch (Exception e) {
            System.out.println("Error reading users file: " + e.getMessage());
               
    }
        return Persons;

    }
    public static boolean signIn(String username, String password) {
        
        return userPass.containsKey(username) && userPass.get(username).equals(Cipher.encryptSubstitution(password));

   
    }
}
