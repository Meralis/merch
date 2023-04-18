package org.klim.istock.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.klim.istock.DTO.CategoryDTO;
import org.klim.istock.entity.Product;
import org.klim.istock.repository.ProductRepository;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    static private ProductRepository repository;
    private ProductService service;

    @BeforeAll
    static void beforeAll() {
        List<Product> PRODUCT_LIST = Arrays.asList(
                buildProduct("Кіт.Годування.Консерви"),
                buildProduct("Кіт.Годування.Сухий корм"),
                buildProduct("Кіт.Гігієна.Наповнувач"),
                buildProduct("Кіт.Гігієна.Шампунь"),
                buildProduct("Собака.Годування.Консерви"),
                buildProduct("Собака.Годування.Сухий корм"),
                buildProduct("Собака.Гігієна.Наповнувач"),
                buildProduct("Собака.Гігієна.Шампунь"),
                buildProduct("Собака.Годування.Консерви")
        );
        repository = mock(ProductRepository.class);
        when(repository.findAll()).thenReturn(PRODUCT_LIST);
    }

    static private Product buildProduct(String category) {
        Product product = new Product();
        product.setCategory(category);
        return product;
    }

    @BeforeEach
    void beforeEach() {
        service = new ProductService(repository);
    }

    @Test
    void getCategories() {

        CategoryDTO category = service.getCategories();

        assertEquals("ROOT", category.getName());
    }
}