package com.example.urlshortener.service;

import java.security.SecureRandom;
import java.time.Instant;

import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.urlshortener.dto.CreateShortUrlRequest;
import com.example.urlshortener.dto.ShortUrlResponse;
import com.example.urlshortener.dto.StatsResponse;
import com.example.urlshortener.dto.UpdateShortUrlRequest;
import com.example.urlshortener.exception.ShortUrlNotFoundException;
import com.example.urlshortener.model.ShortUrl;
import com.example.urlshortener.repository.ShortUrlRepository;

@Service
public class ShortUrlService {

    private final ShortUrlRepository repository;
    private final MongoTemplate mongoTemplate;

    private final SecureRandom random = new SecureRandom();

    public ShortUrlService(
            ShortUrlRepository repository,
            MongoTemplate mongoTemplate
    ) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }


    // CREATE
    public ShortUrlResponse createShortUrl(
            CreateShortUrlRequest request
    ) {

        String shortCode = generateShortCode();

        ShortUrl shortUrl = new ShortUrl();

        shortUrl.setUrl(request.getUrl());
        shortUrl.setShortCode(shortCode);

        Instant now = Instant.now();

        shortUrl.setCreatedAt(now);
        shortUrl.setUpdatedAt(now);
        shortUrl.setAccessCount(0);

        ShortUrl saved = repository.save(shortUrl);

        return ShortUrlResponse.from(saved);
    }


    // GET
    public ShortUrlResponse getByShortCode(
            String shortCode
    ) {

        ShortUrl shortUrl = repository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new ShortUrlNotFoundException(shortCode)
                );

        return ShortUrlResponse.from(shortUrl);
    }


    // UPDATE
    public ShortUrlResponse updateShortUrl(
            String shortCode,
            UpdateShortUrlRequest request
    ) {

        ShortUrl shortUrl = repository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new ShortUrlNotFoundException(shortCode)
                );

        shortUrl.setUrl(request.getUrl());
        shortUrl.setUpdatedAt(Instant.now());

        ShortUrl updated = repository.save(shortUrl);

        return ShortUrlResponse.from(updated);
    }


    // DELETE
    public void deleteShortUrl(
            String shortCode
    ) {

        ShortUrl shortUrl = repository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new ShortUrlNotFoundException(shortCode)
                );

        repository.delete(shortUrl);
    }


    // STATS
    public StatsResponse getStats(
            String shortCode
    ) {

        ShortUrl shortUrl = repository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new ShortUrlNotFoundException(shortCode)
                );

        return StatsResponse.from(shortUrl);
    }


    // REDIRECT + ATOMIC ACCESS COUNT
    public String accessShortUrl(
            String shortCode
    ) {

        Query query = new Query(
                where("shortCode").is(shortCode)
        );

        Update update = new Update()
                .inc("accessCount", 1);

        ShortUrl updatedUrl =
                mongoTemplate.findAndModify(
                        query,
                        update,
                        ShortUrl.class
                );

        if (updatedUrl == null) {
            throw new ShortUrlNotFoundException(shortCode);
        }

        return updatedUrl.getUrl();
    }


    // GENERATE SHORT CODE
    private String generateShortCode() {

        String characters =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "abcdefghijklmnopqrstuvwxyz" +
                "0123456789";

        StringBuilder code = new StringBuilder(7);

        for (int i = 0; i < 7; i++) {

            int index =
                    random.nextInt(characters.length());

            code.append(characters.charAt(index));
        }

        return code.toString();
    }
}