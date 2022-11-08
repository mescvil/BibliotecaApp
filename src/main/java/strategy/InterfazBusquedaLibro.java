/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package strategy;

import modelo.Libro;

import java.util.ArrayList;

/**
 *
 * @author theky
 */
public interface InterfazBusquedaLibro {

    public ArrayList<Libro> buscaLibro(ArrayList<Libro> lista_libros, String busqueda);

}
