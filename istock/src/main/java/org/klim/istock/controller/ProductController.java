package org.klim.istock.controller;

import org.klim.istock.DTO.CategoryDTO;
import org.klim.istock.DTO.ProductDTO;
import org.klim.istock.entity.Product;
import org.klim.istock.service.ProductService;
import org.klim.istock.util.ModelMapperUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
//@CrossOrigin(origins = " https://istock-app.herokuapp.com/")
@CrossOrigin(origins = "http://localhost:8080/")
public class ProductController {
    private final ProductService productService;
    private final ModelMapperUtil modelMapper;

    public ProductController(ProductService productService, ModelMapperUtil modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/product")
    @CrossOrigin(origins = "http://localhost:8080/")
    public List<ProductDTO> getAllProducts() {
        return productService.findAll().stream().map(this::toDto).collect(toList());
    }

    @GetMapping("/product/{id}")
    @CrossOrigin(origins = "http://localhost:8080/")
    public ProductDTO singleProduct(
            @PathVariable(value = "id") int productId) {
        return toDto(productService.find(productId));
    }

    @PostMapping("/product/search")
    @CrossOrigin(origins = "http://localhost:8080/")
    public List<ProductDTO> searchProduct(@RequestBody Optional<String> searchText) {
        return productService.searchProduct(searchText).stream().map(this::toDto).collect(toList());
    }

    @PostMapping("/product/category")
    @CrossOrigin(origins = "http://localhost:8080/")
    public List<ProductDTO> findByCategory(@RequestBody String category) {
        return productService.findByCategoryLike(category).stream().map(this::toDto).collect(toList());
    }

    @GetMapping("/product/categoryTree")
    @CrossOrigin(origins = "http://localhost:8080/")
    public CategoryDTO categoryDTO() {
        return productService.getCategories();
    }

    private ProductDTO toDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
}

