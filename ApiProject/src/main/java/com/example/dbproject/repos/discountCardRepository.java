package com.example.dbproject.repos;

import com.example.dbproject.model.Discount_Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface discountCardRepository extends CrudRepository<Discount_Card,Integer> {
    List<Discount_Card> findByNumber(int number);
    List<Discount_Card> findByDiscount(int discount);
    List<Discount_Card> findByOwnerEmail(String email);
}
