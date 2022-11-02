/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import observer.ObservadorLibros;
import observer.EventoLibro;
import excepciones.SinEjemplaresException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import observer.EventoAlquiler;
import observer.ObservadorAlquiler;

/**
 *
 * @author Escoz
 */
public class ModeloSimple implements Modelo, EventoLibro, EventoAlquiler {

    private HashMap<String, Libro> libros;
    private HashMap<String, Usuario> personas;

    private ArrayList<ObservadorLibros> lista_observadoresLibro;
    private ArrayList<ObservadorAlquiler> lista_observadoresAlquiler;

    public ModeloSimple() {
        libros = new HashMap<>();
        personas = new HashMap<>();
        lista_observadoresLibro = new ArrayList<>();
        lista_observadoresAlquiler = new ArrayList<>();
    }

    @Override
    public HashMap<String, Libro> cargaLibros() {
        Libro libro;
        HashMap<String, Libro> mapa_libros = new HashMap();

        libro = new Libro("0000000", "La Fortaleza Digital", "Dan Brown", "1998", 1);
        mapa_libros.put(libro.getIsbn(), libro);

        libro = new Libro("0000001", "Métodos computacionales en álgebra", "Juan Francisco Ruiz",
                "2020", 4);
        mapa_libros.put(libro.getIsbn(), libro);

        libro = new Libro("0000002", "El Símbolo Perdido", "Dan Brown", "2009",
                3);
        mapa_libros.put(libro.getIsbn(), libro);

        return this.libros = mapa_libros;
    }

    @Override
    public HashMap<String, Usuario> cargaPersonas() {

        Usuario persona;
        HashMap<String, Usuario> mapa_personas = new HashMap();

        persona = new Usuario("26512969H", "Miguel", "Escoz", "Vilches", "630919015",
                "mescvil@gmail.com", new GregorianCalendar(1997, 4, 28));
        mapa_personas.put(persona.getDni(), persona);

        persona = new Usuario("00000000A", "Jose Luis", "Torre", "Alva", "953000000",
                "admin@google.es", new GregorianCalendar(1900, 1, 1));
        mapa_personas.put(persona.getDni(), persona);

        return this.personas = mapa_personas;
    }

    @Override
    public ArrayList cargaAlquileres() {
        try {
            ArrayList<Alquiler> alquileres = new ArrayList<>();

            alquileres.add(new Alquiler(libros.get("0000001"), personas.get("26512969H")));
            alquileres.add(new Alquiler(libros.get("0000000"), personas.get("00000000A")));

            return alquileres;

        } catch (SinEjemplaresException ex) {
            ex.printStackTrace();
        }
        return new ArrayList();
    }

    @Override
    public void guardaLibros(Map libros) {
        notificaCambioLibro();
    }

    @Override
    public void guardaPersonas(Map libros) {
        // TODO
    }

    @Override
    public void guardaAlquileres(ArrayList<Alquiler> alquileres) {
        notificaCambioAlquiler();
    }

    @Override
    public void suscribirseLibro(ObservadorLibros o) {
        lista_observadoresLibro.add(o);
    }

    @Override
    public void notificaCambioLibro() {
        for (ObservadorLibros observadorLibros : lista_observadoresLibro) {
            observadorLibros.cambioLibro();
        }
    }

    @Override
    public void suscribirseAlquiler(ObservadorAlquiler o) {
        lista_observadoresAlquiler.add(o);
    }

    @Override
    public void notificaCambioAlquiler() {
        for (ObservadorAlquiler observadorAlquiler : lista_observadoresAlquiler) {
            observadorAlquiler.cambioAlquiler();
        }
    }

}
