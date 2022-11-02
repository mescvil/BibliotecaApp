/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import excepciones.CargaDatosException;
import excepciones.GuardaDatosException;
import java.util.ArrayList;
import java.util.Map;
import modelo.Alquiler;
import modelo.Libro;
import modelo.Modelo;
import modelo.ModeloSimple;
import modelo.ObservadorLibros;
import modelo.Usuario;
import vista.VistaPrincipal;

/**
 *
 * @author Escoz
 */
public class Controlador implements ObservadorLibros, EventoAlquiler {

    private final Modelo modelo;
    private final VistaPrincipal vista;

    private Map mapa_personas;
    private Map mapa_libros;
    private ArrayList<Alquiler> lista_alquileres;

    private ArrayList<ObservadorAlquiler> lista_observadoresAlquiler;

    public Controlador() {
        modelo = new ModeloSimple();
        ((ModeloSimple) modelo).suscribirse(this);

        try {
            mapa_personas = modelo.cargaPersonas();
            mapa_libros = modelo.cargaLibros();
            lista_alquileres = modelo.cargaAlquileres();
            lista_observadoresAlquiler = new ArrayList<>();

        } catch (CargaDatosException ex) {
            // POR VER
            ex.printStackTrace();
        }

        vista = new VistaPrincipal(this);
        vista.setVisible(true);

    }

    public void guardaUsuario(Usuario usuario) throws GuardaDatosException {
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

    public ArrayList<Usuario> buscaUsuarios(String busqueda) {
        ArrayList<Usuario> usuarios_encontrados = new ArrayList<>();

        if (busqueda.isEmpty()) {
            return usuarios_encontrados;
        }

        for (Object o : mapa_personas.values()) {
            Usuario usuario = (Usuario) o;

            if (usuario.getNombreCompleto().toLowerCase().contains(busqueda.toLowerCase())) {
                usuarios_encontrados.add(usuario);
            }
        }

        return usuarios_encontrados;
    }

    public ArrayList<Alquiler> buscaAlquileres(String busqueda, boolean buscaLibro) {
        ArrayList<Alquiler> alquileres_encontrados = new ArrayList<>();

        if (busqueda.isBlank()) {
            return alquileres_encontrados;
        }

        for (Alquiler alquiler : lista_alquileres) {

            if (!buscaLibro) {
                Usuario usuario = alquiler.getPersona();

                if (usuario.getNombreCompleto().toLowerCase().contains(busqueda.toLowerCase())) {
                    alquileres_encontrados.add(alquiler);
                }
            } else {
                Libro libro = alquiler.getLibro();

                if (libro.getTitulo().toLowerCase().contains(busqueda.toLowerCase())) {
                    alquileres_encontrados.add(alquiler);
                }
            }
        }

        return alquileres_encontrados;
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

    public void guardaLibro(Libro libro) throws GuardaDatosException {
        mapa_libros.put(libro.getIsbn(), libro);
        modelo.guardaLibros(mapa_libros);
    }

    public void guardaAlquiler(Alquiler alquiler) throws GuardaDatosException {
        lista_alquileres.add(alquiler);
        modelo.guardaAlquileres(lista_alquileres);
        notificaCambioAlquiler();
    }

    public void realizaDevolucion(Alquiler alquiler) throws GuardaDatosException {
        alquiler.elimaAlquiler();
        lista_alquileres.remove(alquiler);
        modelo.guardaAlquileres(lista_alquileres);
        notificaCambioAlquiler();
    }

    public ArrayList<Alquiler> getAlquileres() {
        return lista_alquileres;
    }

    @Override
    public void cambioLibro() {
        vista.cambioEnListaLibros();
    }

    @Override
    public void suscribirse(ObservadorAlquiler o) {
        lista_observadoresAlquiler.add(o);
    }

    @Override
    public void notificaCambioAlquiler() {
        for (ObservadorAlquiler observadorAlquiler : lista_observadoresAlquiler) {
            observadorAlquiler.cambioDeAlquiler();
        }
    }

}
