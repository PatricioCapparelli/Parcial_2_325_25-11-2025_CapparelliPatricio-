package cine.vista;

import cine.controlador.ControladorCine;
import cine.modelo.Entrada;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.List;

public class VistaMisEntradas {

    public static Scene crear(ControladorCine controlador) {
        
        BorderPane root = new BorderPane();
        root.setStyle(Estilos.FONDO);
        root.setPadding(new Insets(20));

        Text titulo = new Text("Mis Entradas");
        titulo.setStyle(Estilos.TITULO);
        BorderPane.setAlignment(titulo, Pos.CENTER);
        root.setTop(titulo);

        VBox listaContainer = new VBox(15); 
        listaContainer.setPadding(new Insets(20));
        listaContainer.setStyle(Estilos.FONDO);
        listaContainer.setAlignment(Pos.TOP_CENTER);

        List<Entrada> misEntradas = controlador.getEntradasUsuarioLogueado();

        if (misEntradas.isEmpty()) {
            Label lblVacio = new Label("No tienes entradas compradas aún.");
            lblVacio.setStyle(Estilos.SUBTITULO);
            listaContainer.getChildren().add(lblVacio);
        } else {
            for (Entrada entrada : misEntradas) {
                listaContainer.getChildren().add(crearTicketVisual(entrada));
            }
        }

        ScrollPane scrollPane = new ScrollPane(listaContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #2b2b2b; -fx-background-color: #2b2b2b;"); 
        root.setCenter(scrollPane);

        Button btnVolver = new Button("Volver al Menú");
        btnVolver.setStyle(Estilos.BOTON_GRIS);
        btnVolver.setPrefWidth(200);
        btnVolver.setOnAction(e -> controlador.aplicacion.mostrarSeleccionSala());
        
        VBox bottomBox = new VBox(btnVolver);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(15, 0, 0, 0));
        root.setBottom(bottomBox);

        return new Scene(root, 400, 500);
    }

    private static VBox crearTicketVisual(Entrada entrada) {
        VBox ticket = new VBox(5);
        ticket.setPadding(new Insets(15));
        ticket.setStyle("-fx-background-color: #383838; -fx-border-color: #555555; -fx-background-radius: 8; -fx-border-radius: 8;");
        ticket.setMaxWidth(320);

        Text pelicula = new Text(entrada.getSala().getPelicula());
        pelicula.setStyle("-fx-fill: #E0E0E0; -fx-font-weight: bold; -fx-font-size: 18px;");

        Label salaInfo = new Label("Sala " + entrada.getSala().getNumero());
        salaInfo.setStyle("-fx-text-fill: #FFD700; -fx-font-weight: bold;"); 

        Label butacaInfo = new Label("Butaca: Fila " + entrada.getButaca().getFila() + " - Asiento " + entrada.getButaca().getNumero());
        butacaInfo.setStyle(Estilos.LABEL);

        ticket.getChildren().addAll(pelicula, salaInfo, butacaInfo);
        return ticket;
    }
}