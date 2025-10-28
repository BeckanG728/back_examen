package com.tpdteam3.back_examen.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tpdteam3.back_examen.entity.EmpleadoGeronimo;

@JsonInclude(JsonInclude.Include.NON_NULL) // No incluir valores null en JSON
public class LoginResponse {

    private boolean success;
    private String message;
    private EmpleadoGeronimo empleado;

    // Constructores
    public LoginResponse() {
    }

    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.empleado = null;
    }

    public LoginResponse(boolean success, String message, EmpleadoGeronimo empleado) {
        this.success = success;
        this.message = message;
        this.empleado = empleado;
    }

    // Getters y Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EmpleadoGeronimo getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoGeronimo empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
               "success=" + success +
               ", message='" + message + '\'' +
               ", empleado=" + (empleado != null ? empleado.getCodEmp() : "null") +
               '}';
    }
}