package com.unieventos.controller;

import com.unieventos.model.Evento;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DetallesEventoController {
    @FXML private Label nombreLabel;
    @FXML private Label descripcionLabel;
    @FXML private Label fechaLabel;
    @FXML private Label ciudadLabel;
    @FXML private Label direccionLabel;

    private Evento evento;

    public void setEvento(Evento evento) {
        this.evento = evento;
        nombreLabel.setText(evento.getNombre());
        descripcionLabel.setText(evento.getDescripcion());
        fechaLabel.setText(evento.getFecha().toString());
        ciudadLabel.setText(evento.getCiudad());
        direccionLabel.setText(evento.getDireccion());
    }

    @FXML
    private void comprarBoletos() {
        // Navegar a la pantalla de compra de boletos
    }
}
