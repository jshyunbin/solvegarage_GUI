package src.Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

class AES {

    /**
     * Generates the random key for AES256.
     * Key would be 256 bytes long.
     *
     * @return byte array of datas
     */

    public static byte[] generateKey() {
        Random rnd = new Random();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 256 / 8; i++) {
            int r = rnd.nextInt(62);
            if (r < 10)
                res.append((char) (48 + r));
            else if (r < 36)
                res.append((char) (65 + r - 10));
            else
                res.append((char) (97 + r - 36));
        }
        return res.toString().getBytes();
    }

    /**
     * Encrypts the plain text then encodes with base64
     *
     * @param plainText text to encrypt
     * @param key       key to encrypt
     * @return encrypted text
     */

    public static String encrypt(String plainText, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(new String(key).substring(16).getBytes()));
            byte[] bytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            System.out.println("Error on encrypting");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Decrypts the text
     *
     * @param encryptedText text to decrypt
     * @param key           text to decrypt
     * @return decrypted text
     */

    public static String decrypt(String encryptedText, byte[] key) {
        try {
            byte[] bytes = Base64.getDecoder().decode(encryptedText);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(new String(key).substring(16).getBytes()));
            return new String(cipher.doFinal(bytes));
        } catch (Exception e) {
            System.out.println("Error on decrypting");
            e.printStackTrace();
        }
        return null;
    }
}