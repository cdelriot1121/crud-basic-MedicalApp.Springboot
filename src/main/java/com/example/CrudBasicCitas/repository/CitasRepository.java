package com.example.CrudBasicCitas.repository;

import com.example.CrudBasicCitas.entities.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CitasRepository extends JpaRepository <Citas, Long> {
}
