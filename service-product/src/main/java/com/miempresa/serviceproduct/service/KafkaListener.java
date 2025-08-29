package com.miempresa.serviceproduct.service;

import com.miempresa.serviceproduct.dto.ProductPatchRequest;
import com.miempresa.serviceproduct.dto.client.OrderKafka;
import com.miempresa.serviceproduct.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener {
    @Autowired
    private ProductService productService;

    @org.springframework.kafka.annotation.KafkaListener(topics = "order", groupId = "order-group")
    public void listenGroupFoo(OrderKafka orderKafka) {
        System.out.println("Received Message in group 'order-group': " + orderKafka);

        Product product = productService.findProductById(orderKafka.getProductId());

        ProductPatchRequest productUpdated = ProductPatchRequest.builder()
                .stock(product.getStock() - orderKafka.getQuantity())
                .build();

        productService.updateProduct(product.getId(), productUpdated);
    }
}

