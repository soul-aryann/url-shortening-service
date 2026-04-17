package com.url.shortner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.url.shortner.model.Url;
import com.url.shortner.repository.UrlRepository;

public class UrlServiceImpl {
    @Autowired
    private UrlRepository urlRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_CODE_LENGTH = 8;

    @Transactional
    public Url createShortUrl(String url) {
        
    }
    public String getOriginalUrl(){
        return "code";
    }
    public boolean checkUniqueness(String code) {
        return true;
    }

}
