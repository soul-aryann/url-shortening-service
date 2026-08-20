package com.example.urlshortener.service;

import com.example.urlshortener.dto.CreateShortUrlRequest;
import com.example.urlshortener.dto.ShortUrlResponse;
import com.example.urlshortener.dto.UpdateShortUrlRequest;
import com.example.urlshortener.exception.ShortUrlNotFoundException;
import com.example.urlshortener.model.ShortUrl;
import com.example.urlshortener.repository.ShortUrlRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShortUrlServiceTest {

    @Mock
    private ShortUrlRepository repository;

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private ShortUrlService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldCreateShortUrl() {

        CreateShortUrlRequest request =
                new CreateShortUrlRequest();

        request.setUrl("https://google.com");

        when(repository.save(any(ShortUrl.class)))
                .thenAnswer(invocation -> {

                    ShortUrl url = invocation.getArgument(0);

                    url.setId("123");

                    return url;
                });

        ShortUrlResponse response =
                service.createShortUrl(request);

        assertNotNull(response);

        assertEquals(
                "https://google.com",
                response.getUrl()
        );

        assertNotNull(response.getShortCode());

        assertEquals(
                7,
                response.getShortCode().length()
        );

        verify(repository, times(1))
                .save(any(ShortUrl.class));
    }


    @Test
    void shouldGetShortUrl() {

        ShortUrl shortUrl = createShortUrl();

        when(repository.findByShortCode("abc1234"))
                .thenReturn(Optional.of(shortUrl));

        ShortUrlResponse response =
                service.getByShortCode("abc1234");

        assertEquals(
                "https://google.com",
                response.getUrl()
        );

        assertEquals(
                "abc1234",
                response.getShortCode()
        );
    }


    @Test
    void shouldThrowExceptionWhenShortUrlDoesNotExist() {

        when(repository.findByShortCode("wrong"))
                .thenReturn(Optional.empty());

        assertThrows(
                ShortUrlNotFoundException.class,
                () -> service.getByShortCode("wrong")
        );
    }


    @Test
    void shouldUpdateShortUrl() {

        ShortUrl shortUrl = createShortUrl();

        when(repository.findByShortCode("abc1234"))
                .thenReturn(Optional.of(shortUrl));

        when(repository.save(any(ShortUrl.class)))
                .thenAnswer(invocation ->
                        invocation.getArgument(0)
                );

        UpdateShortUrlRequest request =
                new UpdateShortUrlRequest();

        request.setUrl("https://youtube.com");

        ShortUrlResponse response =
                service.updateShortUrl(
                        "abc1234",
                        request
                );

        assertEquals(
                "https://youtube.com",
                response.getUrl()
        );

        assertEquals(
                "abc1234",
                response.getShortCode()
        );

        verify(repository).save(shortUrl);
    }


    @Test
    void shouldDeleteShortUrl() {

        ShortUrl shortUrl = createShortUrl();

        when(repository.findByShortCode("abc1234"))
                .thenReturn(Optional.of(shortUrl));

        service.deleteShortUrl("abc1234");

        verify(repository).delete(shortUrl);
    }


    private ShortUrl createShortUrl() {

        ShortUrl shortUrl = new ShortUrl();

        shortUrl.setId("123");
        shortUrl.setUrl("https://google.com");
        shortUrl.setShortCode("abc1234");
        shortUrl.setAccessCount(0);

        return shortUrl;
    }
}