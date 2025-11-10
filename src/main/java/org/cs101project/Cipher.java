package org.cs101project;

public class Cipher {

    public static String encryptSubstitution(String plaintext) {
        char[] encryptedArr = new char[plaintext.length()];
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            encryptedArr[i] = (char)(c + 3);
        }
        return new String(encryptedArr);
    }
    
    public static String decryptSubstitution(String encryptedText) {
        char[] decryptedArr = new char[encryptedText.length()];
        for (int i = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            decryptedArr[i] = (char)(c - 3);
        }
        return new String(decryptedArr);
    }        // Tiny Encryption Algorithm (TEA)22 
    
}
