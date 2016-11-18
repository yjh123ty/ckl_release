package tech.youmu.ckl.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class SHA1Util {
	
	public static String hexSHA1(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(value.getBytes("utf-8"));
			byte[] digest = md.digest();
			return String.valueOf(Hex.encodeHex(digest));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
