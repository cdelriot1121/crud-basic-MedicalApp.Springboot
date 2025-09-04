package com.example.CrudBasicCitas.controllers;


import com.example.CrudBasicCitas.entities.Cliente;
import com.example.CrudBasicCitas.services.CitasService;
import com.example.CrudBasicCitas.services.ClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CitasService citasService;

    @GetMapping("/login")
    public String mostrarLogin(){
        return "login";
    }

    @GetMapping("/register")
    public String mostrarRegistro(){
        return "register";
    }

    @PostMapping("/loginProcess")
    public String loginProcess(@RequestParam String email, @RequestParam String password, HttpSession session){
        Optional<Cliente> cliente = clienteService.autenticarCliente(email, password);
        if (cliente.isPresent()){
            session.setAttribute("clienteId", cliente.get().getId());
            return "Inicio";
        }

        return "redirect:/login?error=true";
    }

    @PostMapping("/registerProcess")
    public String registerProcess(@RequestParam String nombre,
                                  @RequestParam String email,
                                  @RequestParam String password,
                                  @RequestParam String identificacion,
                                  @RequestParam String fecha_nacimiento,
                                  @RequestParam String genero,
                                  @RequestParam String celular,
                                  @RequestParam String direccion){

        // terminare la logica
        return null;
    }

}
