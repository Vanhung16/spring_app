package com.hunguyen.spring_app.service;

import java.util.List;
import java.util.Optional;

import com.hunguyen.spring_app.domain.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {


    List<Category> findByNameContaining(String name);

    Page<Category> findByNameContaining(String name, Pageable pageable);

    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);

    <S extends Category> S save(S entity);

    Optional<Category> findById(Long aLong);

    boolean existsById(Long aLong);

    void deleteById(Long aLong);

    void delete(Category entity);

    <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);
}
