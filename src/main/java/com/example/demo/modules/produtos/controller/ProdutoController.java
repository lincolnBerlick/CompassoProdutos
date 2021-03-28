package com.example.demo.modules.produtos.controller;

import com.example.demo.modules.produtos.dto.ProductFiltros;
import com.example.demo.modules.produtos.dto.ProductResponseDto;
import com.example.demo.modules.produtos.dto.ProductSaveRequest;
import com.example.demo.modules.produtos.dto.ProductUpdateRequest;
import com.example.demo.modules.produtos.model.Product;
import com.example.demo.modules.produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/products")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto save(@RequestBody @Valid ProductSaveRequest produto) {
       return produtoService.save(produto);
    }

    @PutMapping("{productId}")
    public ProductResponseDto update(@PathVariable Integer produtoId, @Valid ProductUpdateRequest produto) {
        return produtoService.update(produtoId, produto);

    }

    @GetMapping("{productId}")
    public ProductResponseDto getById(@PathVariable Integer productId) {
        return produtoService.getById(productId);
    }

    @GetMapping
    public List<ProductResponseDto> findAll() {
        return produtoService.findAll();
    }

    @GetMapping("search")
    public Page<Product> findAllFiltros(ProductFiltros productFiltros, Pageable pageable) {
        return produtoService.findAllFiltros(pageable, productFiltros);
    }

    @DeleteMapping({"productId"})
    public void deleteById(@PathVariable Integer productId) {
        produtoService.deleteById(productId);
    }
}
