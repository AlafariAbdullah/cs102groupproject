package org.cs101project;

import java.io.FileWriter;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Projectcs102 {

    private static HashMap<String, String> userPass = new HashMap<String, String>();
    private static LinkedList<Person> Persons;
    private static Person currentUser;

    public static void main(String[] args) {
        // System.out.println("Hi");    
        Persons = getPersonsHistory();
        getUserPassFromPersons(Persons);
        try {
            Scanner userInput = new Scanner(System.in);

            System.out.println(userPass);

            // Main menu
            String[] possibleInput = {"Sign in", "Sign up", "Exit", "1", "2", "3"};
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
                    attempts++;
                    success = signIn(loginUsername, loginPassword);
                    if (!success) {
                        System.out.println("Access denied. " + "attempts: " + (attempts));
                    }
                    
                } while (success == false && attempts < 3);

                if (success) {
                    // Program to Display/Edit information
                    //Abdelrahman: The requirements state that giving 4 options to the user after signing in, I changed
                    //the code here to make it closer to the document.
                    System.out.println("Access granted!\nWelcome: "+currentUser.getFirstName()+" "+currentUser.getSurName());
                    boolean inMenue = true;
                    while(inMenue){
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("Do you want to:\n(1)Show your information?\n(2)Change your name?\n(3)Change your password?\n(4)Exit");
                    System.out.print("Enter your choice: ");

                    choice = userInput.nextLine();

                    if (choice.equals("1")) { //Showing information
                        System.out.println();
                        System.out.println();
                        if (currentUser instanceof Student) {
                            Student currentStudent = (Student) (currentUser);
                            System.out.println("");
                            if (!currentStudent.getAwards().isEmpty()) {
                                System.out.println("Do you want to show your awards sorted? (Y/N)");
                                choice = userInput.nextLine();
                                if (choice.equalsIgnoreCase("y")) {
                                    System.out.println("");
                                    System.out.println("Do you want to sort the awards based on name or date and ascending or descending?"
                                            + "\n(1) name ascending\n(2) name descending\n(3) date ascending\n(4) date descending");
                                    System.out.print("Enter your choice: ");

                                    choice = userInput.nextLine();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    switch (choice) {
                                        case "1":
                                            Collections.sort(currentStudent.getAwards());
                                            break; //first choice is name ascending so no comparator
                                        case "2":
                                            Collections.sort(currentStudent.getAwards());
                                            Collections.reverse(currentStudent.getAwards());
                                            break; //sort ascending then reverse in one line 
                                        case "3":
                                            Collections.sort(currentStudent.getAwards(), new AwardsByDateComparator());
                                            break;
                                        case "4":
                                            Collections.sort(currentStudent.getAwards(), new AwardsByDateComparator());
                                            Collections.reverse(currentStudent.getAwards());
                                            break;
                                    }
                                }
                                // if choice is "Y" we sorted else print as-is
                                System.out.println(currentStudent.toDisplayString());
                            } else {
                                System.out.println(currentStudent.toDisplayString());
                            }
                        } else {
                            // For Faculty, SupportEmployee, or any other Person type
                            System.out.println(currentUser.toDisplayString());
                        }
                    }
                    else if (choice.equals("2")) {
                        //I checked the requirements and it requires changing first and last name but doesn't care about username
                        
                        boolean vaidFirstName= false;
                        while (!vaidFirstName){
                        try{
                        System.out.println("Enter your first name: ");
                        String newFirstName = userInput.nextLine();
                        currentUser.setFirstName(newFirstName);
                        vaidFirstName = true;
                        }catch(IllegalArgumentException e){
                            System.out.println(e.getMessage());
                        }
                    }

                        boolean validSurname = false;
                        while (!validSurname) {
                            try {
                                System.out.println("Enter your surname: ");
                                String newLastName = userInput.nextLine();
                                currentUser.setSurName(newLastName);
                                validSurname = true;
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                     else if (choice.equals("3")) {
                        // Change password
                        boolean passwordChanged = false;
                        while (!passwordChanged) {
                            System.out.println("Enter your new password: ");
                            String newPassword = userInput.nextLine();

                            if (!isValidPassword(newPassword)) {
                                System.out.println("Invalid password!");
                                System.out.println("It must be at least 6 characters and contain at least one capital letter, one small letter, one digit, and one special character.");
                                System.out.println("");
                            }

                            System.out.println("Confirm your password: ");
                            String confirmPassword = userInput.nextLine();

                            if (!newPassword.equals(confirmPassword)) {
                                System.out.println("Error! Passwords don't match! Please try again.");
                                System.out.println("");
                                
                            }

                            // If we reach here, password is valid and confirmed
                            currentUser.setPassword(newPassword);
                            // Keep the userPass map in sync with the updated password
                            userPass.put(currentUser.getUserName(), currentUser.getPassword());

                            System.out.println("Password changed successfully.");
                            passwordChanged = true;
                        }

                    } else if (choice.equals("4")) { //Stub for exit
                        System.exit(1); 
                        
                    }
                }
                
                } else {
                    System.out.println("Access denied and attempts past 3.");
                }
            } else if (choice.equalsIgnoreCase("Sign up") || choice.equalsIgnoreCase("2")) {
                System.out.println("");
                System.out.println("-------(Sign up)-------");

                String type = "";
                do {
                    if (!type.equals("")) //So it runs the second time only
                    {
                        System.out.println("Invalid Input");
                    }
                    System.out.println("\n Type (1) If you are a student.\n Type (2) If you are a faculty member.\n Type (3) If you are a support employee.\n Type (4) to exit");
                    System.out.print("Enter your choice: ");
                    type = userInput.nextLine();
                    if (type.equals("4")) {
                        overWriteFile(Persons);
                        System.exit(1);
                    }
                } while (!type.equals("1") && !type.equals("2") && !type.equals("3"));

                String fname = "";
                boolean validFirst = false;
                while(!validFirst){
                    System.out.println("");
                    System.out.print("First name: ");
                    fname = userInput.nextLine();
                    if(validFirstSurname(fname))
                        validFirst =true;
                    else
                        System.out.println("Invalid first name! Try again.");
                }
                String lname = "";
                boolean validLast = false;
                while(!validLast){
                    System.out.println("");
                    System.out.print("Last name: ");
                    lname = userInput.nextLine();
                    if(validFirstSurname(lname))
                        validLast =true;
                    else
                        System.out.println("Invalid last name! Try again.");
                }   
                String username = "";
                boolean validUsername = false;
                while(!validUsername){
                    try{
                        System.out.print("Username: ");
                        username = userInput.nextLine();

                        if (usernameExists(username)) {
                            throw new IllegalArgumentException("Username already exists! Try again!");
                        }
                        validUsername = true;
                    } catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                 }
                System.out.print("Date of birth: ");
                boolean dateAccepted = false;
                String dateToTest = null;
                while (!dateAccepted) {
                    dateToTest = userInput.nextLine();
                    if (DateValidator.isValidDate(dateToTest)) {
                        dateAccepted = true;
                    } else {
                        System.out.println("Invalid format or impossible date.");
                        System.out.println("");
                        System.out.print("Date of birth: ");

                    }
                }
                String Date = dateToTest;

                System.out.print("Password: ");
                boolean passwordAccepted = false;
                String password = null;

                while (!passwordAccepted) {
                    password = userInput.nextLine();

                    if (isValidPassword(password)) {
                        passwordAccepted = true;
                    } else {
                        System.out.println("Invalid password!\nIt must be at least 6 characters and contain at least one capital letter.");
                        System.out.println("");
                        System.out.print("Password: ");
                    }
                }

                switch (type) {
                    case "1": {
                        type = "Student";

                        String status = "";
                        boolean validStatus = false;
                        while (!validStatus) {
                            try {
                                System.out.print("Status (Freshman, Sophomore, Junior or Senior): ");
                                status = userInput.nextLine();
                                String trimmed = status.trim();
                        
                                if (!(trimmed.equalsIgnoreCase("Freshman") ||
                                      trimmed.equalsIgnoreCase("Sophomore") ||
                                      trimmed.equalsIgnoreCase("Junior") ||
                                      trimmed.equalsIgnoreCase("Senior"))) {
                        
                                    throw new IllegalArgumentException(
                                        "Status must be one of: Freshman, Sophomore, Junior, Senior"
                                    );
                                }
                        
                                // if we reach here, status is valid
                                validStatus = true;
                        
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }







                        String major = "";
                        boolean validMajor = false;
                        while (!validMajor) {
                            System.out.print("Major: ");
                            major = userInput.nextLine();
                        
                            if (notEmpty(major))
                                validMajor = true;
                            else
                                System.out.println("Major cannot be empty! Try again.");
                        }

                        // Sample Data!!! Awards logic should be done
                        ArrayList<Award> Awards = new ArrayList<Award>();
                        System.out.println("Awards: Do you have any Awards?");
                        if (userInput.nextLine().equalsIgnoreCase("yes")) {
                            System.out.println("How many Awards do you have? ");
                            int awardsNum = userInput.nextInt();
                            userInput.nextLine(); //To remove buffer \n
                            for (int i = 0; i < awardsNum; i++) {
                                System.out.println("Award #" + (i + 1));
                                System.out.print("Name: ");
                                String name = userInput.nextLine();
                                System.out.print("Date: ");
                                String date = userInput.nextLine();
                                System.out.print("Issuer: ");
                                String issuer = userInput.nextLine();
                                Awards.add(new Award(name, date, issuer));
                            }
                        } else {
                        }
                        //  TO DOO AWARDS IS TO DO

                        System.out.println("Student account created for " + fname + " " + lname);
                        saveUserToFile(new Student(fname, lname, username, password, Date, status, major, Awards));
                        break;
                    }
                    case "2": {
                        type = "Faculty";
                        
                        String dept = "";
                        boolean validDept = false;
                        while (!validDept) {
                            System.out.print("Department: ");
                            dept = userInput.nextLine();
                        
                            if (notEmpty(dept))
                                validDept = true;
                            else
                                System.out.println("Department cannot be empty! Try again.");
                        }
                        System.out.print("Office number: ");
                        String office = userInput.nextLine();


                        String rank = "";
                        boolean validRank = false;
                        while (!validRank) {
                            try {
                                System.out.print("Rank: ");
                                rank = userInput.nextLine();
                                String trimmed = rank.trim();

                                if (!(trimmed.equalsIgnoreCase("Lecturer") ||
                                      trimmed.equalsIgnoreCase("Assistant Professor") ||
                                      trimmed.equalsIgnoreCase("Associate Professor") ||
                                      trimmed.equalsIgnoreCase("Professor"))) {

                                    throw new IllegalArgumentException(
                                        "Rank must be one of: Lecturer, Assistant Professor, Associate Professor, Professor"
                                    );
                                }

                                // if we reach here, rank is valid
                                validRank = true;

                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }


                        String spec = "";
                        boolean validSpec = false;
                        while (!validSpec) {
                            System.out.print("Specialization: ");
                            spec = userInput.nextLine();
                        
                            if (notEmpty(spec))
                                validSpec = true;
                            else
                                System.out.println("Specialization cannot be empty! Try again.");
                        }
                        
                        System.out.println("Faculty account created for " + fname + " " + lname);
                        saveUserToFile(new Faculty(fname, lname, username, password, Date, dept, office, rank, spec));
                        break;

                    }
                    case "3": {
                        type = "SupportEmployee";
                        String dept = "";
                        boolean validDept = false;
                        while (!validDept) {
                            System.out.print("Department: ");
                            dept = userInput.nextLine();
                        
                            if (notEmpty(dept))
                                validDept = true;
                            else
                                System.out.println("Department cannot be empty! Try again.");
                        }
                        System.out.print("Office number: ");
                        String office = userInput.nextLine();
                        System.out.print("Job description: ");
                        String job = userInput.nextLine();
                        System.out.println("Support & Services account created for " + fname + " " + lname);
                        saveUserToFile(new SupportEmployee(fname, lname, username, password, Date, dept, office, job));
                        break;
                    }
                }

            } else if (choice.equalsIgnoreCase("Exit") || choice.equalsIgnoreCase("3")) {
                overWriteFile(Persons);
                System.exit(1);

                System.out.println("(Sign up is complete)");
            }
        } finally {
            overWriteFile(Persons);
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

    public static void overWriteFile(LinkedList<Person> list) {
        // System.out.println(list);
        try (PrintWriter pw = new PrintWriter(new File("users.txt"))) {
            for (Person p : list) {
                pw.println(p.toString() + ";");
            }
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }

    public static void saveUserToFile(Person person) {
        Persons.add(person);
    }

    public static void getUserPassFromPersons(LinkedList<Person> Persons) {
        for (Person person : Persons) {
            userPass.put(person.getUserName(), person.getPassword());
        }

    }

    public static boolean usernameExists(String username) {
        return userPass.containsKey(username);
    }
    public static boolean validFirstSurname(String name){
        if (name == null)
            return false;
        else{
            name = name.trim();
            if (name.isEmpty())
                return false;
            return true;
        }

    }

    public static LinkedList<Person> getPersonsHistory() {
        LinkedList<Person> Persons = new LinkedList<Person>();
        StringBuilder block = new StringBuilder();
        try (Scanner sc = new Scanner(new File("users.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                block.append(line).append("\n");
                if (line.endsWith(";")) {
                    String userData = block.toString();
                    userData = userData.substring(0, userData.length() - 1).trim();
                    userData = userData.replace(";", "");
                    String[] fields = userData.split(",");
                    block.setLength(0);

                    if (fields[0].equals("Student")) {
                        ArrayList<Award> awards = new ArrayList<Award>();
                        String awardsBlock = fields[8];
                        awardsBlock = awardsBlock.replace("Awards:\n", "").trim();
                        String[] awardLines = awardsBlock.split("\n");
                        if (awardLines.length > 1) {
                            for (String awardLine : awardLines) {
                                String[] awardFields = awardLine.split("-");
                                awards.add(new Award(awardFields[0], awardFields[1], awardFields[2]));
                            }
                        }

                        Persons.add(new Student(fields[1], fields[2], fields[3], fields[4],
                                fields[5], fields[6], fields[7], awards, true));

                    } else if (fields[0].equals("Faculty")) {
                        Persons.add(
                                new Faculty(fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8], fields[9], true));
                    } else if (fields[0].equals("Support Employee")) {
                        Persons.add(
                                new SupportEmployee(fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8], true));
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Error reading users file: " + e.getMessage());

        }
        return Persons;

    }

    public static boolean signIn(String username, String password) {
        boolean success = userPass.containsKey(username) && userPass.get(username).equals(Cipher.encryptSubstitution(password));
        for (Person p : Persons) {
            if (p.getUserName().equals(username)) {
                currentUser = p;
            }
        }

        return success;
    }
    public static boolean notEmpty(String input) {
        if (input == null)
            return false;
    
        input = input.trim();
    
        return !input.isEmpty();
    }

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }

        // Must be at least 6 characters
        if (password.length() < 6) {
            return false;
        }

        // at least one capital letter, one small letter, one number and one special
        // character

        boolean hasCapital = false;
        boolean hasSmall = false;
        boolean hasSpecial = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }
            else if(Character.isDigit(c))
                hasDigit = true;
            else if(Character.isLowerCase(c))
                    hasSmall = true;
            else if(!Character.isLetterOrDigit(c))
                hasSpecial = true;
        }
        return hasCapital && hasSmall && hasSpecial && hasDigit;
    }

}
