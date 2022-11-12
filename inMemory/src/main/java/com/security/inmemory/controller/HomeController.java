package com.security.inmemory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHomePage(){
        return "homePage";
    }

    @GetMapping("/welcome")
    public String getWelcomePage(){
        return "welcomePage";
    }
    @GetMapping("/emp")
    public String getEmployeePage(){
        return "empPage";
    }
    @GetMapping("/admin")
    public String getAdminPage(){
        return "adminPage";
    }
    @GetMapping("/mgr")
    public String getMgrPage(){
        return "mgrPage";
    }

    @GetMapping("/common")
    public String getCommonPage(){
        return "commonPage";
    }
    @GetMapping("/accessDenied")
    public String getAccessDeniedPage(){
        return "accessDeniedPage";
    }
}
