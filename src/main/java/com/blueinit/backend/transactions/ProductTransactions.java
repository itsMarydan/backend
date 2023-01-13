package com.blueinit.backend.transactions;

import com.blueinit.backend.exceptions.NotFoundException;
import com.blueinit.backend.model.Product;
import com.blueinit.backend.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class ProductTransactions {

    private final ProductRepository productRepository;

    public ProductTransactions(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void deleteAll() {
        log.info("Deleting all products");
        this.productRepository.deleteAll();
        log.info("All products deleted");
    }

    public void deleteByName(String name) {
        log.info("Deleting product by name: {}", name);
        Product product = this.productRepository.findByName(name);
        if (product == null) {
            throw new NotFoundException("Product " + name  +" not found");
        }
        this.productRepository.delete(product);
        log.info("Product deleted");
    }

    public void deleteAllByCategory(String category) {
        log.info("Deleting all products by category: {}", category);
        this.productRepository.deleteAllByCategory(category);
        log.info("All products in category" + category + " deleted");
    }

    public Product updateProductByName(String name, Map<String, Object> updates) {
        log.info("Updating product with name: {} and updates: {}", name, updates);
        Product product = productRepository.findByName(name);
        if (product == null) {
            log.error("Product with name {} not found", name);
            throw new NotFoundException("Product with name " + name + " not found");
        }

        updates.forEach((field, value) -> {
            switch (field) {
                case "name" -> product.setName((String) value);
                case "description" -> product.setDescription((String) value);
                case "price" -> product.setPrice((BigDecimal) value);
                case "quantity" -> product.setQuantity((Integer) value);
                case "category" -> product.setCategory((String) value);
                default -> log.warn("Invalid field {} passed for update", field);
            }
        });
        productRepository.save(product);
        log.info("Product updated");
        return product;
    }

    public Product createProduct(Product product) {
        log.info("Creating product: {}", product);
        return productRepository.save(product);
    }

    public List<Product> createProducts(List<Product> products) {
        log.info("Creating products: {}", products);
        return productRepository.saveAll(products);
    }

    public List<Product> updateProductsCategoryByCategory(String category, String newCategory) {
        log.info("Updating products category: {}", category);
        List<Product> products = productRepository.findByCategory(category);
        products.forEach(product -> product.setCategory(newCategory));
        return productRepository.saveAll(products);
    }

}
