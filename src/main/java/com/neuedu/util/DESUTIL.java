package com.neuedu.util;

import com.thoughtworks.xstream.core.util.Base64Encoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DESUTIL {
    static Key KEY;
    static String KEYSTR = "abc";
    static {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEYSTR.getBytes());
            keyGenerator.init(secureRandom);
            KEY = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String enCode(String pswd){
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String result = "";
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, KEY);
            byte[] b = cipher.doFinal(pswd.getBytes());
            result = base64Encoder.encode(b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String deCode(String ciphertext){
        String result = null;
        Base64Encoder base64Encoder = new Base64Encoder();
        byte[] b = base64Encoder.decode(ciphertext);
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE,KEY);
            byte[] pswd = cipher.doFinal(b);
            result = new String(pswd);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(enCode("root"));
        System.out.println(enCode("123456"));
    }
}
