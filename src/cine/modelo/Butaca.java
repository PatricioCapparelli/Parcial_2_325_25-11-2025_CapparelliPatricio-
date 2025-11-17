package cine.modelo;

import java.io.Serializable;

public class Butaca implements Serializable {

    private static final long serialVersionUID = 1L;

    private int fila;
    private int numero;
    private EstadoButaca estado;

    public Butaca(int fila, int numero) {
        this.fila = fila;
        this.numero = numero;
        this.estado = EstadoButaca.LIBRE;
    }

    public int getFila() {
        return fila;
    }

    public int getNumero() {
        return numero;
    }

    public EstadoButaca getEstado() {
        return estado;
    }

    public void setEstado(EstadoButaca estado) {
        this.estado = estado;
    }

    public boolean ocupar() {
        if (this.estado == EstadoButaca.LIBRE) {
            this.estado = EstadoButaca.OCUPADA;
            return true;
        }
        return false;
    }
    
    public boolean liberar() {
    if (estado == EstadoButaca.OCUPADA) {
        estado = EstadoButaca.LIBRE;
        return true;
    }
    return false;
    }


    @Override
    public String toString() {
        return "Butaca [F" + fila + " N" + numero + " - " + estado + "]";
    }
}