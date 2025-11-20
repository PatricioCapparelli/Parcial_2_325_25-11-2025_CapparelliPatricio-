package cine;

import cine.controlador.ControladorCine;
import cine.modelo.Sala;
import cine.vista.VistaLogin;
import cine.vista.VistaButacas;
import cine.vista.VistaMisEntradas;
import cine.vista.VistaSeleccionSala;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;
    private ControladorCine controlador;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.controlador = new ControladorCine(this);
        controlador.iniciarDatos();

        primaryStage.setOnCloseRequest(e -> {
            System.out.println("Cerrando aplicación y guardando datos...");
            controlador.guardarDatos();
        });

        mostrarLogin();
    }

    public void mostrarLogin() {
        try {
            Scene escenaLogin = VistaLogin.crear(controlador);
            primaryStage.setTitle("Sistema de Cine - Login");
            primaryStage.setScene(escenaLogin);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarSeleccionSala() {
        try {
            Scene escenaSalas = VistaSeleccionSala.crear(controlador);
            primaryStage.setTitle("Selección de Película");
            primaryStage.setScene(escenaSalas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarButacas(Sala sala) {
        try {
            Scene escenaButacas = VistaButacas.crear(controlador, sala);
            primaryStage.setTitle("Seleccionar Butacas - " + sala.getPelicula());
            primaryStage.setScene(escenaButacas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarMisEntradas() {
        try {
            Scene scene = VistaMisEntradas.crear(this.controlador);
            primaryStage.setTitle("Mis Entradas Compradas");
            primaryStage.setScene(scene);
        } catch (Exception e) {
            System.err.println("Error al mostrar Mis Entradas:");
            e.printStackTrace();
        }
    }
}