package com.urlshortener.demo.service;

import org.springframework.stereotype.Service;

import java.util.UUID;
@Service("urlUuidKeyGenerationService")
public class UrlUuidKeyGenerationService implements UrlKeyGenerationService{
    @Override
    public String generateKey(String input, int length) {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        return uuidString.substring(0, Math.min(length, uuidString.length()));
    }
}
