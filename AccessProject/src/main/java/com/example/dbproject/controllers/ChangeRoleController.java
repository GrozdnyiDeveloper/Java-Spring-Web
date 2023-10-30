package com.example.dbproject.controllers;

import com.example.dbproject.model.Userr;
import com.example.dbproject.model.roleEnum;
import com.example.dbproject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ChangeRoleController {
    private final userRepository UserRepository;

    @Autowired
    public ChangeRoleController(userRepository userRepository){
        this.UserRepository = userRepository;
    }

    @GetMapping("/changeRole")
    public String loadPage(Model model){
        model.addAttribute("users", UserRepository.findAll());
        model.addAttribute("roles", roleEnum.values());
        return "change";
    }

    @PostMapping("/changeRole")
    public String changeRole(@RequestParam(value = "username") String username, @RequestParam(value = "role") String role){
        Userr user = UserRepository.findByUsername(username);
        user.getRoles().clear();
        user.getRoles().add(roleEnum.valueOf(role));
        UserRepository.save(user);
        return "redirect:/changeRole";
    }
}
