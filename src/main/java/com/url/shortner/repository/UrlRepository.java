package com.url.shortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url.shortner.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {

}
