package org.klim.istock.repository;

import org.klim.istock.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
