/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author mrcpe
 */
public class Utils {
    public static String md5(String palabra){
        String palabraMD5="";
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            byte[] b=md.digest(palabra.getBytes());
            int size=b.length;
            StringBuffer sb=new StringBuffer(size);
            for(int i=0;i<size;i++){
                int u=b[i] & 255;
                if(u<16){
                    sb.append("0"+Integer.toHexString(u));
                }
                else{
                    sb.append(Integer.toHexString(u));
                }
            }
            palabraMD5=sb.toString();
        }catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        return palabraMD5;
    }
}
