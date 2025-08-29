package com.miempresa.serviceproduct.controller;

import com.miempresa.serviceproduct.dto.ProductRequest;
import com.miempresa.serviceproduct.model.Product;
import com.miempresa.serviceproduct.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    //Inyeccion de dependencias:
    private final ProductService productService;

    @Autowired
    //Constructor con inyección de dependencia
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Get Endpoint
    @GetMapping
    public ResponseEntity<List<Product>> getProductsByCategory(
                                                        @RequestParam(required = false) Long categoryId,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "20") int limit) {

        List<Product> products = productService.findProducts();

        return ResponseEntity.ok(products);
    }

    //Get Single Product By Id
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {

        Product product = productService.findProductById(id);

        return ResponseEntity.ok(product);
    }

    //Post endpoint
    @PostMapping
    public ResponseEntity<Product> postProduct(@Valid @RequestBody ProductRequest request){
        Product response = productService.createProduct(request);
        return ResponseEntity.ok(response);
    }

}
