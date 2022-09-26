package com.dh.gatewayservice.Controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @CircuitBreaker(name = "moviesService")
    @GetMapping("/movies")
    public ResponseEntity<String> moviesFallback() {
        return new ResponseEntity<>("Servidor de filmes no disponible.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @CircuitBreaker(name = "seriesService")
    @GetMapping("/series")
    public ResponseEntity<String> serieFallback() {
        return new ResponseEntity<>("Servidor de series no disponible.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @CircuitBreaker(name = "catalogsService")
    @GetMapping("/catalogs")
    public ResponseEntity<String> catalogFallback() {
        return new ResponseEntity<>("Servidor de catálogos no disponible.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
