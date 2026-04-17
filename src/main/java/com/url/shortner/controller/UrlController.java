package com.url.shortner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortner.model.Url;

@RestController

public class UrlController {
    public Url url;

    @GetMapping("/shorten/{url}")
    public String getShortUrl(@PathVariable String url) {
        return "shortUrl";
    }
    @PostMapping("/shorten")
    public Url shortenUrl(@RequestBody Url url) {
        return url;
    }

}
