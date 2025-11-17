package cine.modelo;

import java.util.Objects;

public class Cliente extends Persona {

    private static final long serialVersionUID = 1L;

    private String password;

    public Cliente(String nombre, String email, String password) {
        super(nombre, email);
        this.password = password;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return email.equals(cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
