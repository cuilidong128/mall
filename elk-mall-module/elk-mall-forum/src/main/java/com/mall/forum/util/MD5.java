package com.mall.forum.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.mall.forum.core.exceptions.ForumException;
public class MD5 {
    /**
     * Encodes a string
     *
     * @param str String to encode
     * @return Encoded String
     * @throws NoSuchAlgorithmException
     */
    public static String hash(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String cannot be null or zero length");
        }

        StringBuilder hexString = new StringBuilder();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();

            for (byte element : hash) {
                if ((0xff & element) < 0x10) {
                    hexString.append('0').append(Integer.toHexString((0xFF & element)));
                }
                else {
                    hexString.append(Integer.toHexString(0xFF & element));
                }
            }
        }
        catch (NoSuchAlgorithmException e) {
            throw new ForumException(e);
        }

        return hexString.toString();
    }
}

