/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import accesoDatos.Modelo;
import accesoDatos.ModeloSimple;
import excepciones.CargaDatosException;
import excepciones.GuardaDatosException;
import modelo.Alquiler;
import modelo.Libro;
import modelo.Usuario;
import observer.ObservadorAlquiler;
import observer.ObservadorLibros;
import strategy.*;
import vista.VistaPrincipal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Escoz
 */
public class Controlador implements ObservadorLibros, ObservadorAlquiler {

    private final Modelo modelo;
    private final VistaPrincipal vista;

    private Map mapa_usuarios;
    private Map mapa_libros;
    private ArrayList<Alquiler> lista_alquileres;

    public Controlador() {
        modelo = new ModeloSimple();
        ((ModeloSimple) modelo).suscribirseLibro(this);
        ((ModeloSimple) modelo).suscribirseAlquiler(this);

        try {
            mapa_usuarios = modelo.cargaPersonas();
            mapa_libros = modelo.cargaLibros();
            lista_alquileres = modelo.cargaAlquileres();

        } catch (CargaDatosException ex) {
            // POR VER
            ex.printStackTrace();
        }

        vista = new VistaPrincipal(this);
        vista.setVisible(true);

    }

    public void guardaUsuario(Usuario usuario) throws GuardaDatosException {
        mapa_usuarios.put(usuario.getDni(), usuario);
        modelo.guardaPersonas(mapa_usuarios);
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

        for (Object o : mapa_usuarios.values()) {
            Usuario usuario = (Usuario) o;

            if (usuario.getNombreCompleto().toLowerCase().contains(busqueda.toLowerCase())) {
                usuarios_encontrados.add(usuario);
            }
        }

        return usuarios_encontrados;
    }

    public void buscaUsuarios(HashMap busqueda) {
        BusquedaUsuarios contexto;

        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Usuario> usuarios_aux = new ArrayList<>();
        ArrayList<Usuario> usuarios_econtrados = new ArrayList<>();

        for (Object o : mapa_usuarios.values()) {
            Usuario u = (Usuario) o;
            usuarios.add(u);
        }
        usuarios_aux.addAll(usuarios);

        for (Object key : busqueda.keySet()) {

            switch ((String) key) {
                case "nombre" -> {
                    contexto = new BusquedaUsuarios(new BuscaUsuarioNombre());
                    usuarios_econtrados = contexto.busca(usuarios_aux, (String) busqueda.get(key));
                }
                case "apellidos" -> {
                    contexto = new BusquedaUsuarios(new BuscaUsuarioApellidos());
                    usuarios_econtrados = contexto.busca(usuarios_aux, (String) busqueda.get(key));
                }
                case "telefono" -> {
                    contexto = new BusquedaUsuarios(new BuscaUsuarioTelf());
                    usuarios_econtrados = contexto.busca(usuarios_aux, (String) busqueda.get(key));
                }
                case "anio" -> {
                    contexto = new BusquedaUsuarios(new BuscaUsuarioAnio());
                    usuarios_econtrados = contexto.busca(usuarios_aux, (String) busqueda.get(key));
                }
                case "simple" -> {
                    usuarios_econtrados = buscaUsuarios((String) busqueda.get(key));
                }
                default ->
                    throw new AssertionError();
            }
            usuarios_aux.clear();
            usuarios_aux.addAll(usuarios_econtrados);
        }

        vista.actualizaBusquedaUsuarios(usuarios_econtrados);
    }

    public void buscaLibros(HashMap busqueda) {
        BusquedaLibros contexto;

        ArrayList<Libro> libros = new ArrayList<>();
        ArrayList<Libro> libros_aux = new ArrayList<>();
        ArrayList<Libro> libros_econtrados = new ArrayList<>();

        for (Object o : mapa_libros.values()) {
            Libro l = (Libro) o;
            libros.add(l);
        }
        libros_aux.addAll(libros);

        for (Object key : busqueda.keySet()) {

            switch ((String) key) {
                case "titulo" -> {
                    contexto = new BusquedaLibros(new BuscaLibroTitulo());
                    libros_econtrados = contexto.busca(libros_aux, (String) busqueda.get(key));
                }
                case "autor" -> {
                    contexto = new BusquedaLibros(new BuscaLibroAutor());
                    libros_econtrados = contexto.busca(libros_aux, (String) busqueda.get(key));
                }
                case "publicacion" -> {
                    contexto = new BusquedaLibros(new BuscaLibroPublicacion());
                    libros_econtrados = contexto.busca(libros_aux, (String) busqueda.get(key));
                }
                case "simple" -> {
                    libros_econtrados = buscaLibroTitulo((String) busqueda.get(key));
                }
                default ->
                    throw new AssertionError();
            }
            libros_aux.clear();
            libros_aux.addAll(libros_econtrados);
        }

        vista.actualizaBusquedaLibros(libros_econtrados);
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

    public ArrayList<Alquiler> getInfoAlquileres(Libro libro) {
        ArrayList<Alquiler> alquileres_libro = new ArrayList<>();

        for (Alquiler alquiler : lista_alquileres) {
            if (alquiler.getLibro().equals(libro)) {
                alquileres_libro.add(alquiler);
            }
        }
        return alquileres_libro;
    }

    public ArrayList<Usuario> getPersonas() {

        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (Object o : mapa_usuarios.values()) {
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
    }

    public void realizaDevolucion(Alquiler alquiler) throws GuardaDatosException {
        alquiler.elimaAlquiler();
        lista_alquileres.remove(alquiler);
        modelo.guardaAlquileres(lista_alquileres);
    }

    public ArrayList<Alquiler> getAlquileres() {
        return lista_alquileres;
    }

    @Override
    public void cambioLibro() {
        vista.cambioEnListaLibros();
    }

    @Override
    public void cambioAlquiler() {
        vista.cambioDeAlquiler();
    }

}
