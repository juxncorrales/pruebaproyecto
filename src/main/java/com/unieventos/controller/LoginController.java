package com.unieventos.controller;

import com.unieventos.MainApp;
import com.unieventos.servicios.UsuarioService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private UsuarioService usuarioService = new UsuarioService();
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void login() {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            usuarioService.loguearUsuario(email, password);
            mainApp.mostrarListaEventos();
        } catch (Exception e) {
            mostrarAlerta(AlertType.ERROR, "Error de Login", e.getMessage());
        }
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
