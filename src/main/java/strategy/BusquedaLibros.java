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
public class BusquedaLibros {

    private InterfazBusquedaLibro estrategia;

    public BusquedaLibros(InterfazBusquedaLibro estrategia) {
        this.estrategia = estrategia;
    }

    public ArrayList<Libro> busca(ArrayList<Libro> lista_libros, String busqueda) {
        return estrategia.buscaLibro(lista_libros, busqueda);
    }
}
