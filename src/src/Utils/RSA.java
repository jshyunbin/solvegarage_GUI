package src.Utils;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Tool for RSA
 *
 * @see <a href="https://gist.github.com/stunstunstun/8dbc82bd86f38c9232139e0ba9a7d8ad">
 * https://gist.github.com/stunstunstun/8dbc82bd86f38c9232139e0ba9a7d8ad</a>
 */
public class RSA {

    /**
     * Generates the RSA key pair length of 2048
     *
     * @return generated key pair
     */

    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048, new SecureRandom());
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No algorithm named 'RSA'");
        }
        return null;
    }

    /**
     * generates the public key from encoded key
     *
     * @param encodedPublicKey encoded public key
     * @return generated public key
     */

    private static PublicKey generatePublicKey(byte[] encodedPublicKey) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(encodedPublicKey));
        } catch (Exception e) {
            System.out.println("Error on generating public key");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * generates the private key from encoded key
     *
     * @param encodedPrivateKey encoded private key
     * @return generated private key
     */

    private static PrivateKey generatePrivateKey(byte[] encodedPrivateKey) {
        try {
            return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(encodedPrivateKey));
        } catch (Exception e) {
            System.out.println("Error on generating private key");
        }
        return null;
    }

    /**
     * encrypts the text given with public key
     *
     * @param plainText        text to encrypt
     * @param encodedPublicKey encoded public key
     * @return encrypted text with public key
     */

    public static String encrypt(String plainText, byte[] encodedPublicKey) {
        PublicKey publicKey = RSA.generatePublicKey(encodedPublicKey);
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            System.out.println("Error on encrypting");
        }
        return null;
    }

    /**
     * decrypts the text given with private key
     *
     * @param cipherText        text to decrypt
     * @param encodedPrivateKey encoded private key
     * @return decrypted text with private key
     */

    public static String decrypt(String cipherText, byte[] encodedPrivateKey) {
        PrivateKey privateKey = RSA.generatePrivateKey(encodedPrivateKey);
        try {
            byte[] bytes = Base64.getDecoder().decode(cipherText);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(bytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Error on decrypting");
        }
        return null;
    }

    /**
     * makes the sign of the text with private key
     *
     * @param plainText         text to encrypt
     * @param encodedPrivateKey encoded private key
     * @return made sign with text and private key
     */

    public static String sign(String plainText, byte[] encodedPrivateKey) {
        try {
            Signature privateSignature = Signature.getInstance("SHA512withRSA");
            privateSignature.initSign(RSA.generatePrivateKey(encodedPrivateKey));
            privateSignature.update(plainText.getBytes(StandardCharsets.UTF_8));
            byte[] signature = privateSignature.sign();
            return Base64.getEncoder().encodeToString(signature);
        } catch (Exception e) {
            System.out.println("Error on signing");
        }
        return null;
    }

    /**
     * verifies signature from text and public key
     *
     * @param plainText        text to check
     * @param signature        signature to check
     * @param encodedPublicKey public key to check
     * @return true or false whether it is verified
     */

    public static boolean verify(String plainText, String signature, byte[] encodedPublicKey) {
        PublicKey publicKey = RSA.generatePublicKey(encodedPublicKey);
        return RSA.verifySignature(plainText, signature, publicKey);
    }

    /**
     * Actual method for verify signature
     *
     * @param plainText text to verify
     * @param signature signature to verify
     * @param publicKey public key to verify
     * @return true if it is verified
     */

    private static boolean verifySignature(String plainText, String signature, PublicKey publicKey) {
        Signature sig;
        try {
            sig = Signature.getInstance("SHA512withRSA");
            sig.initVerify(publicKey);
            sig.update(plainText.getBytes());
            return sig.verify(Base64.getDecoder().decode(signature));
        } catch (Exception e) {
            System.out.println("Error on verifying signature");
        }
        return false;
    }
}