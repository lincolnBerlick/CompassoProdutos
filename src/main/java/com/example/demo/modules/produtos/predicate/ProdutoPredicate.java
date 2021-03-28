package com.example.demo.modules.produtos.predicate;

import static com.example.demo.modules.produtos.model.QProduct.product;
import com.querydsl.core.BooleanBuilder;

import java.math.BigDecimal;

import static org.springframework.util.ObjectUtils.isEmpty;

public class ProdutoPredicate {

    private BooleanBuilder builder;

    public ProdutoPredicate() {
        this.builder = new BooleanBuilder();
    }

    public BooleanBuilder build() {
        return this.builder;
    }

    public ProdutoPredicate comNome(String nome) {
        if (!isEmpty(nome)) {
          builder.and(product.name.likeIgnoreCase(nome));
        }
        return this;
    }

    public ProdutoPredicate comDescricao(String descricao) {
        if (!isEmpty(descricao)) {
           builder.and(product.description.likeIgnoreCase(descricao));
        }
        return this;
    }

    public ProdutoPredicate comId(Integer id) {
        if (!isEmpty(id)) {
           builder.and(product.id.eq(id));
        }
        return this;
    }

    public ProdutoPredicate comValor(BigDecimal valor) {
        if (!isEmpty(valor)) {
           builder.and(product.price.eq(valor));
        }
        return this;
    }

    public ProdutoPredicate comValorEntre(BigDecimal valorInicial, BigDecimal valorFinal) {
        if(!isEmpty(valorFinal) && !isEmpty(valorFinal)) {
           builder.and(product.price.between(valorInicial, valorFinal));
        }
        return this;
    }

    public ProdutoPredicate comValorMenorQue(BigDecimal valor) {
        if (!isEmpty(valor)) {
           builder.and(product.price.loe(valor));
        }
        return this;
    }

    public ProdutoPredicate comDescricaoOuNome(String q) {
        if (!isEmpty(q)) {
           builder.and(product.description.likeIgnoreCase(q)
                   .or(product.name.likeIgnoreCase(q)));
        }
        return this;
    }


}
