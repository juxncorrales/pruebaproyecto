package com.unieventos.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Cupon implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;
    private double porcentajeDescuento;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaExpiracion;
    private boolean usado;

    // Constructor
    public Cupon(double porcentajeDescuento, LocalDateTime fechaExpiracion) {
        this.codigo = generarCodigoCupon();
        this.porcentajeDescuento = porcentajeDescuento;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaExpiracion = fechaExpiracion;
        this.usado = false;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public boolean isUsado() {
        return usado;
    }

    // Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    // Método para generar el código del cupón
    private String generarCodigoCupon() {
        return UUID.randomUUID().toString();
    }

    // Método para verificar si el cupón es válido (no ha expirado y no ha sido usado)
    public boolean esValido() {
        return !usado && LocalDateTime.now().isBefore(fechaExpiracion);
    }
}
