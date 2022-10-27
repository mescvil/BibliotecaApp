/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Map;

/**
 *
 * @author Escoz
 */
public class Alquiler {

    /**
     * - isbn - dni
     */
    private Map detalles_alquileres;

    public Alquiler() {
    }

    public Alquiler(Map detalles_alquileres) {
        this.detalles_alquileres = detalles_alquileres;
    }

    public Map getDetalles_alquileres() {
        return detalles_alquileres;
    }

}
