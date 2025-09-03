package com.example.CrudBasicCitas.repository;

import com.example.CrudBasicCitas.entities.Citas;
import com.example.CrudBasicCitas.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CitasRepository extends JpaRepository <Citas, Long> {
    List<Citas> findByCliente(Cliente cliente);
}
