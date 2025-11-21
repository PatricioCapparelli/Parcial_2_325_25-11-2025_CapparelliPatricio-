package cine.vista;

import cine.controlador.ControladorCine;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class VistaLogin {
    public static Scene crear(ControladorCine controlador) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle(Estilos.FONDO);

        Text scenetitle = new Text("Bienvenido al Cine");
        scenetitle.setStyle(Estilos.TITULO);
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Email:");
        userName.setStyle(Estilos.LABEL); 
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField("user@cine.com");
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Contraseña:");
        pw.setStyle(Estilos.LABEL); 
        grid.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        pwBox.setText("1234");
        grid.add(pwBox, 1, 2);

        Button btnLogin = new Button("Iniciar Sesion");
        btnLogin.setStyle(Estilos.BOTON_ROJO);
        HBox hbBtnLogin = new HBox(10);
        hbBtnLogin.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnLogin.getChildren().add(btnLogin);
        grid.add(hbBtnLogin, 1, 4);

        Text registerTitle = new Text("¿Nuevo? Registrate");
        registerTitle.setStyle(Estilos.SUBTITULO);
        grid.add(registerTitle, 0, 6, 2, 1);

        Label regNombre = new Label("Nombre:");
        regNombre.setStyle(Estilos.LABEL); 
        grid.add(regNombre, 0, 7);
        TextField regNombreField = new TextField();
        grid.add(regNombreField, 1, 7);
        
        Label regEmail = new Label("Email:");
        regEmail.setStyle(Estilos.LABEL); 
        grid.add(regEmail, 0, 8);
        TextField regEmailField = new TextField();
        grid.add(regEmailField, 1, 8);

        Label regPw = new Label("Contraseña:");
        regPw.setStyle(Estilos.LABEL); 
        grid.add(regPw, 0, 9);
        PasswordField regPwField = new PasswordField();
        grid.add(regPwField, 1, 9);

        Button btnRegister = new Button("Registrarse");
        btnRegister.setStyle(Estilos.BOTON_ROJO); 
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
}