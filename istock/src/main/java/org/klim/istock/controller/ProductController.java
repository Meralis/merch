package org.klim.istock.controller;

import org.klim.istock.DTO.ProductDTO;
import org.klim.istock.entity.Product;
import org.klim.istock.service.ProductService;
import org.klim.istock.util.ModelMapperUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class ProductController {
    private final ProductService productService;
    private final ModelMapperUtil modelMapper;

    public ProductController(ProductService productService, ModelMapperUtil modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/product")
    @CrossOrigin
    public List<ProductDTO> getAllProducts() {
        return productService.findAll().stream().map(this::toDto).collect(toList());
    }

    @GetMapping("/product/{id}")
    @CrossOrigin
    public ProductDTO singleProduct(
            @PathVariable(value = "id") long productId) {
        return toDto(productService.find(productId));
    }

    private ProductDTO toDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
}
//        new HttpHeaders().setCacheControl("no-cache, no-store, must-revalidate");

