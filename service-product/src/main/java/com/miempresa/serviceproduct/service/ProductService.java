package com.miempresa.serviceproduct.service;

import com.miempresa.serviceproduct.dto.ProductPatchRequest;
import com.miempresa.serviceproduct.dto.ProductRequest;
import com.miempresa.serviceproduct.model.Product;
import com.miempresa.serviceproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    // Get All
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Don't Found any Product whit that Id: " + id));

    }

    public List<Product> findListProductById(List<Long> ids) {
        return productRepository.findAllByIdIn(ids);
    }

    public Product createProduct(ProductRequest request) {
        System.out.println("Request product to be saved: " + request.toString());

        Long stock;
        if(request.getStock() != null){
            stock = request.getStock();
        }else{
            stock = 0L;
        }

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .category(request.getCategory())
                .image(request.getImage())
                .stock(stock)
                .build();

        System.out.println("Product to save: " + product.toString());

        return productRepository.save(product);
    }

    public Product updateProduct(Long id,ProductPatchRequest request) {
        System.out.println("Request product to be saved: " + request.toString());

        Product actualProduct = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Don't Found any Product whit that Id: " + id));

        if (request.getName() != null) {
            actualProduct.setName(request.getName());
        }
        if (request.getCategory() != null) {
            actualProduct.setCategory(request.getCategory());
        }
        if (request.getImage() != null) {
            actualProduct.setImage(request.getImage());
        }
        if (request.getPrice() != null) {
            actualProduct.setPrice(request.getPrice());
        }
        if (request.getStock() != null) {
            actualProduct.setStock(request.getStock());
        }

        System.out.println("Product to save: " + actualProduct);

        return productRepository.save(actualProduct);
    }

}
