package com.example.urlshortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.urlshortener.dto.CreateShortUrlRequest;
import com.example.urlshortener.dto.ShortUrlResponse;
import com.example.urlshortener.dto.StatsResponse;
import com.example.urlshortener.dto.UpdateShortUrlRequest;
import com.example.urlshortener.service.ShortUrlService;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shorten")
public class ShortUrlController {

    private final ShortUrlService service;

    public ShortUrlController(ShortUrlService service) {
        this.service = service;
    }

    @Operation(
        summary = "Create a shortened URL",
        description = "Creates a short URL from a long URL"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShortUrlResponse create(
            @Valid @RequestBody CreateShortUrlRequest request
    ) {

        return service.createShortUrl(request);
    }

    @Operation(
        summary = "Retrieve a shortened URL",
        description = "Returns the original URL and metadata for a short code"
    )
    @GetMapping("/{shortCode}")
    public ShortUrlResponse get(
            @PathVariable String shortCode
    ) {

        return service.getByShortCode(shortCode);
    }


    @Operation(
        summary = "Update a shortened URL",
        description = "Updates the original URL associated with a short code"
    )
    @PutMapping("/{shortCode}")
    public ShortUrlResponse update(
            @PathVariable String shortCode,
            @Valid @RequestBody UpdateShortUrlRequest request
    ) {

        return service.updateShortUrl(
                shortCode,
                request
        );
    }

    @Operation(
        summary = "Delete a shortened URL",
        description = "Permanently deletes a short URL"
)
    @DeleteMapping("/{shortCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable String shortCode
    ) {

        service.deleteShortUrl(shortCode);
    }

    @Operation(
        summary = "Get URL statistics",
        description = "Returns access statistics for a short URL"
    )
    @GetMapping("/{shortCode}/stats")
    public StatsResponse stats(
            @PathVariable String shortCode
    ) {

        return service.getStats(shortCode);
    }
    
}