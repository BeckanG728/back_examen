package com.tpdteam3.back_examen.repository;

import com.tpdteam3.back_examen.entity.EmpleadoGeronimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoGeronimo, Integer> {

    // Método para buscar empleado por código y clave (para login)
    Optional<EmpleadoGeronimo> findByCodEmpAndClavEmp(Integer codEmp, String clavEmp);

    // Método para verificar si existe un empleado con un código específico
    boolean existsByCodEmp(Integer codEmp);
}