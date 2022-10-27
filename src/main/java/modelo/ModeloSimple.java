/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Escoz
 */
public class ModeloSimple implements Modelo {

    @Override
    public Map cargaLibros() {
        Libro libro;
        Map detalles_libros = new HashMap();
        Map mapa_libros = new HashMap();

        detalles_libros.put("isbn", "0000000");
        detalles_libros.put("titulo", "El libro troll");
        detalles_libros.put("autor", "El Rubius");
        detalles_libros.put("fecha_publicacion", "2014");
        detalles_libros.put("n_ejemplares", 1);

        libro = new Libro(detalles_libros);
        mapa_libros.put(detalles_libros.get("isbn"), libro);

        detalles_libros = new HashMap();
        detalles_libros.put("isbn", "0000001");
        detalles_libros.put("titulo", "Métodos computacionales en álgebra");
        detalles_libros.put("autor", "Juan Francisco Ruiz");
        detalles_libros.put("fecha_publicacion", "2020");
        detalles_libros.put("n_ejemplares", 1);

        libro = new Libro(detalles_libros);
        mapa_libros.put(detalles_libros.get("isbn"), libro);

        detalles_libros = new HashMap();
        detalles_libros.put("isbn", "0000002");
        detalles_libros.put("titulo", "Wigetta: un viaje mágico");
        detalles_libros.put("autor", "Samuel de Luque Batuecas");
        detalles_libros.put("fecha_publicacion", "2016");
        detalles_libros.put("n_ejemplares", 1);

        libro = new Libro(detalles_libros);
        mapa_libros.put(detalles_libros.get("isbn"), libro);

        return mapa_libros;
    }

    @Override
    public Map cargaPersonas() {

        Persona persona;
        Map detalles_persona = new HashMap();
        Map mapa_personas = new HashMap();

        detalles_persona.put("dni", "26512969H");
        detalles_persona.put("nombre", "Miguel");
        detalles_persona.put("apellido_1", "Escoz");
        detalles_persona.put("apellido_2", "Vilches");
        detalles_persona.put("telefono", "630919015");
        detalles_persona.put("correo", "mescvil@gmail.com");
        detalles_persona.put("fecha_nacimiento", "28/04/1997");

        persona = new Persona(detalles_persona);
        mapa_personas.put(detalles_persona.get("dni"), persona);

        detalles_persona = new HashMap();
        detalles_persona.put("dni", "00000000A");
        detalles_persona.put("nombre", "Ramón");
        detalles_persona.put("apellido_1", "Escoz");
        detalles_persona.put("apellido_2", "Moreno");
        detalles_persona.put("telefono", "000000000");
        detalles_persona.put("correo", "rescmor@gmail.com");
        detalles_persona.put("fecha_nacimiento", "23/06/1964");

        persona = new Persona(detalles_persona);
        mapa_personas.put(detalles_persona.get("dni"), persona);

        return mapa_personas;
    }

    @Override
    public ArrayList cargaAlquileres() {

        ArrayList<Alquiler> lista_alquileres = new ArrayList<>();
        Map detalles_alquiler = new HashMap();
        Alquiler alquiler;

        detalles_alquiler.put("dni", "26512969H");
        detalles_alquiler.put("isbn", "0000001");

        alquiler = new Alquiler(detalles_alquiler);
        lista_alquileres.add(alquiler);
        
        detalles_alquiler = new HashMap();
        detalles_alquiler.put("dni", "00000000A");
        detalles_alquiler.put("isbn", "0000000");

        alquiler = new Alquiler(detalles_alquiler);
        lista_alquileres.add(alquiler);

        return lista_alquileres;
    }

    @Override
    public void guardaLibros(Map libros) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void guardaPersonas(Map libros) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void guardaAlquileres(ArrayList<Alquiler> alquileres) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
