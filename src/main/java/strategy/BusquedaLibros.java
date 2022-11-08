package strategy;

import modelo.Libro;

import java.util.ArrayList;

/**
 * @author theky
 */
public class BusquedaLibros {

    private final InterfazBusquedaLibro estrategia;

    public BusquedaLibros(InterfazBusquedaLibro estrategia) {
        this.estrategia = estrategia;
    }

    public ArrayList<Libro> busca(ArrayList<Libro> lista_libros, String busqueda) {
        return estrategia.buscaLibro(lista_libros, busqueda);
    }
}
