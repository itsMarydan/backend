package com.blueinit.backend.services;

import com.blueinit.backend.model.Product;
import com.blueinit.backend.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        List<Product> products = this.productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        products.forEach(productList::add);
        productList.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        return productList;
    }

    public Product findByName(String name) {
        return this.productRepository.findByName(name);
    }

    public List<Product> findByCategory(String category) {
        List<Product> products = this.productRepository.findByCategory(category);
        List<Product> productList = new ArrayList<>();
        products.forEach(productList::add);
        productList.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        return productList;
    }

    public long countByCategory(String category) {
        return this.productRepository.countByCategory(category);
    }

    public long count() {
        return this.productRepository.count();
    }


}
