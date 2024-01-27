package com.urlshortener.demo.repository;

import com.urlshortener.demo.model.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlModelRepository extends JpaRepository<UrlModel, Long> {
    UrlModel findByOriginalUrl(String originalUrl);
    UrlModel findByKey(String key);



}
