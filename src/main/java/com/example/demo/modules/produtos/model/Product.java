package com.example.demo.modules.produtos.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "PRODUCT")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_PRODUTO", sequenceName = "SEQ_PRODUTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PRODUTO")
    private Integer id;

    @Column(name = "NAME", length = 100)
    @NotNull
    private String name;

    @Column(name = "DESCRIPTION", length = 255)
    @NotNull
    private String description;

    @Column(name = "PRICE")
    @Min(value = 0)
    @NotNull
    private BigDecimal price;
}
