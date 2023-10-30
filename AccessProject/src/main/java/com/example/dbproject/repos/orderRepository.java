package com.example.dbproject.repos;

import com.example.dbproject.model.Event;
import com.example.dbproject.model.Orderr;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface orderRepository extends CrudRepository<Orderr, Integer> {
    List<Orderr> findByNumber(int number);
    List<Orderr> findByDate(Date date);
    List<Orderr> findByPayment(boolean payment);
}
