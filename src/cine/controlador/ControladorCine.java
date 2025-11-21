package cine.controlador;

import cine.Main;
import cine.modelo.*;
import cine.persistencia.PersistenciaDatos;
import cine.vista.Alerta;
import cine.util.PasswordUtil;
import java.util.Optional;
import java.util.List;
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
            crearDatosPrueba();
        }
    }
    
    private void crearDatosPrueba() {
        System.out.println("Creando datos iniciales...");
        this.cine = new Cine();
        this.cine.agregarSala(new Sala(1, "El Padrino", 5, 8, "padrino.png"));
        this.cine.agregarSala(new Sala(2, "Matrix", 6, 6, "matrix.png"));
        this.cine.agregarSala(new Sala(3, "Scarface", 8, 10, "scarface.png"));
        this.cine.agregarSala(new Sala(4, "Terminator", 6, 8, "terminator.png"));
        this.cine.agregarSala(new Sala(5, "Depredator", 6, 6, "predator.png"));
        this.cine.agregarSala(new Sala(6, "Titanic", 8, 10, "titanic.png"));

        String passHash = PasswordUtil.hashearPassword("1234");
        this.cine.agregarCliente(new Cliente("Admin", "admin@cine.com", passHash));
    }

    public void intentoDeRegistro(String nombre, String email, String password) {
        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Alerta.mostrarAlerta("Error de Registro", "Todos los campos son obligatorios.", Alerta.Tipo.ERROR);
            return;
        }

        if (cine.buscarCliente(email).isPresent()) {
            Alerta.mostrarAlerta("Error de Registro", "El email ya está en uso.", Alerta.Tipo.ERROR);
        } else {
            String hash = PasswordUtil.hashearPassword(password);
            Cliente nuevoCliente = new Cliente(nombre, email, hash);
            
            cine.agregarCliente(nuevoCliente);
            guardarDatos();
            
            Alerta.mostrarAlerta("Registro Exitoso", "Cliente registrado.", Alerta.Tipo.INFO);
        }
    }

    public void intentoDeLogin(String email, String passwordIngresada) {
        Optional<Cliente> clienteOpt = cine.buscarCliente(email);
        
        if (clienteOpt.isPresent()) {
            Cliente clienteEncontrado = clienteOpt.get();
            String hashGuardado = clienteEncontrado.getPassword();

            if (PasswordUtil.verificarPassword(passwordIngresada, hashGuardado)) {
                this.clienteActual = clienteEncontrado;
                Alerta.mostrarAlerta("Login Exitoso", "Bienvenido, " + clienteActual.getNombre(), Alerta.Tipo.INFO);
                aplicacion.mostrarSeleccionSala();
            } else {
                Alerta.mostrarAlerta("Error de Login", "Contraseña incorrecta.", Alerta.Tipo.ERROR);
            }
        } else {
            Alerta.mostrarAlerta("Error de Login", "Usuario no encontrado.", Alerta.Tipo.ERROR);
        }
    }


    public void seleccionarSala(Sala sala) {
        System.out.println("Sala seleccionada: " + sala.getPelicula());
        aplicacion.mostrarButacas(sala);
    }

    public void comprarEntrada(Sala sala, int filaIdx, int numIdx) {
        if (clienteActual == null) {
            Alerta.mostrarAlerta("Error", "Debe iniciar sesión para comprar.", Alerta.Tipo.ERROR);
            return;
        }

        Butaca butaca = sala.getButacas()[filaIdx][numIdx];

        if (butaca.getEstado() == EstadoButaca.OCUPADA) {
             Alerta.mostrarAlerta("Error", "Esta butaca ya está ocupada.", Alerta.Tipo.ERROR);
             return;
        }

        if (butaca.ocupar()) {
            Entrada nuevaEntrada = new Entrada(clienteActual, sala, butaca);
            cine.agregarEntrada(nuevaEntrada);
            guardarDatos(); 

            String mensajeTicket = "Pelicula: " + sala.getPelicula() + "\n" +
                                   "Sala: " + sala.getNumero() + "\n" +
                                   "Ubicacion: Fila " + butaca.getFila() + " - Asiento " + butaca.getNumero() + "\n" +
                                   "Cliente: " + clienteActual.getNombre() + "\n\n" +
                                   "Gracias por su compra!";

            Alerta.mostrarAlerta("Compra Exitosa", mensajeTicket, Alerta.Tipo.INFO);
            aplicacion.mostrarSeleccionSala();
        } else {
             Alerta.mostrarAlerta("Error", "La butaca no se pudo ocupar.", Alerta.Tipo.ERROR);
        }
    }

    public Cine getCine() {
        return cine;
    }

    public void guardarDatos() {
        PersistenciaDatos.guardarCine(cine, ARCHIVO_PERSISTENCIA);
    }
    
    public void navegarAMisEntradas() {
        aplicacion.mostrarMisEntradas();
    }

    public List<Entrada> getMisEntradas() {
        if (clienteActual == null) return List.of();
        return cine.getEntradasVendidas().stream()
                .filter(e -> e.getCliente().getEmail().equals(clienteActual.getEmail()))
                .collect(Collectors.toList());
    }
    
    public boolean esAdmin() {
        return clienteActual != null && clienteActual.getEmail().equals("admin@cine.com");
    }
    
    public void reiniciarTodo() {
        cine.reiniciarSistema(); 
        guardarDatos();     
        Alerta.mostrarAlerta("Sistema Reiniciado", "Todas las butacas han sido liberadas y el historial borrado.", Alerta.Tipo.INFO);
        aplicacion.mostrarSeleccionSala();
    }
    
    public void cerrarSesion() {
        this.clienteActual = null;
        aplicacion.mostrarLogin();
    }
}