package com.example.demo.modules.produtos.repository;

import com.example.demo.modules.produtos.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ProdutoRepositoryTest {

    @Autowired
    private ProductRepository produtoRepository;

    @Test
    public void save_deveSalvar_quandoChamadoMetodoSave() {
        var save = produtoRepository.save(umProduto());
        assertThat(save)
                .extracting(Product::getName, Product::getDescription)
                .contains("name 1", "desc");
        assertThat(save.getId()).isNotNull();
    }

    private Product umProduto() {
        return Product.builder()
                .name("name 1")
                .description("desc")
                .price(new BigDecimal("120.00"))
                .build();
    }
}
