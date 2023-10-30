package com.example.dbproject.repos;

import com.example.dbproject.model.Buyer;
import com.example.dbproject.model.Cart;
import com.example.dbproject.model.Compliance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface cartRepository extends CrudRepository<Cart,Integer> {
    List<Cart> findByAmount(int amount);
    List<Cart> findByBuyerEmail(String email);
    List<Cart> findByComplianceProductNumber(String number);
}
