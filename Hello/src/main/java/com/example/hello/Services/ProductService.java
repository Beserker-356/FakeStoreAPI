package com.example.hello.Services;

import com.example.hello.Model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    void deleteProduct(Long id);

    Product getSingleProduct(Long id);

    List<Product> getProductByCategory(String categoryN);

    List<String> getCategory();

    void updateProduct(Product product);

    void createProduct(Product product);
}
