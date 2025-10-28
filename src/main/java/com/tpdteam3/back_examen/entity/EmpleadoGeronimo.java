package com.tpdteam3.back_examen.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "EmpleadoGeronimo")
public class EmpleadoGeronimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codEmp")
    private Integer codEmp;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    @Column(name = "nombEmp", length = 50, nullable = false)
    private String nombEmp;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(max = 50, message = "El apellido paterno no puede exceder 50 caracteres")
    @Column(name = "appaEmp", length = 50, nullable = false)
    private String appaEmp;

    @NotBlank(message = "El apellido materno es obligatorio")
    @Size(max = 50, message = "El apellido materno no puede exceder 50 caracteres")
    @Column(name = "apmaEmp", length = 50, nullable = false)
    private String apmaEmp;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 1, max = 10, message = "La contraseña debe tener entre 1 y 10 caracteres")
    @Column(name = "clavEmp", length = 10, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // No mostrar en respuestas JSON
    private String clavEmp;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    @Column(name = "fechNaciEmp", nullable = false)
    private LocalDate fechNaciEmp;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser un valor positivo")
    @DecimalMin(value = "0.01", message = "El peso debe ser mayor a 0")
    @DecimalMax(value = "500.00", message = "El peso no puede exceder 500 kg")
    @Column(name = "pesoEmp", nullable = false)
    private Double pesoEmp;

    // Constructores
    public EmpleadoGeronimo() {
    }

    public EmpleadoGeronimo(Integer codEmp, String nombEmp, String appaEmp,
                            String apmaEmp, String clavEmp, LocalDate fechNaciEmp, Double pesoEmp) {
        this.codEmp = codEmp;
        this.nombEmp = nombEmp;
        this.appaEmp = appaEmp;
        this.apmaEmp = apmaEmp;
        this.clavEmp = clavEmp;
        this.fechNaciEmp = fechNaciEmp;
        this.pesoEmp = pesoEmp;
    }

    // Getters y Setters
    public Integer getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(Integer codEmp) {
        this.codEmp = codEmp;
    }

    public String getNombEmp() {
        return nombEmp;
    }

    public void setNombEmp(String nombEmp) {
        this.nombEmp = nombEmp;
    }

    public String getAppaEmp() {
        return appaEmp;
    }

    public void setAppaEmp(String appaEmp) {
        this.appaEmp = appaEmp;
    }

    public String getApmaEmp() {
        return apmaEmp;
    }

    public void setApmaEmp(String apmaEmp) {
        this.apmaEmp = apmaEmp;
    }

    public String getClavEmp() {
        return clavEmp;
    }

    public void setClavEmp(String clavEmp) {
        this.clavEmp = clavEmp;
    }

    public LocalDate getFechNaciEmp() {
        return fechNaciEmp;
    }

    public void setFechNaciEmp(LocalDate fechNaciEmp) {
        this.fechNaciEmp = fechNaciEmp;
    }

    public Double getPesoEmp() {
        return pesoEmp;
    }

    public void setPesoEmp(Double pesoEmp) {
        this.pesoEmp = pesoEmp;
    }

    @Override
    public String toString() {
        return "EmpleadoGeronimo{" +
               "codEmp=" + codEmp +
               ", nombEmp='" + nombEmp + '\'' +
               ", appaEmp='" + appaEmp + '\'' +
               ", apmaEmp='" + apmaEmp + '\'' +
               ", fechNaciEmp=" + fechNaciEmp +
               ", pesoEmp=" + pesoEmp +
               '}';
    }
}