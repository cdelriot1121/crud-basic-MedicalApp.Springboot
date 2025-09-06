package com.example.CrudBasicCitas.controllers;


import com.example.CrudBasicCitas.entities.Citas;
import com.example.CrudBasicCitas.entities.Cliente;
import com.example.CrudBasicCitas.services.CitasService;
import com.example.CrudBasicCitas.services.ClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/citas")
public class CitasController {
    @Autowired
    private CitasService citasService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/nuevaCita")
    public String mostrarFormNuevaCita(HttpSession session, Model model){
        Long clienteId = (Long) session.getAttribute("ClienteId");

        if (clienteId == null){
            return "redirect:/login";
        }

        Cliente cliente = clienteService.obtenerClientePorId(clienteId);
        model.addAttribute("cliente", cliente);
        return "registrarCitas";
    }

    @PostMapping("/registrarCita")
    public String registrarCita(@RequestParam String asunto,
                                @RequestParam String descripcion,
                                @RequestParam String tipoCita,
                                @RequestParam String fecha_cita,
                                @RequestParam String doctor,
                                HttpSession session, RedirectAttributes redirectAttributes){
        Long clienteId = (Long) session.getAttribute("clienteId");
        if (clienteId == null){
            return "redirect:/login";
        }
        try {
            Cliente cliente = clienteService.obtenerClientePorId(clienteId);

            Citas nuevaCita = new Citas();
            nuevaCita.setCliente(cliente);
            nuevaCita.setAsunto(asunto);
            nuevaCita.setDescripcion(descripcion);
            nuevaCita.setTipoCita(tipoCita);
            nuevaCita.setFecha_cita(fecha_cita);
            nuevaCita.setDoctor(doctor);

            citasService.registrarCitas(nuevaCita);

            redirectAttributes.addFlashAttribute("mensaje", "cita correctamente registrada" );

            return "redirect:/citas/misCitas";

        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Ocurrio un error al registrar las citas ): mensaje: " + e.getMessage());
            return "redirect:/citas/nuevaCita";
        }

    }

    @GetMapping("/misCitas")
    public String mostrarMisCitas(HttpSession session, Model model){
        Long clienteId = (Long) session.getAttribute("clienteId");
        if (clienteId == null){
            return "redirect:/login";
        }

        Cliente cliente = clienteService.obtenerClientePorId(clienteId);
        List<Citas> citas = citasService.obtenerCitasPorCliente(cliente);

        model.addAttribute("citas", citas);
        model.addAttribute("cliente", cliente);

        return "listaCitas";

    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCita(@PathVariable Long id, HttpSession session, Model model) {

        Long clienteId = (Long) session.getAttribute("clienteId");
        if (clienteId == null) {
            return "redirect:/login";
        }

        Optional<Citas> cita = citasService.obtenerCitasPorId(id);
        if (cita.isPresent()) {

            if (cita.get().getCliente().getId().equals(clienteId)) {
                model.addAttribute("cita", cita.get());
                return "editarCita";
            }
        }

        return "redirect:/citas/misCitas";
    }


    @PostMapping("/actualizar/{id}")
    public String actualizarCita(
            @PathVariable Long id,
            @RequestParam String asunto,
            @RequestParam String descripcion,
            @RequestParam String tipoCita,
            @RequestParam String fecha_cita,
            @RequestParam String doctor,
            HttpSession session,
            RedirectAttributes redirectAttributes) {


        Long clienteId = (Long) session.getAttribute("clienteId");
        if (clienteId == null) {
            return "redirect:/login";
        }

        Optional<Citas> citaExistente = citasService.obtenerCitasPorId(id);
        if (citaExistente.isPresent() && citaExistente.get().getCliente().getId().equals(clienteId)) {
            Citas cita = citaExistente.get();
            cita.setAsunto(asunto);
            cita.setDescripcion(descripcion);
            cita.setTipoCita(tipoCita);
            cita.setFecha_cita(fecha_cita);
            cita.setDoctor(doctor);

            citasService.actualizarCita(id, cita);

            redirectAttributes.addFlashAttribute("mensaje", "Cita actualizada exitosamente");
        }

        return "redirect:/citas/misCitas";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {

        Long clienteId = (Long) session.getAttribute("clienteId");
        if (clienteId == null) {
            return "redirect:/login";
        }

        Optional<Citas> cita = citasService.obtenerCitasPorId(id);
        if (cita.isPresent() && cita.get().getCliente().getId().equals(clienteId)) {
            citasService.eliminarCita(id);
            redirectAttributes.addFlashAttribute("mensaje", "Cita eliminada exitosamente");
        }

        return "redirect:/citas/misCitas";
    }

}
