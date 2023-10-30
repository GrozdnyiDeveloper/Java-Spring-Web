package com.example.dbproject.controllers;

import com.example.dbproject.model.Userr;
import com.example.dbproject.model.roleEnum;
import com.example.dbproject.repos.*;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ChangeRoleController {
    private final userRepository UserRepository;

    @Autowired
    public ChangeRoleController(userRepository userRepository){
        this.UserRepository = userRepository;
    }

    @PostMapping("/changeRole")
    public Userr changeRole(@RequestBody Model body){
        Userr user = UserRepository.findByUsername(body.getAttribute("username").toString());
        user.getRoles().clear();
        user.getRoles().add(roleEnum.valueOf(body.getAttribute("role").toString()));
        return UserRepository.save(user);
    }
}
