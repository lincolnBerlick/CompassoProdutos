package com.example.demo.modules.produtos.dto;

import com.example.demo.modules.produtos.predicate.ProdutoPredicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductFiltros {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal min_price;
    private BigDecimal max_price;
    private BigDecimal lowerPrice;
    private String q;

    public ProdutoPredicate toPredicate() {
        return new ProdutoPredicate()
                .comNome(name)
                .comDescricao(description)
                .comId(id)
                .comValor(price)
                .comValorEntre(min_price, max_price)
                .comValorMenorQue(lowerPrice)
                .comDescricaoOuNome(q);
    }

}
