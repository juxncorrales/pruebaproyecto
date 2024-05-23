package com.unieventos.servicios;

import com.unieventos.model.Evento;
import com.unieventos.persistence.PersistenceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EventoService {

    private ObservableList<Evento> eventos;

    public EventoService() {
        List<Evento> eventoList = PersistenceManager.cargarEventos();
        if (eventoList != null) {
            eventos = FXCollections.observableArrayList(eventoList);
        } else {
            eventos = FXCollections.observableArrayList();
        }
    }

    public ObservableList<Evento> listarEventos() {
        return eventos;
    }

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
        PersistenceManager.guardarEventos(eventos);
    }

    // Otros métodos para gestionar eventos
}
