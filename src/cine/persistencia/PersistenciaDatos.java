package cine.persistencia;

import cine.modelo.Cine;

import java.io.*;

public class PersistenciaDatos {

    public static void guardarCine(Cine cine, String rutaArchivo) {
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeObject(cine);
            System.out.println("Datos del cine guardados en " + rutaArchivo);

        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Cine cargarCine(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            System.out.println("No se encontro el archivo de persistencia. Se creara uno nuevo al cerrar.");
            return null;
        }

        try (FileInputStream fis = new FileInputStream(rutaArchivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            Cine cine = (Cine) ois.readObject();
            System.out.println("Datos del cine cargados desde " + rutaArchivo);
            return cine;

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}