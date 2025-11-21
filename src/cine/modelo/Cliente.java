package cine.modelo;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String email;
    private String password; 

    public Cliente(String nombre, String email, String password) {
        super(nombre, email);
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getNombre() { return nombre; }
    
    @Override
    public String getEmail() { return email; }
    
    public String getPassword() { return password; } 
    
    @Override
    public String toString() {
        return "Cliente: " + nombre;
    }
}