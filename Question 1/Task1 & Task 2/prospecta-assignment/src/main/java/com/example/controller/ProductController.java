package com.example.controller;

import com.example.DTO.ProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final RestTemplate restTemplate;

    public ProductController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    // GET API: Fetch products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String category) {
        String url = "https://fakestoreapi.com/products/category/" + category;
        try {
            ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(url, ProductDTO[].class);
            List<ProductDTO> products = Arrays.asList(response.getBody());
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    // POST API: Add a new product
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        String url = "https://fakestoreapi.com/products";
        try {
            ProductDTO response = restTemplate.postForObject(url, productDTO, ProductDTO.class);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
