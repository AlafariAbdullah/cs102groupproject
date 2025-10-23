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
        String[] possibleInput = {"Sing in", "Sign up", "Exit", "1", "2" ,"3"};
        String choice = null;
        while (choice == null || !includes(possibleInput, choice)){
        System.out.println("Welcome! \n Type (Sign in) OR (1) to sign into your account \n or type (Sign up) OR (2 ) to regiser \n or type (Exit) (3) to quit the app");
        choice = userInput.next();
        
        }
        if (choice.equalsIgnoreCase("Sign in") || choice.equalsIgnoreCase("1")){

        }
        else if (choice.equalsIgnoreCase("Sign up") || choice.equalsIgnoreCase("2")){
        
        }
        else if (choice.equalsIgnoreCase("Exit") || choice.equalsIgnoreCase("3")){
            System.exit(1);
        }







    }
    public static boolean includes(String[] arr, String str){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equalsIgnoreCase(str))
                return true;
        }
        return false;
    }
}
