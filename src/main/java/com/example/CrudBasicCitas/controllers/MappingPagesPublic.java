package com.example.CrudBasicCitas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MappingPagesPublic {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/Inicio")
    public String inicio(HttpSession session){
        
        if (session.getAttribute("clienteId") == null) {
            return "redirect:/login";
        }
        return "redirect:/inicioUser";
    }

    @GetMapping("/about")
    public String acercade() {
        return "about"; 
    }
}
