package com.example.demo.modules.produtos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {

    @NotEmpty(message = "Nome do produto precisa ser informado")
    private String name;
    @NotEmpty(message = "Descrição do produto precisa ser informado")
    private String description;
    @NotNull(message = "Valor do produto precisa ser informado")
    @Min(0)
    private BigDecimal price;
}
