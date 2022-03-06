package com.hunguyen.spring_app.repository;

import com.hunguyen.spring_app.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
