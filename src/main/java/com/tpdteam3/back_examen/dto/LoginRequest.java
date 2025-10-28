package com.tpdteam3.back_examen.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    @NotNull(message = "El código de empleado es obligatorio")
    @Positive(message = "El código de empleado debe ser un número positivo")
    private Integer codEmp;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 1, max = 10, message = "La contraseña debe tener entre 1 y 10 caracteres")
    private String clavEmp;

    // Constructores
    public LoginRequest() {
    }

    public LoginRequest(Integer codEmp, String clavEmp) {
        this.codEmp = codEmp;
        this.clavEmp = clavEmp;
    }

    // Getters y Setters
    public Integer getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(Integer codEmp) {
        this.codEmp = codEmp;
    }

    public String getClavEmp() {
        return clavEmp;
    }

    public void setClavEmp(String clavEmp) {
        this.clavEmp = clavEmp;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
               "codEmp=" + codEmp +
               ", clavEmp='[PROTECTED]'" +
               '}';
    }
}
