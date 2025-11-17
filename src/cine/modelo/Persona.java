package cine.modelo;

import java.io.Serializable;

public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String nombre;
    protected String email;

    public Persona(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return nombre + " (" + email + ")";
    }
}
