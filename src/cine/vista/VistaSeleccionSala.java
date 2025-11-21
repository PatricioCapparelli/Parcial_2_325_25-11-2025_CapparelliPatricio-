package cine.vista;

import cine.controlador.ControladorCine;
import cine.modelo.Sala;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VistaSeleccionSala {

    public static Scene crear(ControladorCine controlador) {
        BorderPane root = new BorderPane();
        root.setStyle(Estilos.FONDO);
        
        Text title = new Text("En Cartelera Hoy");
        title.setStyle(Estilos.TITULO);
        VBox topBox = new VBox(title);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(20));
        root.setTop(topBox);

        FlowPane postersContainer = new FlowPane();
        postersContainer.setHgap(25);
        postersContainer.setVgap(25);
        postersContainer.setAlignment(Pos.CENTER);
        postersContainer.setPadding(new Insets(30));
        postersContainer.setStyle(Estilos.FONDO);

        DropShadow shadow = new DropShadow(20, Color.BLACK);

        for (Sala sala : controlador.getCine().getSalas()) {
            Button btnPoster = new Button();
            
            try {
                String rutaImagen = "assets/" + sala.getImagenPath();
                FileInputStream stream = new FileInputStream(rutaImagen);
                Image image = new Image(stream);
                
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(300); 
                imageView.setPreserveRatio(true);
                imageView.setEffect(shadow);
                
                btnPoster.setGraphic(imageView);
                btnPoster.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-cursor: hand;");
                
                Tooltip tooltip = new Tooltip("Sala " + sala.getNumero() + ": " + sala.getPelicula());
                btnPoster.setTooltip(tooltip);

            } catch (FileNotFoundException e) {
                System.err.println("Imagen no encontrada: " + sala.getImagenPath());
                btnPoster.setText("Sala " + sala.getNumero() + "\n" + sala.getPelicula());
                btnPoster.setPrefSize(150, 220);
                btnPoster.setStyle(Estilos.BOTON_ROJO);
            }

            btnPoster.setOnAction(e -> controlador.seleccionarSala(sala));
            
            postersContainer.getChildren().add(btnPoster);
        }

        ScrollPane scrollPane = new ScrollPane(postersContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        root.setCenter(scrollPane);

        VBox bottomControls = new VBox(15);
        bottomControls.setAlignment(Pos.CENTER);
        bottomControls.setPadding(new Insets(20));

        Button btnMisEntradas = new Button("Ver Mis Entradas");
        btnMisEntradas.setPrefSize(250, 40);
        btnMisEntradas.setStyle("-fx-background-color: #1E90FF; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-font-size: 14px; -fx-cursor: hand;");
        btnMisEntradas.setOnAction(e -> controlador.navegarAMisEntradas());

        Button btnSalir = new Button("Cerrar Sesion");
        btnSalir.setPrefSize(250, 40);
        btnSalir.setStyle(Estilos.BOTON_GRIS + "; -fx-cursor: hand;");
        btnSalir.setOnAction(e -> controlador.cerrarSesion());


        if (controlador.esAdmin()) {
            Button btnReset = new Button("REINICIAR SISTEMA");
            btnReset.setPrefSize(250, 40);
            btnReset.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
            btnReset.setOnAction(e -> controlador.reiniciarTodo());
            bottomControls.getChildren().add(btnReset);
        }
     
        bottomControls.getChildren().addAll(btnMisEntradas, btnSalir);
        root.setBottom(bottomControls);
        return new Scene(root, 900, 700);
    }
}