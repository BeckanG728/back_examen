package com.tpdteam3.back_examen.controller;

import com.tpdteam3.back_examen.dto.LoginRequest;
import com.tpdteam3.back_examen.dto.LoginResponse;
import com.tpdteam3.back_examen.entity.EmpleadoGeronimo;
import com.tpdteam3.back_examen.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empleado")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // Endpoint para login CON VALIDACIONES
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest loginRequest,
            BindingResult bindingResult) {

        // Validar errores de entrada
        if (bindingResult.hasErrors()) {
            String errores = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new LoginResponse(false, "Errores de validación: " + errores));
        }

        // Validación adicional: verificar que no sean valores nulos
        if (loginRequest.getCodEmp() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new LoginResponse(false, "El código de empleado no puede estar vacío"));
        }

        if (loginRequest.getClavEmp() == null || loginRequest.getClavEmp().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new LoginResponse(false, "La contraseña no puede estar vacía"));
        }

        try {
            Optional<EmpleadoGeronimo> empleado = empleadoService.autenticarEmpleado(
                    loginRequest.getCodEmp(),
                    loginRequest.getClavEmp()
            );

            if (empleado.isPresent()) {
                return ResponseEntity.ok(
                        new LoginResponse(true, "Inicio de sesión exitoso", empleado.get())
                );
            } else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new LoginResponse(false, "Código de empleado o contraseña incorrectos"));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponse(false, "Error en el servidor: " + e.getMessage()));
        }
    }

    // Listar todos los empleados
    @GetMapping
    public ResponseEntity<List<EmpleadoGeronimo>> listarEmpleados() {
        try {
            List<EmpleadoGeronimo> empleados = empleadoService.obtenerTodosLosEmpleados();
            return ResponseEntity.ok(empleados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity
                    .badRequest()
                    .body("El ID debe ser un número positivo");
        }

        try {
            Optional<EmpleadoGeronimo> empleado = empleadoService.obtenerEmpleadoPorId(id);
            return empleado
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .build());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener empleado: " + e.getMessage());
        }
    }

    // Crear nuevo empleado CON VALIDACIONES
    @PostMapping
    public ResponseEntity<?> crearEmpleado(
            @Valid @RequestBody EmpleadoGeronimo empleado,
            BindingResult bindingResult) {

        // Validar errores de entrada
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errores);
        }

        try {
            EmpleadoGeronimo nuevoEmpleado = empleadoService.crearEmpleado(empleado);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nuevoEmpleado);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al crear empleado: " + e.getMessage()));
        }
    }

    // Actualizar empleado existente CON VALIDACIONES
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(
            @PathVariable Integer id,
            @Valid @RequestBody EmpleadoGeronimo empleado,
            BindingResult bindingResult) {

        if (id == null || id <= 0) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "El ID debe ser un número positivo"));
        }

        // Validar errores de entrada
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errores);
        }

        try {
            EmpleadoGeronimo empleadoActualizado = empleadoService.actualizarEmpleado(id, empleado);
            return ResponseEntity.ok(empleadoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al actualizar empleado: " + e.getMessage()));
        }
    }

    // Eliminar empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "El ID debe ser un número positivo"));
        }

        try {
            empleadoService.eliminarEmpleado(id);
            return ResponseEntity.ok(Map.of("message", "Empleado eliminado exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al eliminar empleado: " + e.getMessage()));
        }
    }

    // Verificar si existe un empleado
    @GetMapping("/existe/{codEmp}")
    public ResponseEntity<?> existeEmpleado(@PathVariable Integer codEmp) {
        if (codEmp == null || codEmp <= 0) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "El código debe ser un número positivo"));
        }

        try {
            boolean existe = empleadoService.existeEmpleado(codEmp);
            return ResponseEntity.ok(Map.of("existe", existe));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al verificar empleado: " + e.getMessage()));
        }
    }
}