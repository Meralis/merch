package org.klim.istock.services;

import org.klim.istock.Product;
import org.klim.istock.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        List<Product> productItems = productRepository.findAll();
        productItems.forEach(product -> product.setImagePath("../src/components/static/" + product.getId() + ".jpg"));
        return productItems;
    }
}
