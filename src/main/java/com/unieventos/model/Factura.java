package com.unieventos.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID codigoFactura;
    private Cliente cliente;
    private Evento evento;
    private String localidad;
    private int cantidadEntradas;
    private double subtotal;
    private double total;
    private LocalDateTime fechaCompra;
    private String codigoQR;
    private Cupon cupon; // opcional, si se usa un cupón

    // Constructor
    public Factura(Cliente cliente, Evento evento, String localidad, int cantidadEntradas, double subtotal, double total, Cupon cupon) {
        this.codigoFactura = UUID.randomUUID();
        this.cliente = cliente;
        this.evento = evento;
        this.localidad = localidad;
        this.cantidadEntradas = cantidadEntradas;
        this.subtotal = subtotal;
        this.total = total;
        this.fechaCompra = LocalDateTime.now();
        this.codigoQR = generarCodigoQR();
        this.cupon = cupon;
    }

    // Getters
    public UUID getCodigoFactura() {
        return codigoFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Evento getEvento() {
        return evento;
    }

    public String getLocalidad() {
        return localidad;
    }

    public int getCantidadEntradas() {
        return cantidadEntradas;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public String getCodigoQR() {
        return codigoQR;
    }

    public Cupon getCupon() {
        return cupon;
    }

    // Setters
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setCantidadEntradas(int cantidadEntradas) {
        this.cantidadEntradas = cantidadEntradas;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setCodigoQR(String codigoQR) {
        this.codigoQR = codigoQR;
    }

    public void setCupon(Cupon cupon) {
        this.cupon = cupon;
    }

    // Método para generar el código QR (puede ser una simulación)
    private String generarCodigoQR() {
        // Aquí puedes implementar una lógica para generar un código QR
        // Por ejemplo, podrías usar una biblioteca de generación de QR o cualquier otra lógica personalizada
        return "QR-" + codigoFactura.toString();
    }
}
