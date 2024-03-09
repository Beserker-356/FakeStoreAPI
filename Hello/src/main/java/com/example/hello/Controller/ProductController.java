package com.example.hello.Controller;

import com.example.hello.Model.Category;
import com.example.hello.Model.Product;
import com.example.hello.Services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello world";
    }

    @GetMapping("/sing")
    public String sayShavaShava() {
        return "Shava Shava";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @PostMapping("/products")
    public void createProduct(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @GetMapping("/products/categories")
    public List<String> getAllCategories() {
        return productService.getCategory();
    }

    @GetMapping("/products/category/{categoryN}")
    public List<Product> getInCategory(@PathVariable("categoryN") String categoryName) {
        return productService.getProductByCategory(categoryName);
    }

    @PutMapping("/products")
    public void updateProduct(@RequestBody Product p){
        productService.updateProduct(p);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

}
