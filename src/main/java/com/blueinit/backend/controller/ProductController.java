package com.blueinit.backend.controller;

import com.blueinit.backend.model.Product;
import com.blueinit.backend.services.ProductService;
import com.blueinit.backend.transactions.ProductTransactions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductTransactions productTransactions;

    public ProductController(ProductService productService, ProductTransactions productTransactions) {
        this.productService = productService;
        this.productTransactions = productTransactions;
    }

    @GetMapping
    public List<Product> getProducts() {
        log.info("Getting all products");
        return this.productService.findAll();
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category) {
        log.info("Getting all products by category: {}", category);
        return this.productService.findByCategory(category);
    }

    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable("name") String name) {
        log.info("Getting product by name: {}", name);
        return this.productService.findByName(name);
    }

    @GetMapping("/count")
    public long countProducts() {
        log.info("Counting all products");
        return this.productService.count();
    }

    @GetMapping("/count/{category}")
    public long countProductsByCategory(@PathVariable("category") String category) {
        log.info("Counting all products by category: {}", category);
        return this.productService.countByCategory(category);
    }

    @DeleteMapping("/category/{category}")
    public void deleteProductsByCategory( @PathVariable("category") String category) {
        log.info("Deleting all products by category: {}", category);
        this.productTransactions.deleteAllByCategory(category);
    }

    @DeleteMapping("/name/{name}")
    public void deleteProductByName( @PathVariable("name") String name) {
        log.info("Deleting product by name: {}", name);
        this.productTransactions.deleteByName(name);
    }

    @DeleteMapping
    public void deleteAllProducts() {
        log.info("Deleting all products");
        this.productTransactions.deleteAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        log.info("Creating product: {}", product);
        try {
            return this.productTransactions.createProduct(product);
        } catch (Exception e) {
            log.error("Error creating product: {}", e.getMessage());
            return null;
        }
    }

    @PostMapping("/batch")
    public List<Product> createProducts(@RequestBody List<Product> products) {
        log.info("Creating products: {}", products);
        try {
            return this.productTransactions.createProducts(products);
        } catch (Exception e) {
            log.error("Error creating products: {}", e.getMessage());
            return null;
        }
    }

    @PatchMapping ("/name/{name}")
    public Product updateProductByName(@PathVariable("name") String name, @RequestBody Map<String, Object> product) {
        log.info("Updating product: {}", product);
        try {
            return this.productTransactions.updateProductByName(name, product);
        } catch (Exception e) {
            log.error("Error updating product: {}", e.getMessage());
            return null;
        }
    }

    @PatchMapping("/category/{category}")
    public List<Product> updateProductsByCategory(@PathVariable("category") String category, @RequestBody String newCategory) {
        log.info("Updating product: {}", newCategory);
        try {
            return this.productTransactions.updateProductsCategoryByCategory(category, newCategory);
        } catch (Exception e) {
            log.error("Error updating product: {}", e.getMessage());
            return null;
        }
    }


}
