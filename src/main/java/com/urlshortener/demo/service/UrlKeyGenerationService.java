package com.urlshortener.demo.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Service
public interface UrlKeyGenerationService {

    public String generateKey(String input, int length);


}
