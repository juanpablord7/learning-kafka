package com.miempresa.serviceorder.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotNull(message = "The Product Id can't be empty")
    @PositiveOrZero
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @NotNull(message = "The Quantity can't be empty")
    @PositiveOrZero
    @Column(nullable = false)
    private Long quantity;

    @NotNull(message = "The Price can't be empty")
    @PositiveOrZero
    @Column(nullable = false)
    private Long price;

}
