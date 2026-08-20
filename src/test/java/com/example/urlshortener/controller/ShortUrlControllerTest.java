package com.example.urlshortener.controller;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.urlshortener.dto.ShortUrlResponse;
import com.example.urlshortener.service.ShortUrlService;

@WebMvcTest(ShortUrlController.class)
class ShortUrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
        private ShortUrlService service;


    @Test
    void shouldCreateShortUrl() throws Exception {

        ShortUrlResponse response =
                new ShortUrlResponse(
                        "123",
                        "https://google.com",
                        "abc1234",
                        null,
                        null,
                        0
                );

        when(service.createShortUrl(any()))
                .thenReturn(response);

        mockMvc.perform(
                post("/shorten")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "url": "https://google.com"
                                }
                                """)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.url")
                .value("https://google.com"))
        .andExpect(jsonPath("$.shortCode")
                .value("abc1234"));
    }


    @Test
    void shouldRejectInvalidUrl() throws Exception {

        mockMvc.perform(
                post("/shorten")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "url": "google.com"
                                }
                                """)
        )
        .andExpect(status().isBadRequest());
    }
}