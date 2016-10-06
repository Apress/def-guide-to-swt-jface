package examples.ch9.password.data;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

/**
 * This class manages the security for the password app. It creates an MD5 hash
 * of the master password, and uses Password Based Encryption to encrypt all
 * other user IDs and passwords
 */
public class SecurityHandler {
  // Arbitrary bytes for the salt
  private static final byte[] SALT = { (byte) 0x33, (byte) 0x44, (byte) 0xda,
      (byte) 0x23, (byte) 0x11, (byte) 0x32, (byte) 0xae, (byte) 0xff};
  private static final int ITERATIONS = 20;

  private static final String ALGORITHM = "PBEWithMD5AndDES";
  private static final MessageDigest MD;
  static {
    try {
      MD = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException("Cannot create security handler");
    }
  }

  /**
   * Gets an MD5 hash of an arbitrary String
   * 
   * @param password the string
   * @return byte[]
   */
  public static byte[] getHash(String password) {
    return MD.digest(password.getBytes());
  }

  /**
   * Checks hashes for a match
   * 
   * @param a one hash to check
   * @param b the other hash to check
   * @return boolean
   */
  public static boolean checkHash(byte[] a, byte[] b) {
    return MessageDigest.isEqual(a, b);
  }

  /**
   * Gets a Cipher object for the password and mode
   * 
   * @param password the password
   * @param mode the mode
   * @return Cipher
   */
  private static Cipher getCipher(String password, int mode) {
    Cipher cipher = null;
    PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
    try {
      SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
      SecretKey key = factory.generateSecret(keySpec);
      cipher = Cipher.getInstance(ALGORITHM);
      PBEParameterSpec spec = new PBEParameterSpec(SALT, ITERATIONS);
      cipher.init(mode, key, spec);
    } catch (Exception e) {
      e.printStackTrace();
      cipher = null;
    }
    return cipher;
  }

  /**
   * Encrypts a string, using the password
   * 
   * @param plainText the string to encrypt
   * @param password the password
   * @return String
   */
  public static String encrypt(String plainText, String password) {
    String encryptedText = null;
    Cipher cipher = getCipher(password, Cipher.ENCRYPT_MODE);
    if (cipher == null) { throw new IllegalStateException("Encryption failed"); }
    try {
      byte[] b = cipher.doFinal(plainText.getBytes());
      encryptedText = new String(b);
    } catch (Exception e) {
      throw new IllegalStateException("Encryption failed");
    }
    return encryptedText;
  }

  /**
   * Decrypts a string, using the password
   * 
   * @param encryptedText the string to decrypt
   * @param password the password
   * @return String
   */
  public static String decrypt(String encryptedText, String password) {
    String plainText = null;
    Cipher cipher = getCipher(password, Cipher.DECRYPT_MODE);
    if (cipher == null) { throw new IllegalStateException("Decryption failed"); }
    try {
      byte[] b = cipher.doFinal(encryptedText.getBytes());
      plainText = new String(b);
    } catch (Exception e) {
      throw new IllegalStateException("Decryption failed");
    }
    return plainText;
  }
}
