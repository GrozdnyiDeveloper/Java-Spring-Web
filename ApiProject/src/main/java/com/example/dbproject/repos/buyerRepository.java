package com.example.dbproject.repos;

import com.example.dbproject.model.Buyer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface buyerRepository extends CrudRepository<Buyer,Integer> {
    List<Buyer> findByEmail(String email);
    List<Buyer> findByPhone(String phone);
    List<Buyer> findByPassword(String password);
    List<Buyer> findByDiscountCardNumber(int number);
    List<Buyer> findByDiscountCardDiscount(int discount);
}
