package com.apbdoo.hrm.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {

    public static final String SHA256 = "SHA-256";

    public static String encrypt(String input) {
        return encrypt(input, SHA256);
    }

    public static String encrypt(String input, String algorithm) {
        MessageDigest md = null;
        String hash = new String() ;
        try {
            md = MessageDigest.getInstance(algorithm); 	//step 2
            md.update(input.getBytes("UTF-8")); 		//step 3
            byte raw[] = md.digest(); 					//step 4
            hash = (new CharacterEncoderBASE64()).encode(raw);
        } catch(UnsupportedEncodingException exc) {
            System.out.println(exc.getMessage());
        } catch(NoSuchAlgorithmException exc) {
            System.out.println(exc.getMessage());
        }
        return hash;
    }
}
