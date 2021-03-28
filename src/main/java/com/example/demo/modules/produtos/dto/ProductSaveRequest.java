package com.example.demo.modules.produtos.dto;

import com.example.demo.modules.produtos.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaveRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private BigDecimal price;

    public static Product of(ProductSaveRequest productSaveRequest) {
        var produto = new Product();
        BeanUtils.copyProperties(productSaveRequest, produto);
        return produto;
    }
}
