/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.Map;
import modelo.Alquiler;
import modelo.Libro;
import modelo.Modelo;
import modelo.ModeloSimple;
import modelo.ModeloArchivo;
import modelo.Persona;
import vista.VistaPrincipal;

/**
 *
 * @author Escoz
 */
public class Controlador {

    private final Modelo modelo;
    private final VistaPrincipal vista;

    private Map mapa_personas;
    private Map mapa_libros;
    private ArrayList<Alquiler> lista_alquileres;

    public Controlador() {
        modelo = new ModeloSimple();

        mapa_personas = modelo.cargaPersonas();
        mapa_libros = modelo.cargaLibros();
        lista_alquileres = modelo.cargaAlquileres();

        vista = new VistaPrincipal(this);
        vista.setVisible(true);

    }

    public ArrayList getLibros() {
        ArrayList<Libro> libros = new ArrayList<>();

        for (Object object : mapa_libros.values()) {
            libros.add((Libro) object);
        }
        return libros;
    }

    public ArrayList<Libro> buscaLibroTitulo(String busqueda) {
        ArrayList<Libro> libros_encontrados = new ArrayList<>();

        if (busqueda.isEmpty()) {
            return libros_encontrados;
        }

        for (Object object : mapa_libros.values()) {
            Libro libro_object = (Libro) object;
            String titulo = (String) libro_object.getDetalles_libro().get("titulo");

            if ((titulo.toLowerCase()).contains(busqueda.toLowerCase())) {
                libros_encontrados.add(libro_object);
            }
        }

        return libros_encontrados;
    }

    public Libro getInfoLibro(String titulo) {
        Libro libro = null;

        for (Object object : mapa_libros.values()) {
            Libro libro_object = (Libro) object;
            String nombre = (String) libro_object.getDetalles_libro().get("titulo");

            if (nombre.equals(titulo)) {
                libro = libro_object;
            }
        }
        System.out.println(ModeloArchivo.mapToCsv(libro.getDetalles_libro()));
        return libro;
    }

    public Persona getInfoAlquileres(String isbn_buscado) {
        Persona persona = null;

        for (Alquiler alquiler : lista_alquileres) {
            String isbn_alquiler = (String) alquiler.getDetalles_alquileres().get("isbn");
            String dni_alquilado = (String) alquiler.getDetalles_alquileres().get("dni");

            if (isbn_alquiler.equals(isbn_buscado)) {
                persona = (Persona) mapa_personas.get(dni_alquilado);
                return persona;
            }
        }
        return persona;
    }

}
