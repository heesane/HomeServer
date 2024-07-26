package hhs.server.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtil {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SecureRandom secureRandom = new SecureRandom();

    public String encode(String rawPassword) throws NoSuchAlgorithmException {
        // Step 1: Apply initial hashing (e.g., SHA-256)
        String initialHash = sha256Hash(rawPassword);

        // Step 2: Add additional salt
        String saltedHash = addSalt(initialHash);

        // Step 3: Apply BCrypt hashing
        return passwordEncoder.encode(saltedHash);
    }

    private String sha256Hash(String rawPassword) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(rawPassword.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(Integer.toHexString(0xFF & b));
        }
        return hexString.toString();
    }

    private String addSalt(String hash) {
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        StringBuilder saltedHash = new StringBuilder(hash);
        for (byte b : salt) {
            saltedHash.append(Integer.toHexString(0xFF & b));
        }
        return saltedHash.toString();
    }
}
