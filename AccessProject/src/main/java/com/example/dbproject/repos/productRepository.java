package com.example.dbproject.repos;

import com.example.dbproject.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface productRepository extends CrudRepository<Product,Integer> {
    List<Product> findByNumber(String number);
    List<Product> findByName(String name);
    List<Product> findByPrice(double price);
    List<Product> findByAmount(int amount);
    List<Product> findByEventName(String name);
    List<Product> findBySupplierName(String name);
    List<Product> findByStorageAddress(String address);
}
