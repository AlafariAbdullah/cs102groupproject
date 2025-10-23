

public class SubstitutionCypherTesting {
    public static void main(String[] args) {
        String plaintext = "myPassword123z";
        char[] encryptedArr = new char[plaintext.length()];

        for (int i = 0; i < plaintext.length(); i++) {
            char encryptedChar = (char) ('A' + (plaintext.charAt(i) - 'A' + 3) %52);
            encryptedArr[i] = encryptedChar;
        }
        System.out.println(new String(encryptedArr));
    }
}
