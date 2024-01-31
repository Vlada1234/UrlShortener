package com.urlshortener.demo.service;

import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UrlHashedKeyGenerationService implements UrlKeyGenerationService {

    @Override
    public String generateKey(String input, int length) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());

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
