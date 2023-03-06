package org.klim.istock.service;

import org.klim.istock.DTO.ProductDTO;
import org.klim.istock.entity.Product;
import org.klim.istock.repository.ProductRepository;
import org.klim.istock.util.ModelMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapperUtil modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapperUtil modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(this::toDto).collect(toList());
    }

    private ProductDTO toDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
}
