package cine.controlador;

import cine.Main;
import cine.modelo.*;
import cine.persistencia.PersistenciaDatos;
import cine.vista.Alerta;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

public class ControladorCine {

    private static final String ARCHIVO_PERSISTENCIA = "cine.ser";

    private Cine cine;
    private Cliente clienteActual;
    public Main aplicacion; 

    public ControladorCine(Main aplicacion) {
        this.aplicacion = aplicacion;
        this.clienteActual = null;
    }

    public void iniciarDatos() {
        this.cine = PersistenciaDatos.cargarCine(ARCHIVO_PERSISTENCIA);

        if (this.cine == null) {
            System.out.println("Creando datos iniciales...");
            this.cine = new Cine();
            this.cine.agregarSala(new Sala(1, "El padrino", 5, 8));
            this.cine.agregarSala(new Sala(2, "Fuego contra fuego", 8, 10));
            this.cine.agregarSala(new Sala(3, "Scarface", 15, 10));

            this.cine.agregarCliente(new Cliente("Usuario Ejemplo", "user@cine.com", "1234"));
        }
    }

    public void guardarDatos() {
        PersistenciaDatos.guardarCine(cine, ARCHIVO_PERSISTENCIA);
    }

    public void intentoDeLogin(String email, String contrasena) {
        Optional<Cliente> clienteOpt = cine.login(email, contrasena);
        if (clienteOpt.isPresent()) {
            this.clienteActual = clienteOpt.get();
            Alerta.mostrarAlerta("Login Exitoso", "Bienvenido, " + clienteActual.getNombre(), Alerta.Tipo.INFO);
            aplicacion.mostrarSeleccionSala();
        } else {
            Alerta.mostrarAlerta("Error de Login", "Email o contraseña incorrectos.", Alerta.Tipo.ERROR);
        }
    }

    public void intentoDeRegistro(String nombre, String email, String contrasena) {
        if (nombre.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
            Alerta.mostrarAlerta("Error de Registro", "Todos los campos son obligatorios.", Alerta.Tipo.ERROR);
            return;
        }

        if (cine.buscarCliente(email).isPresent()) {
            Alerta.mostrarAlerta("Error de Registro", "El email ya está en uso.", Alerta.Tipo.ERROR);
        } else {
            Cliente nuevoCliente = new Cliente(nombre, email, contrasena);
            cine.agregarCliente(nuevoCliente);
            Alerta.mostrarAlerta("Registro Exitoso", "Cliente registrado. Ahora puede iniciar sesión.", Alerta.Tipo.INFO);
        }
    }

    public void seleccionarSala(Sala sala) {
        System.out.println("Sala seleccionada: " + sala.getPelicula());
        aplicacion.mostrarButacas(sala);
    }

    public void comprarEntrada(Sala sala, int filaIdx, int numIdx) {
        if (clienteActual == null) {
            Alerta.mostrarAlerta("Error", "Debe iniciar sesion para comprar.", Alerta.Tipo.ERROR);
            return;
        }

        Butaca butaca = sala.getButacas()[filaIdx][numIdx];

        if (butaca.getEstado() == EstadoButaca.OCUPADA) {
             Alerta.mostrarAlerta("Error", "Esta butaca ya esta ocupada.", Alerta.Tipo.ERROR);
             return;
        }
        

        if (butaca.ocupar()) {
            Entrada nuevaEntrada = new Entrada(clienteActual, sala, butaca);
            cine.agregarEntrada(nuevaEntrada);
            guardarDatos(); 

            String mensajeTicket = "Pelicula: " + sala.getPelicula() + "\n" +
                                   "Sala: " + sala.getNumero() + "\n" +
                                   "Ubicacion: Fila " + butaca.getFila() + " - Asiento " + butaca.getNumero() + "\n" +
                                   "Cliente: " + clienteActual.getNombre();

            System.out.println("Entrada comprada: " + nuevaEntrada);
            Alerta.mostrarAlerta("Compra Exitosa", "¡Disfrute la función!\n\n" + mensajeTicket, Alerta.Tipo.INFO);

            aplicacion.mostrarSeleccionSala();
        } else {
             Alerta.mostrarAlerta("Error", "La butaca no se pudo ocupar. Intente de nuevo.", Alerta.Tipo.ERROR);
        }
    }
    
    public List<Entrada> getEntradasUsuarioLogueado() {
        if (clienteActual == null) {
            return List.of();
        }
        
        return cine.getEntradasVendidas().stream()
                .filter(e -> e.getCliente().getEmail().equals(clienteActual.getEmail()))
                .collect(Collectors.toList());
    }

    public Cine getCine() {
        return cine;
    }
}