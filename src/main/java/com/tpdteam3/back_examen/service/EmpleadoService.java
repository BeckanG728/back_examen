package com.tpdteam3.back_examen.service;

import com.tpdteam3.back_examen.entity.EmpleadoGeronimo;
import com.tpdteam3.back_examen.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Obtener todos los empleados
    public List<EmpleadoGeronimo> obtenerTodosLosEmpleados() {
        return empleadoRepository.findAll();
    }

    // Obtener empleado por ID
    public Optional<EmpleadoGeronimo> obtenerEmpleadoPorId(Integer id) {
        return empleadoRepository.findById(id);
    }

    // Crear nuevo empleado
    public EmpleadoGeronimo crearEmpleado(EmpleadoGeronimo empleado) {
        // Validar que no exista un empleado con el mismo código si se proporciona
        if (empleado.getCodEmp() != null &&
            empleadoRepository.existsByCodEmp(empleado.getCodEmp())) {
            throw new RuntimeException("Ya existe un empleado con el código: " + empleado.getCodEmp());
        }
        return empleadoRepository.save(empleado);
    }

    // Actualizar empleado existente
    public EmpleadoGeronimo actualizarEmpleado(Integer id, EmpleadoGeronimo empleadoActualizado) {
        return empleadoRepository.findById(id)
                .map(empleado -> {
                    empleado.setNombEmp(empleadoActualizado.getNombEmp());
                    empleado.setAppaEmp(empleadoActualizado.getAppaEmp());
                    empleado.setApmaEmp(empleadoActualizado.getApmaEmp());
                    empleado.setClavEmp(empleadoActualizado.getClavEmp());
                    empleado.setFechNaciEmp(empleadoActualizado.getFechNaciEmp());
                    empleado.setPesoEmp(empleadoActualizado.getPesoEmp());
                    return empleadoRepository.save(empleado);
                })
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + id));
    }

    // Eliminar empleado
    public void eliminarEmpleado(Integer id) {
        if (!empleadoRepository.existsById(id)) {
            throw new RuntimeException("Empleado no encontrado con id: " + id);
        }
        empleadoRepository.deleteById(id);
    }

    // Método para login (autenticación)
    public Optional<EmpleadoGeronimo> autenticarEmpleado(Integer codEmp, String clavEmp) {
        return empleadoRepository.findByCodEmpAndClavEmp(codEmp, clavEmp);
    }

    // Verificar si existe un empleado
    public boolean existeEmpleado(Integer codEmp) {
        return empleadoRepository.existsByCodEmp(codEmp);
    }
}