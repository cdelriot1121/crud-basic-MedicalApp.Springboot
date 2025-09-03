package com.example.CrudBasicCitas.services;

import com.example.CrudBasicCitas.entities.Cliente;
import com.example.CrudBasicCitas.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    // aqui simplemente son los metodos basicos de CRUD
    public Cliente registrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setEmail(clienteActualizado.getEmail());
            cliente.setPassword(clienteActualizado.getPassword());
            cliente.setIdentificacion(clienteActualizado.getIdentificacion());
            cliente.setFecha_nacimiento(clienteActualizado.getFecha_nacimiento());
            cliente.setGenero(clienteActualizado.getGenero());
            cliente.setCelular(clienteActualizado.getCelular());
            cliente.setDireccion(clienteActualizado.getDireccion());
            cliente.setEstado(clienteActualizado.getEstado());
            cliente.setCitas(clienteActualizado.getCitas());
            return clienteRepository.save(cliente);
        }).orElse(null);
    }



}
