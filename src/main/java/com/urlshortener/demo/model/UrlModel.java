package com.urlshortener.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "url_model", uniqueConstraints = @UniqueConstraint(columnNames = "short_key", name = "uk_short_key"))
public class UrlModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "short_key", unique = true, nullable = false)
    private String key;

    @Column(name = "shortened_url", unique = true, nullable = false)
    @Transient
    private String shortenedUrl;
    @Column(name = "original_url", nullable = false)
    private String originalUrl;
    @Column(name = "key_generation_method", nullable = false)
    private String keyGenerationMethod;

    public UrlModel(String key, String shortenedUrl, String originalUrl) {
        this.key = key;
        this.shortenedUrl = shortenedUrl;
        this.originalUrl = originalUrl;
    }

    public UrlModel() {

    }

    public Long getId() {
        return id;
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public void setShortenedUrl(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getKeyGenerationMethod() {
        return keyGenerationMethod;
    }

    public void setKeyGenerationMethod(String keyGenerationMethod) {
        this.keyGenerationMethod = keyGenerationMethod;
    }
}
