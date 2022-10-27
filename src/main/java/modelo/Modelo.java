/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import excepciones.SinEjemplaresException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Escoz
 */
public abstract interface Modelo {

    public abstract Map cargaLibros();

    public abstract Map cargaPersonas();

    public abstract ArrayList cargaAlquileres();

    public abstract void guardaLibros(Map libros);

    public abstract void guardaPersonas(Map personas);

    public abstract void guardaAlquileres(ArrayList<Alquiler> alquileres);
}
