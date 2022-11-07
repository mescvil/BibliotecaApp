/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import java.util.ArrayList;
import modelo.Libro;

/**
 *
 * @author theky
 */
public class BuscaLibroAutor implements InterfazBusquedaLibro {

    @Override
    public ArrayList<Libro> buscaLibro(ArrayList<Libro> lista_libros, String busqueda) {
        ArrayList<Libro> libros_encontrados = new ArrayList<>();

        if (busqueda.isEmpty()) {
            return libros_encontrados;
        }
        for (Libro libro : lista_libros) {
            if (libro.getAutor().toLowerCase().contains(busqueda.toLowerCase())) {
                libros_encontrados.add(libro);
            }
        }
        return libros_encontrados;
    }

}
