package com.blueinit.backend.repository;


import com.blueinit.backend.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByName(String name);

    List<Product> findByCategory(String category);

    public long countByCategory(String category);
    public long count();

    public void deleteAllByCategory(String category);
}
