/**
 * 
 */
package com.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月8日
 */
public class StringHelper {
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String sha1Hex(String data) {
        if (data == null) {
            return "";
        }

        byte[] bytes = digest("SHA1", data);

        return toHexString(bytes);
    }

    private static String toHexString(byte[] bytes) {
        int l = bytes.length;

        char[] out = new char[l << 1];

        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & bytes[i]) >>> 4];
            out[j++] = DIGITS[0x0F & bytes[i]];
        }

        return new String(out);
    }
    
    private static byte[] digest(String algorithm, String data) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return digest.digest(data.getBytes());
    }
    
    public static boolean isEmpty(String str){
    	if(null == str || "".equals(str)){
    		return true;
    	}
    	return false;
    }
}
