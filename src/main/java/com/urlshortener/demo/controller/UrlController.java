package com.urlshortener.demo.controller;

import ch.qos.logback.classic.Logger;
import com.urlshortener.demo.model.UrlModel;
import com.urlshortener.demo.repository.UrlModelRepository;
import com.urlshortener.demo.service.UrlKeyGenerationService;
import com.urlshortener.demo.service.UrlRandomNumberKeyGenerationService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
@RequestMapping("/api/url")
public class UrlController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(UrlController.class);
    @Autowired
    private UrlModelRepository urlModelRepository;
    @Autowired
    @Qualifier("urlUuidKeyGenerationService")
    private UrlKeyGenerationService uuidKeyGenerationService;
    @Autowired
    @Qualifier("urlHashedKeyGenerationService")
    private UrlKeyGenerationService hashedKeyGenerationService;
    @Autowired
    @Qualifier("urlRandomNumberKeyGenerationService")
    private UrlRandomNumberKeyGenerationService randomNumberKeyGenerationService;

    @GetMapping("/add-url")
    public String showAddUrlForm() {
        return "addUrl";
    }


    @PostMapping("/add")
    public String addUrl(@ModelAttribute UrlModel urlModel, Model model) {

        UrlModel existingUrlModel = urlModelRepository.findByOriginalUrl(urlModel.getOriginalUrl());

        if (existingUrlModel != null) {
            model.addAttribute("shortenedUrl", existingUrlModel.getShortenedUrl());
        } else {
            String key;

            if ("uuid".equals(urlModel.getKeyGenerationMethod())) {
                key = uuidKeyGenerationService.generateKey(urlModel.getOriginalUrl(), 8);
            }else if("randomNumber".equals(urlModel.getKeyGenerationMethod())) {
                key = randomNumberKeyGenerationService.generateKey(urlModel.getOriginalUrl(), 8);
            }else {
                key = hashedKeyGenerationService.generateKey(urlModel.getOriginalUrl(), 8);
            }

            String protocol = urlModel.getOriginalUrl().startsWith("https") ? "https" : "http";

            String shortenedUrl = protocol + "://localhost:8080/api/url/" + key;

            urlModel.setKey(key);

            urlModelRepository.save(urlModel);

            model.addAttribute("shortenedUrl", shortenedUrl);

        }
        return "addUrl";
    }
    @GetMapping("/{key}")
    public void redirectToOriginalUrl(@PathVariable String key, HttpServletResponse response) throws IOException {


            UrlModel urlModel = urlModelRepository.findByKey(key);

            if (urlModel != null) {
                String originalUrl = urlModel.getOriginalUrl();
                response.setStatus(HttpServletResponse.SC_FOUND);
                response.setHeader("Location", urlModel.getOriginalUrl());
            } else {
                logger.warn("Key not found in the database: {}", key);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }

}



