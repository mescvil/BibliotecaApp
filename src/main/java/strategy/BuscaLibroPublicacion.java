package strategy;

import modelo.Libro;

import java.util.ArrayList;

/**
 * @author theky
 */
public class BuscaLibroPublicacion implements InterfazBusquedaLibro {

    @Override
    public ArrayList<Libro> buscaLibro(ArrayList<Libro> lista_libros, String busqueda) {
        ArrayList<Libro> libros_encontrados = new ArrayList<>();

        if (busqueda.isEmpty()) {
            return libros_encontrados;
        }
        for (Libro libro : lista_libros) {
            if (libro.getAnio_publicacion().equalsIgnoreCase(busqueda)) {
                libros_encontrados.add(libro);
            }
        }
        return libros_encontrados;
    }

}
