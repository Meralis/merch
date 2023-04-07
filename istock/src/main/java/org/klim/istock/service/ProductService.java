package org.klim.istock.service;

import org.klim.istock.DTO.CategoryDTO;
import org.klim.istock.entity.Product;
import org.klim.istock.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private CategoryDTO rootNode = new CategoryDTO("ROOT", null);

    @Transactional
    public Product find(int id) {
        return productRepository.getReferenceById(id);
    }

    @Transactional
    public List<Product> findByCategoryLike(String category) {
        category = category.contains("ROOT.") ? category.replace("ROOT.", "") : category;
        System.out.println(category);
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

    @PostConstruct
    public void postConstruct() {
        findAll().stream()
                .map(Product::getCategory)
                .sorted(Collections.reverseOrder())
                .distinct()
                .map(c -> c.split("\\."))
                .forEach(path -> addChildCategory(rootNode, path, 0));
    }

    public CategoryDTO getCategories() {
        return rootNode;
    }

    private boolean addChildCategory(CategoryDTO node, String[] path, int index) {
        if (node != rootNode && !Objects.equals(node.getName(), path[index])) {
            return false;
        }
        final int newIndex = (node == rootNode) ? 0 : index + 1;
        boolean isAddedToExistedChild = node.getChildren().stream()
                .map(n -> addChildCategory(n, path, newIndex))
                .filter(b -> b)
                .findFirst()
                .orElse(false);
        if (!isAddedToExistedChild) {
            addSubCategories(node, path, newIndex);
        }
        return true;
    }

    private void addSubCategories(CategoryDTO node, String[] path, int index) {
        if (index < path.length) {
            CategoryDTO newNode = new CategoryDTO(path[index], node);
            node.getChildren().add(newNode);
            addSubCategories(newNode, path, index + 1);
        }
    }
}
