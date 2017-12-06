package com.lintas.config;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;



public class encryptions {

	Cipher ecipher;
	Cipher dcipher;
	private final String PRIVATEKEY="Lintas010";
	static final Logger logger = Logger.getLogger(encryptions.class);
	public encryptions(String pass) {

		byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
				(byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03 };

		int iterationCount = 10;

		try {

			KeySpec keySpec = new PBEKeySpec(pass.toCharArray(), salt,
					iterationCount);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);

			ecipher = Cipher.getInstance(key.getAlgorithm());
			dcipher = Cipher.getInstance(key.getAlgorithm());

			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
					iterationCount);

			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

		} catch (InvalidAlgorithmParameterException e) {
			logger.error("EXCEPTION: InvalidAlgorithmParameterException");
		} catch (InvalidKeySpecException e) {
			logger.error("EXCEPTION: InvalidKeySpecException");
		} catch (NoSuchPaddingException e) {
			logger.error("EXCEPTION: NoSuchPaddingException");
		} catch (NoSuchAlgorithmException e) {
			logger.error("EXCEPTION: NoSuchAlgorithmException");
		} catch (InvalidKeyException e) {
			logger.error("EXCEPTION: InvalidKeyException");
		}
	}
	public encryptions() {
		super();
		byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
				(byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03 };

		int iterationCount = 10;


		try {

			KeySpec keySpec = new PBEKeySpec(PRIVATEKEY.toCharArray(), salt,
					iterationCount);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);

			ecipher = Cipher.getInstance(key.getAlgorithm());
			dcipher = Cipher.getInstance(key.getAlgorithm());

			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt,
					iterationCount);

			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

		} catch (InvalidAlgorithmParameterException e) {
			logger.error("EXCEPTION: InvalidAlgorithmParameterException");
		} catch (InvalidKeySpecException e) {
			logger.error("EXCEPTION: InvalidKeySpecException");
		} catch (NoSuchPaddingException e) {
			logger.error("EXCEPTION: NoSuchPaddingException");
		} catch (NoSuchAlgorithmException e) {
			logger.error("EXCEPTION: NoSuchAlgorithmException");
		} catch (InvalidKeyException e) {
			logger.error("EXCEPTION: InvalidKeyException");
		}
	}

	public String encrypt(String str) {
		try {
			// Encode the string into bytes using utf-8
			byte[] utf16 = str.getBytes("UTF32");

			// Encrypt
			byte[] enc = ecipher.doFinal(utf16); 

			return new sun.misc.BASE64Encoder().encode(enc);

		} catch (BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
		}
		return null;
	}

	public String decrypt(String str) {

		try {

			// Decode base64 to get bytes
			byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

			// Decrypt
			byte[] utf16 = dcipher.doFinal(dec);

			// Decode using utf-8
			return new String(utf16, "UTF32");

		} catch (BadPaddingException e) {
			logger.error(e);
		} catch (IllegalBlockSizeException e) {
			logger.error(e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
	}


	public static void main(String[] args) throws Exception {

		mainAES();

	}
	static Cipher cipher;
	static Key aesKey = getKey(); 
	static String initVector = "RandomInitVector"; // 16 bytes IV
	public static Key getKey()
	{
		String key = "Bar12345Bar78921"; // 128 bit key
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		return aesKey;
	}
	public static void mainAES() throws Exception {
		/*String plainText = "yogesh@spysr.in-sp1-1";
		logger.info("Plain Text Before Encryption: " + plainText);

		String encryptedText = encryptAES(plainText);
		logger.info("Encrypted Text After Encryption: " + encryptedText);

		String decryptedText = decryptAES(encryptedText);
		logger.info("Decrypted Text After Decryption: " + decryptedText);*/
	}
	public  String encryptAES(String plainText)
			throws Exception {
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
		cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");	
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, aesKey,iv);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		String encryptedText = Base64.encodeBase64String(encryptedByte);
		return encryptedText;
	}

	public  String decryptAES(String encryptedText)
			throws Exception {
		cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");	
		IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
		byte[] encryptedTextByte = Base64.decodeBase64(encryptedText);
		cipher.init(Cipher.DECRYPT_MODE, aesKey,iv);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);		
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}

}
