package com.url.shortner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortner.model.Url;
import com.url.shortner.service.UrlService;


@RestController
@RequestMapping("/api")
public class UrlController {
    public Url url;

    @Autowired
    private UrlService urlService;

    @GetMapping("/{shortUrl}")
    public Url getShortUrl(@PathVariable String shortUrl) {
        return urlService.getUrlByShortUrl(shortUrl);
    }
    
    @PostMapping("/")
    public Url shortenUrl(@RequestBody String url) {
        Url saveUrl = urlService.createShortUrl(url);
        return saveUrl;
    }
}
