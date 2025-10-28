package com.tpdteam3.back_examen.dto;


public class LoginRequest {

    private Integer codEmp;

    private String clavEmp;

    public LoginRequest() {
    }

    public LoginRequest(Integer codEmp, String clavEmp) {
        this.codEmp = codEmp;
        this.clavEmp = clavEmp;
    }

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
}
