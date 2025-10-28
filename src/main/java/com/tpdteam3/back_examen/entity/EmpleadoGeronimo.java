package com.tpdteam3.back_examen.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "EmpleadoGeronimo")
public class EmpleadoGeronimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codEmp")
    private Integer codEmp;

    @Column(name = "nombEmp", length = 50)
    private String nombEmp;

    @Column(name = "appaEmp", length = 50)
    private String appaEmp;

    @Column(name = "apmaEmp", length = 50)
    private String apmaEmp;

    @Column(name = "clavEmp", length = 10)
    private String clavEmp;

    @Column(name = "fechNaciEmp")
    private LocalDate fechNaciEmp;

    @Column(name = "pesoEmp")
    private Double pesoEmp;

    // Constructor vacío
    public EmpleadoGeronimo() {
    }

    // Constructor con parámetros
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