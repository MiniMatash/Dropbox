package com.minimatash.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

 public final class Encrypt {
    /**
     * @param arg
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt(String arg) throws NoSuchAlgorithmException {
        return sha1(arg);
    }

    private static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte aResult : result) {
            sb.append(Integer.toString((aResult & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
