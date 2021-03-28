package com.example.demo.modules.produtos.service;

import com.example.demo.modules.comum.exceptions.ValidacaoException;
import com.example.demo.modules.produtos.dto.ProductFiltros;
import com.example.demo.modules.produtos.dto.ProductResponseDto;
import com.example.demo.modules.produtos.dto.ProductSaveRequest;
import com.example.demo.modules.produtos.dto.ProductUpdateRequest;
import com.example.demo.modules.produtos.model.Product;
import com.example.demo.modules.produtos.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProductRepository produtoRepository;

    public ProductResponseDto save(ProductSaveRequest produto) {
        var produtoParaSalvar = ProductSaveRequest.of(produto);
        return ProductResponseDto.of(produtoRepository.save(produtoParaSalvar));
    }

    public ProductResponseDto update(Integer produtoId, ProductUpdateRequest produto) {
       var produtoParaAtualizar = findByIdOrElseThrow(produtoId);
        BeanUtils.copyProperties(produto, produtoParaAtualizar,"id");
        return ProductResponseDto.of(produtoRepository.save(produtoParaAtualizar));
    }

    public ProductResponseDto getById(Integer productId) {
        return ProductResponseDto.of(findByIdOrElseThrow(productId));
    }

    private Product findByIdOrElseThrow(Integer produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ValidacaoException("Não foi possível encontrar o produto"));

    }

    public List<ProductResponseDto> findAll() {
        return produtoRepository.findAll()
                .stream()
                .map(ProductResponseDto::of)
                .collect(Collectors.toList());
    }

    public Page<Product> findAllFiltros(Pageable pageable, ProductFiltros productFiltros) {
        return produtoRepository.findAll(productFiltros.toPredicate().build(), pageable);
    }

    public void deleteById(Integer productId) {
        findByIdOrElseThrow(productId);
        produtoRepository.deleteById(productId);
    }
}
