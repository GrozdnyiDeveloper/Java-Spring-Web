package com.example.dbproject.repos;

import com.example.dbproject.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface categoryRepository extends CrudRepository<Category,Integer> {
    List<Category> findByName(String name);
}
