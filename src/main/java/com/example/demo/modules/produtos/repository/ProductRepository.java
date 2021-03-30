package com.example.demo.modules.produtos.repository;

import com.example.demo.modules.produtos.model.Product;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>,
        QuerydslPredicateExecutor<Product>, ProductCustomRepository {

    Optional<Product> findById(Integer produtoId);

    List<Product> findAll();

}
