package strategy;

import modelo.Libro;

import java.util.ArrayList;

/**
 * @author theky
 */
public interface InterfazBusquedaLibro {

    ArrayList<Libro> buscaLibro(ArrayList<Libro> lista_libros, String busqueda);

}
