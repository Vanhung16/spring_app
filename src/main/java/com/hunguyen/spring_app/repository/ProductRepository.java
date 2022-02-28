package com.hunguyen.spring_app.repository;

import com.hunguyen.spring_app.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
