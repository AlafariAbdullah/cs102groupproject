package org.cs101project;

public class Cypher {

    public static String encryptSubstitution(String plaintext) {
        //Substitution Cipher11
        char[] encryptedArr = new char[plaintext.length()];

        for (int i = 0; i < plaintext.length(); i++) {
            char encryptedChar = (char) ('A' + (plaintext.charAt(i) - 'A' + 3) % 52);
            encryptedArr[i] = encryptedChar;
        }
        return (new String(encryptedArr));
        // Tiny Encryption Algorithm (TEA)22 
    }
}
