package cine.modelo;

import cine.util.PasswordUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Cine implements Serializable {

    private static final long serialVersionUID = 1L;

    private Catalogo<Sala> salas;
    private Catalogo<Cliente> clientes;
    private Catalogo<Entrada> entradas;

    public Cine() {
        this.salas = new Catalogo<>();
        this.clientes = new Catalogo<>();
        this.entradas = new Catalogo<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.agregar(cliente);
    }

    public Optional<Cliente> buscarCliente(String email) {
        return clientes.buscar(c -> Objects.equals(c.getEmail(), email));
    }

    public Optional<Cliente> login(String email, String passwordIngresada) {
        Optional<Cliente> clienteOpt = buscarCliente(email);
        
        if (clienteOpt.isPresent()) {
            Cliente c = clienteOpt.get();
            String hashGuardado = c.getPassword(); 

            if (PasswordUtil.verificarPassword(passwordIngresada, hashGuardado)) {
                return clienteOpt; 
            }
        }
        return Optional.empty(); 
    }
    
    public void reiniciarSistema() {
        entradas.vaciar(); 
        
        for (Sala s : salas.getTodos()) {
            s.liberarTodasLasButacas();
        }
    }
    
    public void agregarSala(Sala sala) {
        salas.agregar(sala);
    }

    public List<Sala> getSalas() {
        return salas.getTodos();
    }

    public void agregarEntrada(Entrada entrada) {
        entradas.registrar(entrada);
    }

    public List<Entrada> getEntradasVendidas() {
        return entradas.getTodos();
    }
}