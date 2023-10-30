package com.example.dbproject.repos;

import com.example.dbproject.model.Event;
import com.example.dbproject.model.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface supplierRepository extends CrudRepository<Supplier,Integer> {
    List<Supplier> findByName(String name);
}
