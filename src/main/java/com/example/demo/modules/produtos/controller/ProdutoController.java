package com.example.demo.modules.produtos.controller;

import com.example.demo.modules.produtos.dto.ProductFiltros;
import com.example.demo.modules.produtos.dto.ProductResponseDto;
import com.example.demo.modules.produtos.dto.ProductSaveRequest;
import com.example.demo.modules.produtos.dto.ProductUpdateRequest;
import com.example.demo.modules.produtos.model.Product;
import com.example.demo.modules.produtos.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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


    @ApiOperation(value = "Criar novo produto", code = 201, produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "201 se criado com sucesso", response = ProductResponseDto.class),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto save(@RequestBody @Valid ProductSaveRequest produto) {
       return produtoService.save(produto);
    }

    @ApiOperation(value = "Atualizar produto por id", produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se atualizado com sucesso", response = ProductResponseDto.class),
    })
    @PutMapping("{productId}")
    public ProductResponseDto update(@PathVariable Integer productId, @RequestBody @Valid ProductUpdateRequest produto) {
        return produtoService.update(productId, produto);

    }

    @ApiOperation(value = "Busca por id do produto", produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se encontrado", response = ProductResponseDto.class),
    })
    @GetMapping("{productId}")
    public ProductResponseDto getById(@PathVariable Integer productId) {
        return produtoService.getById(productId);
    }

    @ApiOperation(value = "Listar todos produtos", produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listado com sucesso", response = ProductResponseDto.class),
    })
    @GetMapping
    public List<ProductResponseDto> findAll() {
        return produtoService.findAll();
    }

    @ApiOperation(value = "Buscar produto com filtro", produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se encontrado", response = Product.class),
    })
    @GetMapping("search")
    public List<Product> findAllFiltros(ProductFiltros productFiltros) {
        return produtoService.findAllFiltros(productFiltros);
    }

    @ApiOperation(value = "Buscar produto com paginação", produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se encontrado", response = Product.class),
    })
    @GetMapping("search/page")
    public Page<Product> findAllFiltrosPage(ProductFiltros productFiltros, Pageable pageable) {
        return produtoService.findAllFiltrosPage(pageable, productFiltros);
    }

    @ApiOperation(value = "Atualizar produto", produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se deletado com sucesso"),
    })
    @DeleteMapping("{productId}")
    public void deleteById(@PathVariable Integer productId) {
        produtoService.deleteById(productId);
    }
}
