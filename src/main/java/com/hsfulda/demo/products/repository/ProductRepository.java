package com.hsfulda.demo.products.repository;

import com.hsfulda.demo.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(name = "Product.findByColor")
    List<Product> findByColor(@Param("color") String color);
}
