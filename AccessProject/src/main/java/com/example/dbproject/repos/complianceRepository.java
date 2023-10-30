package com.example.dbproject.repos;

import com.example.dbproject.model.Category;
import com.example.dbproject.model.Compliance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface complianceRepository extends CrudRepository<Compliance,Integer> {

}
