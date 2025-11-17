package cine.vista;

import javafx.scene.control.Alert;

public class Alerta {

    public enum Tipo {
        INFO,
        ERROR,
        CONFIRM
    }

    public static void mostrarAlerta(String titulo, String mensaje, Tipo tipo) {
        Alert.AlertType tipoAlerta;

        switch (tipo) {
            case INFO:
                tipoAlerta = Alert.AlertType.INFORMATION;
                break;
            case ERROR:
                tipoAlerta = Alert.AlertType.ERROR;
                break;
            case CONFIRM:
                tipoAlerta = Alert.AlertType.CONFIRMATION;
                break;
            default:
                tipoAlerta = Alert.AlertType.NONE;
        }

        Alert alert = new Alert(tipoAlerta);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}