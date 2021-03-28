package com.example.demo.modules.produtos.dto;

import com.example.demo.modules.produtos.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;

    public static ProductResponseDto of(Product produto) {
        var produtoResponse = new ProductResponseDto();
        BeanUtils.copyProperties(produto, produtoResponse);
        return produtoResponse;
    }
}
