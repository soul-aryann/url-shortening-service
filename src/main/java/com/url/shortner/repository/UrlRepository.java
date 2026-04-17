package com.url.shortner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url.shortner.model.Url;


public interface UrlRepository extends JpaRepository<Url, Long> {
    List<Url> findByShortUrl(String shortUrl);
}
