package com.example.dbproject.repos;

import com.example.dbproject.model.Concurrency;
import org.springframework.data.repository.CrudRepository;

public interface concurrencyRepository extends CrudRepository<Concurrency,Integer> {

}
