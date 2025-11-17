package cine.modelo;

import java.io.Serializable;

public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;

    private int numero;
    private String pelicula;
    private Butaca[][] butacas;

    public Sala(int numero, String pelicula, int filas, int numerosPorFila) {
        this.numero = numero;
        this.pelicula = pelicula;
        inicializarButacas(filas, numerosPorFila);
    }

    private void inicializarButacas(int filas, int numerosPorFila) {
        this.butacas = new Butaca[filas][numerosPorFila];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < numerosPorFila; j++) {
                this.butacas[i][j] = new Butaca(i + 1, j + 1);
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