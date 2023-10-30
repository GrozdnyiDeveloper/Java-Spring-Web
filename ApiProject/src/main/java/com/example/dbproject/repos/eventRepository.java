package com.example.dbproject.repos;

import com.example.dbproject.model.Event;
import com.example.dbproject.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface eventRepository extends CrudRepository<Event,Integer> {
    List<Event> findByName(String name);
}
