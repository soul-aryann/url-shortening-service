package com.url.shortner.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Random;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.url.shortner.model.Url;
import com.url.shortner.repository.UrlRepository;

@Service
public class UrlService {
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_URL_LEN = 8;

    @Autowired
    private UrlRepository urlRepository;

    public Url getUrlByShortUrl(String shortUrl) throws RuntimeErrorException {
        try {
            return urlRepository.findByShortUrl(shortUrl).getFirst();
        } catch (NoSuchElementException e) {
            throw new RuntimeErrorException(new Error(), "This Short Url already exists");
        }
    }

    @Transactional
    public Url createShortUrl(String url) throws RuntimeErrorException {
        String shortUrl = generateShortUrl(url);
        
        // checking fir uniqueness of the generated short url
        if (!urlRepository.findByShortUrl(shortUrl).isEmpty()) {
            throw new RuntimeErrorException(new Error(), "This Short Url already exists");
        }

        Url saved_url = new Url();
        saved_url.setUrl(url);
        saved_url.setShortUrl(shortUrl);

        LocalDateTime now =LocalDateTime.now();
        saved_url.setCreatedAt(now);
        saved_url.setUpdatedAt(now);

        return urlRepository.save(saved_url);
    }


    public String generateShortUrl(String url){
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i<SHORT_URL_LEN ;i++){
            int index = rand.nextInt(CHARS.length());
            sb.append(CHARS.charAt(index));
        }
        return sb.toString();
    }

}
