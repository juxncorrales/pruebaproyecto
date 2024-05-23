package com.unieventos.controller;

import com.unieventos.model.Evento;
import com.unieventos.servicios.EventoService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListaEventosController {
    @FXML private TableView<Evento> eventosTable;
    @FXML private TableColumn<Evento, String> nombreColumn;
    @FXML private TableColumn<Evento, String> ciudadColumn;
    @FXML private TableColumn<Evento, String> fechaColumn;

    private EventoService eventoService = new EventoService();

    @FXML
    public void initialize() {
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ciudadColumn.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        eventosTable.setItems(eventoService.listarEventos());
    }

    @FXML
    private void verDetalles() {
        Evento eventoSeleccionado = eventosTable.getSelectionModel().getSelectedItem();
        if (eventoSeleccionado != null) {
            // Navegar a la pantalla de detalles del evento
        } else {
            mostrarAlerta(AlertType.WARNING, "Sin Selección", "Por favor, seleccione un evento.");
        }
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
