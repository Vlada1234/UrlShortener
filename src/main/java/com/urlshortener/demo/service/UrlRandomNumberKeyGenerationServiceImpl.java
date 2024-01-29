package com.urlshortener.demo.service;

import com.urlshortener.demo.repository.UrlModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service("urlRandomNumberKeyGenerationServiceImpl")
public class UrlRandomNumberKeyGenerationServiceImpl implements UrlKeyGenerationService {


    @Autowired
    private UrlModelRepository urlModelRepository;
    @Override
    public String generateKey(String input, int length) {
        Random random = new Random();

        while (true) {
            String generatedKey = String.valueOf((Math.abs(random.nextLong())));

            generatedKey = generatedKey.substring(0, Math.min(length, generatedKey.length()));

            boolean keyExists = urlModelRepository.existsByKey(generatedKey);

            if (!keyExists) {
                return generatedKey;
            }
        }

    }
}

