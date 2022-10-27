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
import modelo.Usuario;
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

    public void guardaUsuario(Usuario usuario) {
        mapa_personas.put(usuario.getDni(), usuario);
        modelo.guardaPersonas(mapa_personas);
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
            String titulo = libro_object.getTitulo();

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
            String nombre = libro_object.getTitulo();

            if (nombre.equals(titulo)) {
                libro = libro_object;
            }
        }

        return libro;
    }

    public ArrayList<Alquiler> getInfoAlquileres(String isbn_buscado) {
        ArrayList<Alquiler> alquileres_libro = new ArrayList<>();
        Libro libro_buscado = new Libro(isbn_buscado);

        for (Alquiler alquiler : lista_alquileres) {
            if (alquiler.getLibro().equals(libro_buscado)) {
                alquileres_libro.add(alquiler);
            }
        }

        return alquileres_libro;
    }

    public ArrayList<Usuario> getPersonas() {

        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (Object o : mapa_personas.values()) {
            usuarios.add((Usuario) o);
        }

        return usuarios;
    }

    public void guardaLibro(Libro libro) {
        mapa_libros.put(libro.getIsbn(), libro);
    }

    public void guardaAlquiler(Alquiler alquiler) {
        lista_alquileres.add(alquiler);
    }

    public void realizaDevolucion(Alquiler alquiler) {
        alquiler.elimaAlquiler();
        lista_alquileres.remove(alquiler);
        alquiler = null;
    }

}
