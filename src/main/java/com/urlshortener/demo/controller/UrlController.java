package com.urlshortener.demo.controller;

import com.urlshortener.demo.model.UrlModel;
import com.urlshortener.demo.repository.UrlModelRepository;
import com.urlshortener.demo.service.UrlKeyGenerationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/url")
public class UrlController {
    @Autowired
    private UrlModelRepository urlModelRepository;
    @Autowired
    private UrlKeyGenerationService urlKeyGenerationService;



    @PostMapping("/add")
    public String addUrl(@RequestBody UrlModel urlModel) {

        UrlModel existingUrlModel = urlModelRepository.findByOriginalUrl(urlModel.getOriginalUrl());

        if (existingUrlModel != null) {
            return "Shortened URL: " + existingUrlModel.getShortenedUrl();
        }

        String key = urlKeyGenerationService.generateHashedKey(urlModel.getOriginalUrl(), 8);

        String protocol = urlModel.getOriginalUrl().startsWith("https") ? "https" : "http";

        String shortenedUrl = protocol + "://localhost:8080/api/url/" + key;

        urlModel.setShortenedUrl(shortenedUrl);
        urlModel.setKey(key);

        urlModelRepository.save(urlModel);

        return "Shortened URL: " + shortenedUrl;
    }
    @GetMapping("/{key}")
    public void redirectToOriginalUrl(@PathVariable String key, HttpServletResponse response) throws IOException {


            UrlModel urlModel = urlModelRepository.findByKey(key);

            if (urlModel != null) {
                String originalUrl = urlModel.getOriginalUrl();
                response.setStatus(HttpServletResponse.SC_FOUND);
                response.setHeader("Location", urlModel.getOriginalUrl());
            } else {
                System.out.println("Key not found in the database: " + key);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }

}



