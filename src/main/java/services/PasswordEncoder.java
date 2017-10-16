package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    public static String md5(String password){
        String result=null;
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            byte[] array=password.getBytes();
            byte[] array2=md.digest(array);
            result=new String(array2);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String encode(String password){
        String result=md5(password)+ "skdjvdsjvsjlv";
        StringBuilder sb=new StringBuilder(result).reverse();
        result=sb.toString();
        return result;
    }

}
