package org.klim.istock.service;

import org.klim.istock.entity.Product;
import org.klim.istock.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @Transactional
    public Product find(int id) {
        return productRepository.getReferenceById(id);
    }

    @Transactional
    public List<Product> findByCategoryLike(String category) {
        return productRepository.findByCategoryLike("%" + category + "%");
    }

    @Transactional
    public List<Product> searchProduct(Optional<String> searchText) {
        return searchText.isEmpty()
                ? productRepository.findAll()
                : productRepository.findByTitleLikeIgnoreCase("%" + searchText.get() + "%");
    }

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
