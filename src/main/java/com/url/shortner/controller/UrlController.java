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

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api")
public class UrlController {
    public Url url;

    @Autowired
    private UrlService urlService;

    @Autowired
    ObjectMapper mapper;


    @GetMapping("/{url}")
    public ObjectNode getShortUrl(@PathVariable String url) {
        String shortUrl = urlService.createShortUrl(url);

        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("shortUrl", )
    }

    @PostMapping("/")
    public Url shortenUrl(@RequestBody Url url) {
        return url;
    }

}
