package com.unieventos.model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String ciudad;
    private String descripcion;
    private String tipo; // Puede ser concierto, teatro, deporte, festival, otro
    private String imagen;
    private LocalDateTime fecha;
    private String direccion;
    private Map<String, Localidad> localidades; // Localidades con su capacidad y precio

    // Constructor
    public Evento(String nombre, String ciudad, String descripcion, String tipo, String imagen, LocalDateTime fecha, String direccion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.imagen = imagen;
        this.fecha = fecha;
        this.direccion = direccion;
        this.localidades = new HashMap<>();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public Image getImagen() {
        return imagen;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public Map<String, Localidad> getLocalidades() {
        return localidades;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalidades(Map<String, Localidad> localidades) {
        this.localidades = localidades;
    }

    // Método para añadir una localidad
    public void agregarLocalidad(String nombre, double precio, int capacidad) {
        this.localidades.put(nombre, new Localidad(nombre, precio, capacidad));
    }

    // Clase interna para representar una localidad
    public static class Localidad implements Serializable {
        private static final long serialVersionUID = 1L;

        private String nombre;
        private double precio;
        private int capacidad;
        private int entradasVendidas;

        // Constructor
        public Localidad(String nombre, double precio, int capacidad) {
            this.nombre = nombre;
            this.precio = precio;
            this.capacidad = capacidad;
            this.entradasVendidas = 0;
        }

        // Getters
        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }

        public int getCapacidad() {
            return capacidad;
        }

        public int getEntradasVendidas() {
            return entradasVendidas;
        }

        // Setters
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public void setCapacidad(int capacidad) {
            this.capacidad = capacidad;
        }

        public void setEntradasVendidas(int entradasVendidas) {
            this.entradasVendidas = entradasVendidas;
        }

        // Método para vender entradas
        public boolean venderEntradas(int cantidad) {
            if (entradasVendidas + cantidad <= capacidad) {
                entradasVendidas += cantidad;
                return true;
            } else {
                return false;
            }
        }
    }
}
