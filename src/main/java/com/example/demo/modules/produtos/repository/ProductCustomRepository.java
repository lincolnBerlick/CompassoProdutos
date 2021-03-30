package com.example.demo.modules.produtos.repository;

import com.example.demo.modules.produtos.model.Product;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ProductCustomRepository {

     List<Product> findAll(Predicate predicate);

}
