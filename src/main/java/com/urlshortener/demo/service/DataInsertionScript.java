package com.urlshortener.demo.service;
import com.urlshortener.demo.model.UrlModel;
import com.urlshortener.demo.repository.UrlModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DataInsertionScript {

    @Autowired
    @Qualifier("urlHashedKeyGenerationService")
    private UrlKeyGenerationService hashedKeyGenerationService;
    @Autowired
    private UrlModelRepository urlModelRepository;


    public UrlModel createEntity() {
        UrlModel entity = new UrlModel();

        entity.setOriginalUrl("http://example.com/original/" + System.currentTimeMillis() + "-" + UUID.randomUUID());
        entity.setKey(hashedKeyGenerationService.generateKey(entity.getOriginalUrl(), 8));
        entity.setKeyGenerationMethod("hashed");

        return entity;
    }



}
