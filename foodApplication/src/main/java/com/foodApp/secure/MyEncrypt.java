package com.foodApp.secure;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyEncrypt {
	private static final String AES = "AES";
	private static final byte[] KEY = "1234567890123456".getBytes(); // Use a 16-byte key

	// Encrypt a string using AES
	public static String encrypt(String plainText) {
		try {
			SecretKey secretKey = new SecretKeySpec(KEY, AES);
			Cipher cipher = Cipher.getInstance(AES);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
			return Base64.getEncoder().encodeToString(encryptedBytes); // Convert to Base64 string
		} catch (Exception e) {
			throw new RuntimeException("Error during encryption: " + e.getMessage(), e);
		}
	}

	public static String decrypt(String encryptedText) {
		try {
			SecretKey secretKey = new SecretKeySpec(KEY, AES);
			Cipher cipher = Cipher.getInstance(AES);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
			return new String(decryptedBytes, "UTF-8"); // Convert back to plain text
		} catch (Exception e) {
			throw new RuntimeException("Error during decryption: " + e.getMessage(), e);
		}
	}
}
