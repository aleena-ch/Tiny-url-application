package com.example.demo.service;

import com.example.demo.entity.Url;
import com.example.demo.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository repository;

    private final String BASE_URL = "http:localhost:8080/";

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public String shortenUrl(String originalUrl) {
        String shortCode = generateShortCode();
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortCode(shortCode);
        repository.save(url);
        return BASE_URL + shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        Optional<Url> url = repository.findByShortCode(shortCode);
        return url.map(Url::getOriginalUrl).orElse(null);
    }

    private String generateShortCode() {
        return UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 8);
    }
}
