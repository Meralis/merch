package org.klim.istock.controller;

import org.klim.istock.DTO.ProductDTO;
import org.klim.istock.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @CrossOrigin
    public List<ProductDTO> getAllProducts() {
        return productService.findAll();
    }
}
//        new HttpHeaders().setCacheControl("no-cache, no-store, must-revalidate");