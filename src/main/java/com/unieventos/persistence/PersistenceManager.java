package com.unieventos.persistence;

import java.io.*;
import java.util.List;
import com.unieventos.model.Evento;
import com.unieventos.model.Cliente;

public class PersistenceManager {

    private static final String EVENTOS_FILE = "eventos.ser";
    private static final String CLIENTES_FILE = "clientes.ser";

    // Serializar lista de eventos
    public static void guardarEventos(List<Evento> eventos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EVENTOS_FILE))) {
            oos.writeObject(eventos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserializar lista de eventos
    @SuppressWarnings("unchecked")
    public static List<Evento> cargarEventos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(EVENTOS_FILE))) {
            return (List<Evento>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Serializar lista de clientes
    public static void guardarClientes(List<Cliente> clientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CLIENTES_FILE))) {
            oos.writeObject(clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserializar lista de clientes
    @SuppressWarnings("unchecked")
    public static List<Cliente> cargarClientes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CLIENTES_FILE))) {
            return (List<Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
