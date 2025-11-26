package cine.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Catalogo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> elementos;

    public Catalogo() {
        this.elementos = new ArrayList<>();
    }

    public void agregar(T elemento) {
        if (!elementos.contains(elemento)) {
            elementos.add(elemento);
        }
    }
    
    public void registrar(T elemento) {
        elementos.add(elemento);
    }

    public List<T> getTodos() {
        return elementos;
    }
    
    public void vaciar() {
        this.elementos.clear();
    }

    public Optional<T> buscar(Predicate<T> criterio) {
        return elementos.stream()
                        .filter(criterio)
                        .findFirst();
    }
}