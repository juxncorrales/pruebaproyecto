package com.unieventos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cedula;
    private String nombreCompleto;
    private String telefono;
    private String email;
    private String password;
    private boolean verificado;
    private String codigoVerificacion;
    private List<Cupon> cupones;

    // Constructor
    public Cliente(String cedula, String nombreCompleto, String telefono, String email, String password) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.verificado = false;
        this.codigoVerificacion = generarCodigoVerificacion();
        this.cupones = new ArrayList<>();
    }

    // Getters
    public String getCedula() {
        return cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public String getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public List<Cupon> getCupones() {
        return cupones;
    }

    // Setters
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public void setCodigoVerificacion(String codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    // Método para generar un código de verificación aleatorio
    private String generarCodigoVerificacion() {
        return java.util.UUID.randomUUID().toString();
    }

    // Método para agregar un cupón al cliente
    public void agregarCupon(Cupon cupon) {
        cupones.add(cupon);
    }

    // Método para eliminar un cupón del cliente
    public void eliminarCupon(Cupon cupon) {
        cupones.remove(cupon);
    }
}
