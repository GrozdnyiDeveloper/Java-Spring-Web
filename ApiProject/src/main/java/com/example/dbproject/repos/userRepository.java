package com.example.dbproject.repos;

import com.example.dbproject.model.Userr;
import org.springframework.data.repository.CrudRepository;

public interface userRepository extends CrudRepository<Userr,Long> {
    Userr findByUsername(String username);
}
