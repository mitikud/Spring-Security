package com.security.userdetail.controller;

import com.security.userdetail.entity.User;
import com.security.userdetail.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    //Go to registration Page
    @GetMapping("/register")
    public String register(){
        return "registerUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, Model model){
        Integer id = userService.saveUser(user);
        String message = "User '"+id+"' saved seccessfully !";
        model.addAttribute("msg", message);

        return "registerUser";
    }
}
