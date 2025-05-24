package com.investmentsportal.portal.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/marketdata")
public class StockController {

    private final RestTemplate restTemplate;

    public StockController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${markets-tack.api.key}")
    private String apiKey;

    @GetMapping("/quote/{symbol}")
    public ResponseEntity<?> getStockData(@PathVariable String symbol) {
        String url = String.format("http://api.marketstack.com/v1/eod?access_key=%s&symbols=%s", apiKey, symbol);
        System.out.println("CHECK " + url);
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            System.out.println(response.getBody());
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error fetching stock data: " + e.getMessage());
        }
    }
}
