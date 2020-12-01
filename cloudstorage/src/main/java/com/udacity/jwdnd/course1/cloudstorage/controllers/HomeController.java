package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller

public class HomeController {
    @GetMapping("/home")
    public String goHome(){
        System.out.println("In Home Controller");
        return "home";
    }

    @GetMapping("/goToFiles")
    public String goToFiles(){
        System.out.println("going to files page");
        return "files";

    }
    @GetMapping("/goToNotes")
    public String goToNotes() {
        System.out.println("going to notes page");
        return "notes";
    }
    @GetMapping("/goToCredentials")
    public String goToCredentials() {
        System.out.println("going to Credentials page");
        return "register";
    }
    }
