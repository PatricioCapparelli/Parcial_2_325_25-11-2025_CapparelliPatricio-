package cine.vista;

import cine.controlador.ControladorCine;
import cine.modelo.Butaca;
import cine.modelo.EstadoButaca;
import cine.modelo.Sala;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VistaButacas {

    public static Scene crear(ControladorCine controlador, Sala sala) {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));
        layout.setStyle(Estilos.FONDO);

        Text title = new Text("Sala " + sala.getNumero() + ": " + sala.getPelicula());
        title.setStyle(Estilos.TITULO);
        BorderPane.setAlignment(title, Pos.CENTER);

        StackPane pantalla = new StackPane();
        pantalla.setPadding(new Insets(10));
        pantalla.setStyle("-fx-background-color: #E0E0E0; -fx-border-color: #555555; -fx-border-width: 2;");
        
        Text txtPantalla = new Text("PANTALLA");
        txtPantalla.setStyle("-fx-fill: #333333; -fx-font-weight: bold; -fx-font-size: 18px;");
        pantalla.getChildren().add(txtPantalla);
        
        VBox topContainer = new VBox(20, title, pantalla);
        topContainer.setAlignment(Pos.CENTER);
        layout.setTop(topContainer);

        GridPane gridButacas = new GridPane();
        gridButacas.setAlignment(Pos.CENTER);
        gridButacas.setHgap(10);
        gridButacas.setVgap(10);
        gridButacas.setPadding(new Insets(20));

        Butaca[][] butacas = sala.getButacas();
        final int filas = butacas.length;
        final int numeros = butacas[0].length;
        final ToggleButton[][] botonesButaca = new ToggleButton[filas][numeros];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < numeros; j++) {
                Butaca butaca = butacas[i][j];
                ToggleButton btnButaca = new ToggleButton("F" + butaca.getFila() + "N" + butaca.getNumero());
                btnButaca.setPrefSize(60, 40);
                btnButaca.setUserData(butaca);

                // Estado inicial visual
                if (butaca.getEstado() == EstadoButaca.OCUPADA) {
                    btnButaca.setStyle("-fx-background-color: #F08080;"); 
                    btnButaca.setDisable(true);
                } else {
                    btnButaca.setStyle("-fx-background-color: #90EE90;"); 
                }

                
                btnButaca.setOnAction(e -> {
                    if (btnButaca.isSelected()) {
                        btnButaca.setStyle("-fx-background-color: #FFD700;"); 
                    } else {
                        btnButaca.setStyle("-fx-background-color: #90EE90;");
                    }
                });

                botonesButaca[i][j] = btnButaca;
                gridButacas.add(btnButaca, j, i);
            }
        }
        layout.setCenter(gridButacas);

        Button btnComprar = new Button("Comprar Entradas Seleccionadas");
        btnComprar.setFont(Font.font(16));
        btnComprar.setStyle(Estilos.BOTON_ROJO + "-fx-font-size: 16px;");

        Button btnVolver = new Button("Volver a Salas");
        btnVolver.setOnAction(e -> controlador.aplicacion.mostrarSeleccionSala());
        btnVolver.setStyle(Estilos.BOTON_GRIS);
        
        Button btnLimpiar = new Button("Limpiar Selección");
        btnLimpiar.setStyle(Estilos.BOTON_GRIS); 

        HBox bottomBox = new HBox(20, btnVolver, btnLimpiar, btnComprar); 
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(20, 0, 0, 0));
        layout.setBottom(bottomBox);

        btnComprar.setOnAction(e -> {
            boolean comprada = false;
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < numeros; j++) {
                    if (botonesButaca[i][j].isSelected() && !botonesButaca[i][j].isDisabled()) {
                        controlador.comprarEntrada(sala, i, j);
                        comprada = true;
                    }
                }
            }
            if (!comprada) {
                mostrarAlertaError();
            }
        });
        
        btnLimpiar.setOnAction(e -> {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < numeros; j++) {
                    ToggleButton btnButaca = botonesButaca[i][j];
                    if (btnButaca.isSelected() && !btnButaca.isDisabled()) {
                        btnButaca.setSelected(false); 
                        btnButaca.setStyle("-fx-background-color: #90EE90;");
                    }
                }
            }
        });
        
        return new Scene(layout, (numeros * 80) + 60, (filas * 60) + 250);
    }

    private static void mostrarAlertaError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Compra");
        alert.setHeaderText("No se seleccionaron butacas");
        alert.setContentText("Debe seleccionar al menos una butaca libre para comprar.");
        alert.showAndWait();
    }
}