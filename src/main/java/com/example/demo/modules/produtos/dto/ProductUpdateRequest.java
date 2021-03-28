package com.example.demo.modules.produtos.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class ProductUpdateRequest {

    @NotEmpty(message = "Nome do produto precisa ser informado")
    private String name;
    @NotEmpty(message = "Descrição do produto precisa ser informado")
    private String description;
    @NotNull(message = "Valor do produto precisa ser informado")
    private BigDecimal price;
}
