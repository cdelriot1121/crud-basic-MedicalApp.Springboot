package com.example.CrudBasicCitas.services;

import com.example.CrudBasicCitas.entities.Citas;
import com.example.CrudBasicCitas.entities.Cliente;
import com.example.CrudBasicCitas.repository.CitasRepository;
import com.example.CrudBasicCitas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CitasService {

    @Autowired
    private CitasRepository citasRepository;

    @Autowired
    private ClienteRepository clienteRepository;


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





}
