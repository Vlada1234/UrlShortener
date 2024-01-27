package com.urlshortener.demo.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Service
public class UrlKeyGenerationService {

    public String generateHashedKey(String input, int length) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());

            // Take the first 'length' characters of the hexadecimal representation of the hash
            StringBuilder hashedKey = new StringBuilder();
            for (int i = 0; i < Math.min(length / 2, hash.length); i++) {
                hashedKey.append(String.format("%02x", hash[i]));
            }

            return hashedKey.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
