package com.unieventos.controller;

import com.unieventos.app.MainApp;
import com.unieventos.model.Cliente;
import com.unieventos.servicios.UsuarioService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RegistroController {
    @FXML private TextField cedulaField;
    @FXML private TextField nombreField;
    @FXML private TextField telefonoField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private UsuarioService usuarioService = new UsuarioService();
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void registrar() {
        Cliente cliente = new Cliente(
                cedulaField.getText(),
                nombreField.getText(),
                telefonoField.getText(),
                emailField.getText(),
                passwordField.getText()
        );
        try {
            usuarioService.registrarUsuario(cliente);
            mostrarAlerta(AlertType.INFORMATION, "Registro Exitoso", "Se ha enviado un correo de verificación.");
            mainApp.mostrarPantallaInicio();
        } catch (Exception e) {
            mostrarAlerta(AlertType.ERROR, "Error de Registro", e.getMessage());
        }
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
