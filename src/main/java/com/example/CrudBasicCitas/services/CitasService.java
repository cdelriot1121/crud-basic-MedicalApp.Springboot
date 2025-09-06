package com.example.CrudBasicCitas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CrudBasicCitas.entities.Citas;
import com.example.CrudBasicCitas.entities.Cliente;
import com.example.CrudBasicCitas.repository.CitasRepository;
import com.example.CrudBasicCitas.repository.ClienteRepository;


@Service
public class CitasService {

    @Autowired
    private CitasRepository citasRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Citas> obtenerTodasLasCitas(){
        return citasRepository.findAll();
    }

    public Citas registrarCitas(Citas cita){
        return citasRepository.save(cita);
    }

    public List<Citas> obtenerCitasPorCliente(Cliente cliente){
        return citasRepository.findByCliente(cliente);
    }

    public void eliminarCita(Long id){
        citasRepository.deleteById(id);
    }

    public Optional<Citas> obtenerCitasPorId(Long id){
        return citasRepository.findById(id);
    }

    public Citas actualizarCita(Long id, Citas citaActualizada){
        return citasRepository.findById(id).map(cita -> {
            cita.setCliente(citaActualizada.getCliente());
            cita.setAsunto(citaActualizada.getAsunto());
            cita.setDescripcion(citaActualizada.getDescripcion());
            cita.setTipoCita(citaActualizada.getTipoCita());
            cita.setFecha_cita(citaActualizada.getFecha_cita());
            cita.setDoctor(citaActualizada.getDoctor());
            return citasRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("esta ID de cita no fue encontrada: " + id));

    }

    public List<Citas> obtenerCitasPorClienteId(Long clienteId){
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if(cliente.isPresent()){
            return citasRepository.findByCliente(cliente.get());
        }
        return new ArrayList<>();
    }

}
