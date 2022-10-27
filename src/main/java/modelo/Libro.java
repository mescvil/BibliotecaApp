/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Escoz
 */
public class Libro {

    /* --------------- ATRIBUTOS ---------------
    - titulo 
    - isbn 
    - autor
    - fecha_publicacion
    - n_ejemplares
     */
    private Map detalles_libro;

    // --------------- CONSTRUCTORES ---------------
    public Libro() {
        detalles_libro = new HashMap();
    }

    public Libro(Map detalles_libro) {
        this.detalles_libro = detalles_libro;
    }

    @Override
    public String toString() {
        return (String) detalles_libro.get("titulo");
    }

    public Map getDetalles_libro() {
        return detalles_libro;
    }

}
