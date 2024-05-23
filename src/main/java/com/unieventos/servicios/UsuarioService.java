package com.unieventos.servicios;

import com.unieventos.model.Cliente;
import com.unieventos.persistence.PersistenceManager;

import java.util.List;
import java.util.ArrayList;

public class UsuarioService {

    private List<Cliente> clientes;

    public UsuarioService() {
        clientes = PersistenceManager.cargarClientes();
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
    }

    public void registrarUsuario(Cliente cliente) throws Exception {
        // Lógica de registro, validaciones, etc.
        clientes.add(cliente);
        PersistenceManager.guardarClientes(clientes);
    }

    public void loguearUsuario(String email, String password) throws Exception {
        // Lógica de login, validaciones, etc.
    }

    // Otros métodos para gestionar usuarios
}
