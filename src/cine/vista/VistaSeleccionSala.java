package cine.vista;

import cine.controlador.ControladorCine;
import cine.modelo.Sala;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VistaSeleccionSala {

    public static Scene crear(ControladorCine controlador) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle(Estilos.FONDO);

        Text title = new Text("Seleccione una Pelicula");
        title.setStyle(Estilos.TITULO);
        layout.getChildren().add(title);

        for (Sala sala : controlador.getCine().getSalas()) {
            Button btnSala = new Button("Sala " + sala.getNumero() + ": " + sala.getPelicula());
            btnSala.setPrefSize(300, 50);
            btnSala.setStyle(Estilos.BOTON_ROJO + "-fx-font-size: 16px;"); 
            
            btnSala.setOnAction(e -> controlador.seleccionarSala(sala));
            
            layout.getChildren().add(btnSala);
        }

        Button btnMisEntradas = new Button("Ver Mis Entradas");
        btnMisEntradas.setPrefSize(300, 40);

        btnMisEntradas.setStyle("-fx-background-color: #1E90FF; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-font-size: 14px;");
        
        btnMisEntradas.setOnAction(e -> {
            controlador.aplicacion.mostrarMisEntradas(); 
        });

        layout.getChildren().add(btnMisEntradas);

        Button btnSalir = new Button("Cerrar Sesion");
        btnSalir.setPrefSize(300, 40);
        btnSalir.setStyle(Estilos.BOTON_GRIS); 

        btnSalir.setOnAction(e -> {
            controlador.aplicacion.mostrarLogin(); 
        });

        layout.getChildren().add(btnSalir);
        
        return new Scene(layout, 400, 500);
    }
}