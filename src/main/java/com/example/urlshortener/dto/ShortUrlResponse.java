package com.example.urlshortener.dto;

import java.time.Instant;

import com.example.urlshortener.model.ShortUrl;

public class ShortUrlResponse {

    private String id;
    private String url;
    private String shortCode;
    private Instant createdAt;
    private Instant updatedAt;
    private long accessCount;

    public ShortUrlResponse() {
    }

    public ShortUrlResponse(
            String id,
            String url,
            String shortCode,
            Instant createdAt,
            Instant updatedAt,
            long accessCount
    ) {
        this.id = id;
        this.url = url;
        this.shortCode = shortCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.accessCount = accessCount;
    }

    public static ShortUrlResponse from(ShortUrl shortUrl) {

        return new ShortUrlResponse(
                shortUrl.getId(),
                shortUrl.getUrl(),
                shortUrl.getShortCode(),
                shortUrl.getCreatedAt(),
                shortUrl.getUpdatedAt(),
                shortUrl.getAccessCount()
        );
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getShortCode() {
        return shortCode;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public long getAccessCount() {
        return accessCount;
    }
}