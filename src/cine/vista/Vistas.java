package cine.vista;

import cine.controlador.ControladorCine;
import cine.modelo.Butaca;
import cine.modelo.EstadoButaca;
import cine.modelo.Sala;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Vistas {

    private static final String ESTILO_FONDO = "-fx-background-color: #2b2b2b;";
    private static final String ESTILO_TITULO = "-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #E0E0E0;";
    private static final String ESTILO_SUBTITULO = "-fx-font-size: 18px; -fx-font-weight: normal; -fx-fill: #E0E0E0;";
    private static final String ESTILO_LABEL = "-fx-text-fill: #CCCCCC;";
    private static final String ESTILO_BOTON_ROJO = "-fx-background-color: #c70000; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 5;";
    private static final String ESTILO_BOTON_GRIS = "-fx-background-color: #555555; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;";


    public static Scene crearEscenaLogin(ControladorCine controlador) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle(ESTILO_FONDO);

        Text scenetitle = new Text("Bienvenido al Cine");
        scenetitle.setStyle(ESTILO_TITULO);
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Email:");
        userName.setStyle(ESTILO_LABEL); 
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField("user@cine.com");
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Contraseña:");
        pw.setStyle(ESTILO_LABEL); 
        grid.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        pwBox.setText("1234");
        grid.add(pwBox, 1, 2);

        Button btnLogin = new Button("Iniciar Sesion");
        btnLogin.setStyle(ESTILO_BOTON_ROJO);
        HBox hbBtnLogin = new HBox(10);
        hbBtnLogin.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnLogin.getChildren().add(btnLogin);
        grid.add(hbBtnLogin, 1, 4);

        Text registerTitle = new Text("¿Nuevo? Registrate");
        registerTitle.setStyle(ESTILO_SUBTITULO);
        grid.add(registerTitle, 0, 6, 2, 1);

        Label regNombre = new Label("Nombre:");
        regNombre.setStyle(ESTILO_LABEL); 
        grid.add(regNombre, 0, 7);
        TextField regNombreField = new TextField();
        grid.add(regNombreField, 1, 7);
        
        Label regEmail = new Label("Email:");
        regEmail.setStyle(ESTILO_LABEL); 
        grid.add(regEmail, 0, 8);
        TextField regEmailField = new TextField();
        grid.add(regEmailField, 1, 8);

        Label regPw = new Label("Contraseña:");
        regPw.setStyle(ESTILO_LABEL); 
        grid.add(regPw, 0, 9);
        PasswordField regPwField = new PasswordField();
        grid.add(regPwField, 1, 9);

        Button btnRegister = new Button("Registrarse");
        btnRegister.setStyle(ESTILO_BOTON_ROJO); 
        HBox hbBtnRegister = new HBox(10);
        hbBtnRegister.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnRegister.getChildren().add(btnRegister);
        grid.add(hbBtnRegister, 1, 10);

        btnLogin.setOnAction(e -> 
            controlador.intentoDeLogin(userTextField.getText(), pwBox.getText())
        );
        
        btnRegister.setOnAction(e -> 
            controlador.intentoDeRegistro(
                regNombreField.getText(), 
                regEmailField.getText(), 
                regPwField.getText()
            )
        );

        return new Scene(grid, 400, 500);
    }


    public static Scene crearEscenaSeleccionSala(ControladorCine controlador) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle(ESTILO_FONDO);

        Text title = new Text("Seleccione una Pelicula");
        title.setStyle(ESTILO_TITULO);
        layout.getChildren().add(title);

        for (Sala sala : controlador.getCine().getSalas()) {
            Button btnSala = new Button("Sala " + sala.getNumero() + ": " + sala.getPelicula());
            btnSala.setPrefSize(300, 50);
            
            btnSala.setStyle(ESTILO_BOTON_ROJO + "-fx-font-size: 16px;"); 
            
            btnSala.setOnAction(e -> controlador.seleccionarSala(sala));
            
            layout.getChildren().add(btnSala);
        }

        return new Scene(layout, 400, 400);
    }


    public static Scene crearEscenaButacas(ControladorCine controlador, Sala sala) {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));
        // Aplicar fondo oscuro
        layout.setStyle(ESTILO_FONDO);

        Text title = new Text("Sala " + sala.getNumero() + ": " + sala.getPelicula());
        // Aplicar estilo de título
        title.setStyle(ESTILO_TITULO);
        BorderPane.setAlignment(title, Pos.CENTER);
        layout.setTop(title);

        StackPane pantalla = new StackPane();
        pantalla.setPadding(new Insets(10));
        // Aplicar estilo de pantalla de cine
        pantalla.setStyle("-fx-background-color: #E0E0E0; -fx-border-color: #555555; -fx-border-width: 2;");
        
        Text txtPantalla = new Text("PANTALLA");
        // Aplicar estilo de texto de pantalla
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
        
        // Convertimos a 'final' para que puedan ser usadas en las lambdas (setOnAction)
        final int filas = butacas.length;
        final int numeros = butacas[0].length;
        final ToggleButton[][] botonesButaca = new ToggleButton[filas][numeros];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < numeros; j++) {
                Butaca butaca = butacas[i][j];
                ToggleButton btnButaca = new ToggleButton("F" + butaca.getFila() + "N" + butaca.getNumero());
                btnButaca.setPrefSize(60, 40);
                
                btnButaca.setUserData(butaca);

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
        // Aplicar estilo de botón rojo
        btnComprar.setStyle(ESTILO_BOTON_ROJO + "-fx-font-size: 16px;");

        Button btnVolver = new Button("Volver a Salas");
        btnVolver.setOnAction(e -> controlador.aplicacion.mostrarSeleccionSala());
        btnVolver.setStyle(ESTILO_BOTON_GRIS);
        
        
        Button btnLimpiar = new Button("Limpiar Selección");
        btnLimpiar.setStyle(ESTILO_BOTON_GRIS); 

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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Compra");
                alert.setHeaderText("No se seleccionaron butacas");
                alert.setContentText("Debe seleccionar al menos una butaca libre para comprar.");
                alert.showAndWait();
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
}