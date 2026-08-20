package com.example.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.urlshortener.model.ShortUrl;

public interface ShortUrlRepository
        extends MongoRepository<ShortUrl, String> {

    Optional<ShortUrl> findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);
    
}