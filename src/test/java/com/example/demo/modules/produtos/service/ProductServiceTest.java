package com.example.demo.modules.produtos.service;

import com.example.demo.modules.comum.exceptions.ValidacaoException;
import com.example.demo.modules.produtos.dto.ProductResponseDto;
import com.example.demo.modules.produtos.dto.ProductSaveRequest;
import com.example.demo.modules.produtos.dto.ProductUpdateRequest;
import com.example.demo.modules.produtos.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:/produtoService.sql"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductServiceTest {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void validarFindById_deveRetornarProdutoPorId_quandoIdValido() {
        var produtoSalvo = produtoService.save(umProdutoSaveDto());
        assertThat(produtoService.getById(produtoSalvo.getId()))
                .extracting(ProductResponseDto::getName, ProductResponseDto::getDescription)
                .contains("name 1", "desc");
    }

    @Test
    public void exception_deveRetornarValidacaoException_quandoIdNaoEncontrado() {
        assertThatExceptionOfType(ValidacaoException.class)
                .isThrownBy(() -> produtoService.getById(1000))
                .withMessage("Não foi possível encontrar o produto");
    }

    @Test
    public void delete_deveDeletarProdutoNaoDeveLancarException_quandoReceberId() {
        produtoService.deleteById(200);
    }

    @Test
    public void update_deveDeletarProduto_quandoReceberId() {
        var produtoUpdate = ProductUpdateRequest.builder()
                .description("nova descricao")
                .name("novo nome")
                .price(new BigDecimal(125.00))
                .build();

        produtoService.update(300, produtoUpdate);
        assertThat(produtoService.getById(300))
                .extracting(ProductResponseDto::getId,
                        ProductResponseDto::getDescription,
                        ProductResponseDto::getPrice,
                        ProductResponseDto::getName)
                .contains(300, "nova descricao", new BigDecimal("125.00"), "novo nome");

    }

    @Test
    public void findById_deveRetornar_dtoProdutoRequest_quandoFindById() {
        var resultado = produtoService.getById(100);
        assertThat(produtoService.getById(100))
                .extracting(ProductResponseDto::getId, ProductResponseDto::getPrice)
                .contains(100, new BigDecimal("120.00"));

    }

    private ProductSaveRequest umProdutoSaveDto() {
        return ProductSaveRequest.builder()
                .name("name 1")
                .description("desc")
                .price(new BigDecimal("120.00"))
                .build();
    }
}
