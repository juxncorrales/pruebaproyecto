package com.unieventos.controller;

import com.unieventos.app.MainApp;
import com.unieventos.model.Cliente;
import com.unieventos.model.Compra;
import com.unieventos.model.Evento;
import com.unieventos.model.Localidad;
import com.unieventos.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainController {

    private MainApp mainApp;
    private Usuario usuarioLogueado; // Usuario actualmente logueado (Cliente o Administrador)

    @FXML
    private TableView<Evento> tablaEventos;
    @FXML
    private TableColumn<Evento, String> columnaNombre;
    @FXML
    private TableColumn<Evento, String> columnaCiudad;
    @FXML
    private TableColumn<Evento, String> columnaFecha;
    @FXML
    private TableColumn<Evento, String> columnaTipo;
    @FXML
    private TextField campoBusqueda;
    @FXML
    private ChoiceBox<String> choiceCiudades;
    @FXML
    private ChoiceBox<String> choiceTipos;
    @FXML
    private ImageView imagenEvento;
    @FXML
    private Label etiquetaDescripcion;
    @FXML
    private ComboBox<Localidad> comboBoxLocalidades;
    @FXML
    private Spinner<Integer> spinnerCantidad;
    @FXML
    private Label etiquetaPrecio;
    @FXML
    private Label etiquetaCapacidad;
    @FXML
    private Button botonComprar;
    @FXML
    private Button botonCancelarCompra;
    @FXML
    private Button botonRedimirCupon;
    @FXML
    private Label etiquetaMensaje;

    // Constructor vacío requerido por FXMLLoader
    public MainController() {
    }

    // Método para inicializar controlador después de cargar FXML
    @FXML
    private void initialize() {
        configurarTablaEventos();
        cargarChoiceCiudades();
        cargarChoiceTipos();
        configurarComboBoxLocalidades();
        configurarSpinnerCantidad();
        configurarEventosTabla();
    }

    // Método para configurar la referencia a MainApp
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    // Método para establecer el usuario logueado
    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    // Métodos para configurar elementos de la interfaz gráfica y manejo de eventos

    private void configurarTablaEventos() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        // Listener para mostrar detalles del evento al seleccionar una fila
        tablaEventos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesEvento(newValue));
    }

    private void cargarChoiceCiudades() {
        // Lógica para cargar las ciudades disponibles
        ObservableList<String> ciudades = FXCollections.observableArrayList("Bogotá", "Medellín", "Cali");
        choiceCiudades.setItems(ciudades);
        choiceCiudades.getSelectionModel().selectFirst(); // Seleccionar la primera ciudad por defecto
    }

    private void cargarChoiceTipos() {
        // Lógica para cargar los tipos de eventos disponibles
        ObservableList<String> tipos = FXCollections.observableArrayList("Concierto", "Teatro", "Deporte", "Festival", "Otro");
        choiceTipos.setItems(tipos);
        choiceTipos.getSelectionModel().selectFirst(); // Seleccionar el primer tipo por defecto
    }

    private void configurarComboBoxLocalidades() {
        // Lógica para configurar el ComboBox de localidades disponibles
        comboBoxLocalidades.setCellFactory(param -> new ListCell<Localidad>() {
            @Override
            protected void updateItem(Localidad item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getNombre() == null) {
                    setText(null);
                } else {
                    setText(item.getNombre());
                }
            }
        });
    }

    private void configurarSpinnerCantidad() {
        // Lógica para configurar el Spinner de cantidad de entradas
        spinnerCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
    }

    private void configurarEventosTabla() {
        // Lógica para manejar eventos adicionales en la tabla de eventos, si es necesario
    }

    private void mostrarDetallesEvento(Evento evento) {
        if (evento != null) {
            imagenEvento.setImage(evento.getImagen());
            etiquetaDescripcion.setText(evento.getDescripcion());
            ObservableList<Localidad> localidades = FXCollections.observableArrayList((Localidad) evento.getLocalidades());
            comboBoxLocalidades.setItems(localidades);
            comboBoxLocalidades.getSelectionModel().selectFirst(); // Seleccionar la primera localidad por defecto
        } else {
            imagenEvento.setImage(null);
            etiquetaDescripcion.setText("");
            comboBoxLocalidades.getItems().clear();
        }
    }

    @FXML
    private void buscarEventos() {
        // Lógica para buscar eventos según criterios de búsqueda (nombre, ciudad, tipo)
        String busqueda = campoBusqueda.getText().trim();
        String ciudad = choiceCiudades.getValue();
        String tipo = choiceTipos.getValue();
        List<Evento> eventosEncontrados = mainApp.buscarEventos(busqueda, ciudad, tipo);
        mostrarEventosEnTabla(eventosEncontrados);
    }

    @FXML
    private void handleBotonComprar() {
        Evento eventoSeleccionado = tablaEventos.getSelectionModel().getSelectedItem();
        Localidad localidadSeleccionada = comboBoxLocalidades.getValue();
        int cantidadEntradas = spinnerCantidad.getValue();

        if (eventoSeleccionado == null || localidadSeleccionada == null) {
            mostrarMensajeError("Debe seleccionar un evento y una localidad.");
            return;
        }

// Lógica para verificar disponibilidad de localidad y realizar compra
        if (mainApp.verificarDisponibilidad(eventoSeleccionado, localidadSeleccionada, cantidadEntradas)) {
            double subtotal = localidadSeleccionada.getPrecio() * cantidadEntradas;
            double total = calcularTotal(subtotal);
            Compra compra = new Compra(usuarioLogueado, eventoSeleccionado, localidadSeleccionada, cantidadEntradas, total);
            mainApp.realizarCompra(compra);
            mostrarMensajeExito("Compra realizada correctamente.");
        } else {
            mostrarMensajeError("La localidad seleccionada no tiene capacidad suficiente.");
        }

    }

    private double calcularTotal(double subtotal) {
        // Lógica para calcular el total aplicando descuentos, si es necesario
        // Ejemplo: Aplicación de descuento por cupón
        return subtotal; // Por defecto, sin descuentos aplicados
    }

    @FXML
    private void handleBotonCancelarCompra() {
        // Lógica para cancelar una compra realizada, si es necesario
    }

    @FXML
    private void handleBotonRedimirCupon() {
        // Lógica para redimir un cupón de descuento, si es necesario
    }

    private void mostrarEventosEnTabla(List<Evento> eventos) {
        tablaEventos.setItems(FXCollections.observableArrayList(eventos));
    }

    private void mostrarMensajeError(String mensaje) {
        mostrarAlerta("Error", mensaje, Alert.AlertType.ERROR);
    }

    private void mostrarMensajeExito(String mensaje) {
        mostrarAlerta("Éxito", mensaje, Alert.AlertType.INFORMATION);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void comprarEntradas(ActionEvent actionEvent) {
    }

    public void mostrarRegistro(ActionEvent actionEvent) {
    }

    public void mostrarInicioSesion(ActionEvent actionEvent) {
    }

    public void cerrarSesion(ActionEvent actionEvent) {
    }

    public void registrarCliente(ActionEvent actionEvent) {
    }

    public void iniciarSesion(ActionEvent actionEvent) {
    }
}
