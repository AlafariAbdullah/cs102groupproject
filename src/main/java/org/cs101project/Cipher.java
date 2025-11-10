package org.cs101project;

import java.util.Arrays;
import java.util.Base64;

public class Cipher {
    // ================= Substitution cipher =================
    public static String encryptSubstitution(String plaintext) {
        char[] encryptedArr = new char[plaintext.length()];
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            encryptedArr[i] = (char) (c + 3);
        }
        return new String(encryptedArr);
    }

    public static String decryptSubstitution(String encryptedText) {
        char[] decryptedArr = new char[encryptedText.length()];
        for (int i = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            decryptedArr[i] = (char) (c - 3);
        }
        return new String(decryptedArr);
    }

    // ================= TEA22 (Tiny Encryption Algorithm) =================

    private static final String KEY = "1234567890ABCDEF";   // 16-byte key
    private static final int[] KEY_WORDS = divideKeyToSections(KEY); // k0..k3

    public static String encryptTinyEncryptionAlgorithm(String plaintext) {
        byte[] plainBytes = plaintext.getBytes();

        // pad to multiple of 8 bytes
        byte[] padded = padToBlockSize(plainBytes, 8);
        byte[] cipherBytes = new byte[padded.length];

        // process 8-byte blocks
        for (int offset = 0; offset < padded.length; offset += 8) {
            encryptTea22Block(padded, offset, cipherBytes, offset, KEY_WORDS);
        }

        // return Base64 string
        return Base64.getEncoder().encodeToString(cipherBytes);
    }

    public static String decryptTinyEncryptionAlgorithm(String encryptedText) {
        // decode Base64 to raw cipher bytes
        byte[] cipherBytes = Base64.getDecoder().decode(encryptedText);
        byte[] plainPadded = new byte[cipherBytes.length];

        // process 8-byte blocks
        for (int offset = 0; offset < cipherBytes.length; offset += 8) {
            decryptTea22Block(cipherBytes, offset, plainPadded, offset, KEY_WORDS);
        }

        // remove zero padding
        byte[] plain = removeZeroPadding(plainPadded);
        return new String(plain);
    }

    // ================= Helpers =================

    private static byte[] padToBlockSize(byte[] data, int blockSize) {
        int paddedLength = ((data.length + blockSize - 1) / blockSize) * blockSize;
        byte[] padded = new byte[paddedLength];
        System.arraycopy(data, 0, padded, 0, data.length); // remaining bytes stay 0
        return padded;
    }

    private static byte[] removeZeroPadding(byte[] data) {
        int index = data.length;
        while (index > 0 && data[index - 1] == 0) {
            index--;
        }
        return Arrays.copyOf(data, index);
    }


    private static int[] divideKeyToSections(String key) {
        byte[] keyBytes = key.getBytes();
        return new int[] {
                bytesToInt(keyBytes, 0),
                bytesToInt(keyBytes, 4),
                bytesToInt(keyBytes, 8),
                bytesToInt(keyBytes, 12)
        };
    }


    // Convert 4 bytes starting at offset into int
    private static int bytesToInt(byte[] data, int offset) {
        return ((data[offset]     & 0xFF) << 24) |
               ((data[offset + 1] & 0xFF) << 16) |
               ((data[offset + 2] & 0xFF) <<  8) |
               ( data[offset + 3] & 0xFF);
    }

    // Write int as 4 big-endian bytes into dest at offset
    private static void intToBytes(int value, byte[] dest, int offset) {
        dest[offset]     = (byte) (value >>> 24);
        dest[offset + 1] = (byte) (value >>> 16);
        dest[offset + 2] = (byte) (value >>> 8);
        dest[offset + 3] = (byte) (value);
    }


    private static void encryptTea22Block(byte[] in, int inOffset,
                                          byte[] out, int outOffset,
                                          int[] k) {
        int v0 = bytesToInt(in, inOffset);
        int v1 = bytesToInt(in, inOffset + 4);

        int k0 = k[0], k1 = k[1], k2 = k[2], k3 = k[3];
        int delta = 0x9E3779B9;
        int sum = 0;

        for (int i = 0; i < 22; i++) {
            sum += delta;
            v0 += ((v1 << 4) + k0) ^ (v1 + sum) ^ ((v1 >>> 5) + k1);
            v1 += ((v0 << 4) + k2) ^ (v0 + sum) ^ ((v0 >>> 5) + k3);
        }

        intToBytes(v0, out, outOffset);
        intToBytes(v1, out, outOffset + 4);
    }

    private static void decryptTea22Block(byte[] in, int inOffset,
                                          byte[] out, int outOffset,
                                          int[] k) {
        int v0 = bytesToInt(in, inOffset);
        int v1 = bytesToInt(in, inOffset + 4);

        int k0 = k[0], k1 = k[1], k2 = k[2], k3 = k[3];
        int delta = 0x9E3779B9;
        int sum = delta * 22;

        for (int i = 0; i < 22; i++) {
            v1 -= ((v0 << 4) + k2) ^ (v0 + sum) ^ ((v0 >>> 5) + k3);
            v0 -= ((v1 << 4) + k0) ^ (v1 + sum) ^ ((v1 >>> 5) + k1);
            sum -= delta;
        }

        intToBytes(v0, out, outOffset);
        intToBytes(v1, out, outOffset + 4);
    }
}