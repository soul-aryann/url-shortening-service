package com.example.urlshortener.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

import com.example.urlshortener.service.ShortUrlService;

@RestController
public class RedirectController {

    private final ShortUrlService service;

    public RedirectController(ShortUrlService service) {
        this.service = service;
    }
    @Operation(
        summary = "Redirect to original URL",
        description = "Redirects the user to the original URL and increments the access count"
        )
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode
    ) {

        String originalUrl =
                service.accessShortUrl(shortCode);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(
                URI.create(originalUrl)
        );

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .headers(headers)
                .build();
    }
}