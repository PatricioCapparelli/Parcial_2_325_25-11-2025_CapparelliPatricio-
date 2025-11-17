package cine;

import cine.controlador.ControladorCine;
import cine.modelo.Sala;
import cine.vista.Vistas;
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
            System.out.println("Cerrando aplicacion y guardando datos.");
            controlador.guardarDatos();
        });

        mostrarLogin();
    }

    public void mostrarLogin() {
        Scene escenaLogin = Vistas.crearEscenaLogin(controlador);
        primaryStage.setTitle("Sistema de Cine - Login");
        primaryStage.setScene(escenaLogin);
        primaryStage.show();
    }

    public void mostrarSeleccionSala() {
        Scene escenaSalas = Vistas.crearEscenaSeleccionSala(controlador);
        primaryStage.setTitle("Seleccion de Pelicula");
        primaryStage.setScene(escenaSalas);
    }

    public void mostrarButacas(Sala sala) {
        Scene escenaButacas = Vistas.crearEscenaButacas(controlador, sala);
        primaryStage.setTitle("Seleccionar Butacas - " + sala.getPelicula());
        primaryStage.setScene(escenaButacas);
    }
}