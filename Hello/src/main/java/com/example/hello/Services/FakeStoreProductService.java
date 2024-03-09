package com.example.hello.Services;

import com.example.hello.DTO.FakeStoreProductDto;
import com.example.hello.Model.Category;
import com.example.hello.Model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto ele: fakeStoreProductDtos) {
            Product product = new Product();
            product.setId(ele.getId());
            product.setTitle(ele.getTitle());
            product.setPrice(ele.getPrice());
            product.setImageUrl(ele.getImage());
            product.setDescription(ele.getDescription());
            Category category = new Category();
            category.setName(ele.getCategory());
            product.setCategory(category);
            products.add(product);
        }
        System.out.println("All products fetched successfully!!!");
        return products;
    }

    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        System.out.println("Product deleted successfully!!!");
    }

    public Product getSingleProduct(Long id) {

        FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());

        System.out.println("Product fetched successfully!!!");
        return product;
    }

    public List<Product> getProductByCategory(String categoryN) {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + categoryN,
                FakeStoreProductDto[].class
        );
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto ele : fakeStoreProductDtos) {
            Product product = new Product();
            product.setId(ele.getId());
            product.setTitle(ele.getTitle());
            product.setImageUrl(ele.getImage());
            product.setPrice(ele.getPrice());
            product.setDescription(ele.getDescription());
            Category category = new Category();
            category.setName(ele.getCategory());
            product.setCategory(category);
            products.add(product);
        }
        System.out.println("All products in " + categoryN + " category fetched successfully!!!");
        return products;
    }

    public List<String> getCategory() {
        String[] categories = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                String[].class
        );
        List<String> categoryList = new ArrayList<>();
        for(String category : categories) {
            categoryList.add(category);
        }
        System.out.println("All categories fetched successfully!!!");
        return categoryList;

    }

    @Override
    public void createProduct(Product product) {
        FakeStoreProductDto storeProduct = new FakeStoreProductDto();
        storeProduct.setId(product.getId());
        storeProduct.setTitle(product.getTitle());
        storeProduct.setPrice(product.getPrice());
        storeProduct.setImage(product.getImageUrl());
        storeProduct.setDescription(product.getDescription());
        storeProduct.setCategory(product.getCategory().getName());

        restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                storeProduct,
                FakeStoreProductDto.class
        );
        System.out.println("Product created successfully!!!");
    }

    public void updateProduct(Product product) {
        FakeStoreProductDto storeProduct = new FakeStoreProductDto();
        storeProduct.setId(product.getId());
        storeProduct.setTitle(product.getTitle());
        storeProduct.setPrice(product.getPrice());
        storeProduct.setImage(product.getImageUrl());
        storeProduct.setDescription(product.getDescription());
        storeProduct.setCategory(product.getCategory().getName());

        restTemplate.put(
                "https://fakestoreapi.com/products/" + product.getId(),
                storeProduct
        );
        System.out.println("Product updated successfully!!!");
    }
}
