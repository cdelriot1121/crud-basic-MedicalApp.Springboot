package com.example.CrudBasicCitas.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MappingPagesPublic {
    @GetMapping("/")
    public String index(){
        return "index";
    }


}
