package com.example.CrudBasicCitas.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.CrudBasicCitas.entities.Cliente;
import com.example.CrudBasicCitas.services.CitasService;
import com.example.CrudBasicCitas.services.ClienteService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CitasService citasService;

    @GetMapping("/login")
    public String mostrarLogin(HttpSession session) {
        
        if (session.getAttribute("clienteId") != null) {
            return "redirect:/inicioUser";
        }
        return "login";
    }

    @GetMapping("/register")
    public String mostrarRegistro(HttpSession session) {
        
        if (session.getAttribute("clienteId") != null) {
            return "redirect:/inicioUser";
        }
        return "register";
    }
    
    @GetMapping("/inicioUser")
    public String mostrarInicioUser(HttpSession session, Model model) {
        // Verificar si hay sesión activa
        Long clienteId = (Long) session.getAttribute("clienteId");
        if (clienteId == null) {
            return "redirect:/login";
        }
        
        
        Cliente cliente = clienteService.obtenerClientePorId(clienteId);
        model.addAttribute("cliente", cliente);
        
        return "inicioUser";
    }

    @PostMapping("/loginProcess")
    public String loginProcess(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Optional<Cliente> cliente = clienteService.autenticarCliente(email, password);
        if (cliente.isPresent()) {
            
            session.setAttribute("clienteId", cliente.get().getId());
            session.setAttribute("clienteNombre", cliente.get().getNombre());
            session.setAttribute("clienteEmail", cliente.get().getEmail());
            
            return "redirect:/inicioUser";
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
                                  @RequestParam String direccion, RedirectAttributes redirectAttributes) {

        try {
            // verificar si el usuario ya pues existe, mediante autenticacion
            if (clienteService.existeClientePorEmail(email)) {
                return "redirect:/register?error=emailExists";
            }

            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setNombre(nombre);
            nuevoCliente.setEmail(email);
            nuevoCliente.setPassword(password);
            nuevoCliente.setIdentificacion(identificacion);
            nuevoCliente.setFecha_nacimiento(fecha_nacimiento);
            nuevoCliente.setGenero(genero);
            nuevoCliente.setCelular(celular);
            nuevoCliente.setDireccion(direccion);
            nuevoCliente.setEstado(Cliente.Estado.ACTIVO);

            clienteService.registrarCliente(nuevoCliente);

            return "redirect:/login";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error en el registro:" + e.getMessage());
            return "/register";

        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}