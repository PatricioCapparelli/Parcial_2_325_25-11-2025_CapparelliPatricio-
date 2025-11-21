package cine.modelo;

import java.io.Serializable;

public class Sala implements Serializable {

    private static final long serialVersionUID = 2L;

    private int numero;
    private String pelicula;
    private Butaca[][] butacas;
    private String imagenPath;
    
    public Sala(int numero, String pelicula, int filas, int columnas, String imagenPath) {
        this.numero = numero;
        this.pelicula = pelicula;
        this.imagenPath = imagenPath;
        inicializarButacas(filas, columnas);
    }

    private void inicializarButacas(int filas, int columnas) {
        this.butacas = new Butaca[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                this.butacas[i][j] = new Butaca(i + 1, j + 1);
            }
        }
    }
    
    public void liberarTodasLasButacas() {
        for (int i = 0; i < butacas.length; i++) {
            for (int j = 0; j < butacas[i].length; j++) {
                butacas[i][j].liberar();
            }
        }
    }

    public int getNumero() {
        return numero;
    }

    public String getPelicula() {
        return pelicula;
    }

    public Butaca[][] getButacas() {
        return butacas;
    }
    
    public String getImagenPath() { return imagenPath; }

    public Butaca ocuparButaca(int fila, int numero) {
        if (fila >= 0 && fila < butacas.length && numero >= 0 && numero < butacas[0].length) {
            Butaca butaca = butacas[fila][numero];
            if (butaca.ocupar()) {
                return butaca;
            }
        }
        return null; 
    }

    @Override
    public String toString() {
        return "Sala " + numero + ": " + pelicula;
    }
}