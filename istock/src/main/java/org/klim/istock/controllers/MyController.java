package org.klim.istock.controllers;

import org.klim.istock.Product;
import org.klim.istock.services.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
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
    public List<Product> index(Model model) {
        return productService.findAll();
    }
}