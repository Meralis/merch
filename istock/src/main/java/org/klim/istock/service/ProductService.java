package org.klim.istock.service;

import org.klim.istock.entity.Product;
import org.klim.istock.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product find(int id) {
        return productRepository.getReferenceById(id);
    }

    public List<Product> findByCategoryLike(String category) {
        return productRepository.findByCategoryLike("%" + category + "%");
    }

    public List<Product> findByTitleLikeIgnoreCase(String searchText) {
        return productRepository.findByTitleLikeIgnoreCase("%" + searchText + "%");
    }
}
