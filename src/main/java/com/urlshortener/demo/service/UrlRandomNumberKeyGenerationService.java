package com.urlshortener.demo.service;

import com.urlshortener.demo.repository.UrlModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service("urlRandomNumberKeyGenerationService")
public class UrlRandomNumberKeyGenerationService implements UrlKeyGenerationService {


    @Autowired
    private UrlModelRepository urlModelRepository;
    private static final int NUMBER_OF_TRIES = 20;
    @Override
    public String generateKey(String input, int length) {
        Random random = new Random();

        for(int i = 0; i < NUMBER_OF_TRIES; i++){
            String generatedKey = String.valueOf((Math.abs(random.nextLong())));

            generatedKey = generatedKey.substring(0, Math.min(length, generatedKey.length()));

            boolean keyExists = urlModelRepository.existsByKey(generatedKey);

            if (!keyExists) {
                return generatedKey;
            }
        }
        return null;
    }
}

