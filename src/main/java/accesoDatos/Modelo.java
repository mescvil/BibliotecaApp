/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesoDatos;

import excepciones.CargaDatosException;
import excepciones.GuardaDatosException;
import java.util.ArrayList;
import java.util.Map;
import modelo.Alquiler;

/**
 *
 * @author Escoz
 */
public abstract interface Modelo {

    public abstract Map cargaLibros() throws CargaDatosException;

    public abstract Map cargaPersonas() throws CargaDatosException;

    public abstract ArrayList cargaAlquileres() throws CargaDatosException;

    public abstract void guardaLibros(Map libros) throws GuardaDatosException;

    public abstract void guardaPersonas(Map personas) throws GuardaDatosException;

    public abstract void guardaAlquileres(ArrayList<Alquiler> alquileres) throws GuardaDatosException;
}
