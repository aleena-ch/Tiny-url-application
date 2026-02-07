package com.example.demo.controller;

import com.example.demo.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;

@RestController
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam String url) {
        String shortUrl = service.shortenUrl(url);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortCode}")
    public RedirectView redirect(@PathVariable String shortCode) {
        String originalUrl = service.getOriginalUrl(shortCode);
        return new RedirectView(Objects.requireNonNullElse(originalUrl, "/error"));
    }
}
