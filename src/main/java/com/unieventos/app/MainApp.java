package com.unieventos.app;

import com.unieventos.controller.MainController;
import com.unieventos.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    private Stage primaryStage;
    private MainController mainController;

    private List<Evento> eventos;
    private List<Usuario> usuarios;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("UniEventos");

        inicializarDatos(); // Método para inicializar datos de prueba (eventos y usuarios)

        mostrarPantallaPrincipal();
    }

    private void inicializarDatos() {
        // Inicialización de eventos de prueba
        eventos = new ArrayList<>();
        eventos.add(new Evento("Concierto A", "Bogotá", "Descripción del concierto A", "Concierto", null, "Dirección del evento"));
        eventos.add(new Evento("Teatro B", "Medellín", "Descripción del teatro B", "Teatro", null, "Otra dirección"));

        // Inicialización de usuarios de prueba
        usuarios = new ArrayList<>();
        Cliente primerCliente = new Cliente("1234567890", "Cliente1", "12345678", "cliente@unieventos.com", "cliente123");
        usuarios.add(primerCliente);

        // Asignar cupones a los usuarios de prueba
        Cupon cuponRegistro = new Cupon("REGISTRO", 15);
        Cupon cuponPrimeraCompra = new Cupon("PRIMERA_COMPRA", 10);
        primerCliente.agregarCupon(cuponRegistro);
        primerCliente.agregarCupon(cuponPrimeraCompra);
    }

    private void mostrarPantallaPrincipal() {
        try {
            // Cargar el archivo FXML de la pantalla principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/unieventos/view/Main.fxml"));
            Scene scene = new Scene(loader.load());

            // Obtener el controlador de la pantalla principal
            mainController = loader.getController();
            mainController.setMainApp(this); // Establecer referencia a esta clase MainApp en el controlador

            // Configurar la escena y mostrar la ventana principal
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Métodos para acceder a los datos y realizar operaciones lógicas

    public List<Evento> getEventos() {
        return eventos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Evento> buscarEventos(String busqueda, String ciudad, String tipo) {
        // Lógica para buscar eventos según criterios específicos (nombre, ciudad, tipo)
        List<Evento> eventosEncontrados = new ArrayList<>();
        for (Evento evento : eventos) {
            if (evento.getNombre().toLowerCase().contains(busqueda.toLowerCase())
                    && evento.getCiudad().equalsIgnoreCase(ciudad)
                    && evento.getTipo().equalsIgnoreCase(tipo)) {
                eventosEncontrados.add(evento);
            }
        }
        return eventosEncontrados;
    }

    public boolean verificarDisponibilidad(Evento evento, Localidad localidad, int cantidadEntradas) {
        // Lógica para verificar la disponibilidad de entradas en una localidad específica del evento
        return localidad.getCapacidadMaxima() >= cantidadEntradas;
    }

    public void realizarCompra(Compra compra) {
        // Lógica para realizar una compra de entradas
        // Aquí podrías guardar la compra realizada, enviar un correo electrónico, etc.
        // Ejemplo básico:
        System.out.println("Compra realizada: " + compra);
    }

    // Método para agregar un cupón a un cliente específico
    public void agregarCuponACliente(String emailCliente, Cupon cupon) {
        // Recorrer la lista de usuarios para buscar al cliente por email
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente) {
                Cliente cliente = (Cliente) usuario;
                if (cliente.getEmail().equals(emailCliente)) {
                    // Si encontramos al cliente, agregamos el cupón
                    cliente.agregarCupon(cupon);
                    System.out.println("Cupón agregado al cliente " + cliente.getNombreCompleto() + ": " + cupon.getNombre());
                    return; // Salir del método después de agregar el cupón
                }
            }
        }
        // Si no se encontró ningún cliente con el email proporcionado
        System.out.println("No se encontró ningún cliente con el email " + emailCliente);
    }
}
