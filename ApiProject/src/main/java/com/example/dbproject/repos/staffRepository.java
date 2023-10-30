package com.example.dbproject.repos;

import com.example.dbproject.model.Orderr;
import com.example.dbproject.model.Staff;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface staffRepository extends CrudRepository<Staff, Integer> {
    List<Staff> findByEmail(String email);
    List<Staff> findByLastName(String last_name);
    List<Staff> findByName(String name);
    List<Staff> findByMiddleName(String middle_name);
    List<Staff> findByGender(String gender);
    List<Staff> findByPassword(String password);
}
