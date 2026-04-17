package com.url.shortner.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Url {
    @Id
    private Long id;
    private String url;
    private String shortUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
