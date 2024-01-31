package com.urlshortener.demo.service;

import com.urlshortener.demo.model.UrlModel;
import com.urlshortener.demo.repository.UrlModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class DataInsertionRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInsertionRunner.class);

    @Autowired
    private DataInsertionScript dataInsertionScript;

    @Autowired
    private UrlModelRepository urlModelRepository;

    private static final boolean RUN_SCRIPT_CONDITION = false;

    @Override
    public void run(String... args) throws Exception {

        if (RUN_SCRIPT_CONDITION) {
            int numberOfRowsToInsert = 100000;

            for (int i = 0; i < numberOfRowsToInsert; i++) {
                UrlModel entity = dataInsertionScript.createEntity();
                try {
                    urlModelRepository.save(entity);
                    logger.info("Inserted entity with key: {}", entity.getKey());
                } catch (DataIntegrityViolationException e) {
                    logger.error("Error inserting entity with key: {}", entity.getKey(), e);
                    e.printStackTrace();
                }
            }
        }
    }
}

