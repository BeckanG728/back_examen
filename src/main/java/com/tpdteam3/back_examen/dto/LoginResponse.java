package com.tpdteam3.back_examen.dto;

import com.tpdteam3.back_examen.entity.EmpleadoGeronimo;

public class LoginResponse {

    private boolean success;
    private String message;
    private EmpleadoGeronimo empleado;

    public LoginResponse() {
    }

    public LoginResponse(boolean success, String message, EmpleadoGeronimo empleado) {
        this.success = success;
        this.message = message;
        this.empleado = empleado;
    }

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
}