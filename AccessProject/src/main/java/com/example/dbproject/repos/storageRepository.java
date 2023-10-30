package com.example.dbproject.repos;

import com.example.dbproject.model.Event;
import com.example.dbproject.model.Storage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface storageRepository extends CrudRepository<Storage, Integer> {
    List<Storage> findByCapacity(int capacity);
    List<Storage> findByAddress(String address);
}
