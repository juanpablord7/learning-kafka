package com.miempresa.serviceorder.dto.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderKafka {
    private Long id;

    private Long productId;

    private Long quantity;

    private Long price;
}
