package org.klim.istock.controllers;

import org.klim.istock.Product;
import org.klim.istock.services.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {
    private final ProductService productService;

    public MyController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    @CrossOrigin
    public List<Product> getAllProducts() {
//        new HttpHeaders().setCacheControl("no-cache, no-store, must-revalidate");

        return productService.findAll();
    }
}