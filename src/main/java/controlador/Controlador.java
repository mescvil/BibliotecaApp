package controlador;

import accesoDatos.Modelo;
import accesoDatos.ModeloArchivo;
import command.OrdenAddAlquiler;
import command.OrdenAddLibro;
import command.OrdenAddUsuario;
import command.OrdenDevolverAlquiler;
import excepciones.CargaDatosException;
import excepciones.DuplicadoException;
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
import observer.ObservadorUsuario;
import vista.DialogoArchivos;

/**
 * @author Escoz
 */
public class Controlador implements ObservadorLibros, ObservadorAlquiler, ObservadorUsuario {

    private final Modelo modelo;
    private final VistaPrincipal vista;

    public Controlador() {
        modelo = new ModeloArchivo();
        ((ModeloArchivo) modelo).suscribirseLibro(this);
        ((ModeloArchivo) modelo).suscribirseAlquiler(this);
        ((ModeloArchivo) modelo).suscribirseUsuario(this);

        vista = new VistaPrincipal(this);
        vista.setVisible(true);

        /* Dialgo para cargar los archivos */
        new DialogoArchivos(vista, true, this).setVisible(true);
        vista.requestFocus();
    }

    /* ================== MODELO DE ARCHIVOS ================== */
    public void cargaDatosNotify() throws CargaDatosException {

        modelo.cargaUsuarios();
        modelo.cargaLibros();
        modelo.cargaAlquileres();

        cambioUsuario();
        cambioLibro();
        cambioAlquiler();
        vista.cambioEnPilaLibro();
    }

    public void setRutaFicheros(String ruta) {
        ((ModeloArchivo) modelo).setRuta(ruta);
    }

    public void creaFicherosDatos(String ruta) {
        ((ModeloArchivo) modelo).creaFicherosDatos(ruta);
    }

    public String getRutaAnterior() {
        return ((ModeloArchivo) modelo).getRutaAnterior();
    }

    public void autodestruccion() throws CargaDatosException, GuardaDatosException {
        ((ModeloArchivo) modelo).autodestruccion();
        cargaDatosNotify();
    }

    public void creaNuevaRuta(String ruta) throws CargaDatosException, GuardaDatosException {
        ((ModeloArchivo) modelo).creaNuevaRuta(ruta);
        cargaDatosNotify();
    }

    /* ================== MODELO GENERAL================== */
    public void guardaUsuario(Usuario usuario) throws GuardaDatosException, DuplicadoException {
        new OrdenAddUsuario(modelo, usuario).execute();
    }

    public ArrayList<Libro> getLibros() {
        return new ArrayList<>(((ModeloArchivo) modelo).getMap_libros().values());
    }

    public ArrayList<Usuario> getUsuarios() {
        return new ArrayList<>(((ModeloArchivo) modelo).getMap_usuarios().values());
    }

    public void guardaLibro(Libro libro) throws GuardaDatosException, DuplicadoException {
        new OrdenAddLibro(modelo, libro).execute();
    }

    public void guardaAlquiler(Alquiler alquiler) throws GuardaDatosException {
        new OrdenAddAlquiler(modelo, alquiler).execute();
    }

    public void realizaDevolucion(Alquiler alquiler) throws GuardaDatosException {
        new OrdenDevolverAlquiler(modelo, alquiler).execute();
    }

    public ArrayList<Alquiler> getAlquileres() {
        return ((ModeloArchivo) modelo).getLista_alquileres();
    }

    /* ================== BUSQUEDAS ================== */
    @Deprecated
    public ArrayList<Libro> buscaLibroTitulo(String busqueda) {
        ArrayList<Libro> libros_encontrados = new ArrayList<>();

        if (busqueda.isEmpty()) {
            return libros_encontrados;
        }

        for (Libro object : ((ModeloArchivo) modelo).getMap_libros().values()) {
            String titulo = object.getTitulo();

            if ((titulo.toLowerCase()).contains(busqueda.toLowerCase())) {
                libros_encontrados.add(object);
            }
        }

        return libros_encontrados;
    }

    public ArrayList<Usuario> buscaUsuarios(String busqueda) {
        ArrayList<Usuario> usuarios_encontrados = new ArrayList<>();

        if (busqueda.isEmpty()) {
            return usuarios_encontrados;
        }

        for (Usuario o : ((ModeloArchivo) modelo).getMap_usuarios().values()) {

            if (o.getNombreCompleto().toLowerCase().contains(busqueda.toLowerCase())) {
                usuarios_encontrados.add(o);
            }
        }

        return usuarios_encontrados;
    }

    private ArrayList<Usuario> buscaUsuariosArray(HashMap<String, String> busqueda) {
        BusquedaUsuarios contexto;

        ArrayList<Usuario> usuarios_econtrados = new ArrayList<>();

        ArrayList<Usuario> usuarios = new ArrayList<>(((ModeloArchivo) modelo).getMap_usuarios().values());
        ArrayList<Usuario> usuarios_aux = new ArrayList<>(usuarios);

        for (String key : busqueda.keySet()) {

            switch (key) {
                case "nombre" -> {
                    contexto = new BusquedaUsuarios(new BuscaUsuarioNombre());
                    usuarios_econtrados = contexto.busca(usuarios_aux, busqueda.get(key));
                }
                case "apellidos" -> {
                    contexto = new BusquedaUsuarios(new BuscaUsuarioApellidos());
                    usuarios_econtrados = contexto.busca(usuarios_aux, busqueda.get(key));
                }
                case "telefono" -> {
                    contexto = new BusquedaUsuarios(new BuscaUsuarioTelf());
                    usuarios_econtrados = contexto.busca(usuarios_aux, busqueda.get(key));
                }
                case "anio" -> {
                    contexto = new BusquedaUsuarios(new BuscaUsuarioAnio());
                    usuarios_econtrados = contexto.busca(usuarios_aux, busqueda.get(key));
                }
                case "simple" ->
                    usuarios_econtrados = buscaUsuarios(busqueda.get(key));
            }
            usuarios_aux.clear();
            usuarios_aux.addAll(usuarios_econtrados);

        }
        return usuarios_econtrados;
    }

    public void buscaUsuarios(HashMap<String, String> busqueda) {
        vista.actualizaBusquedaUsuarios(buscaUsuariosArray(busqueda));
    }

    public ArrayList<Libro> buscaLibrosArray(HashMap<String, String> busqueda) {
        BusquedaLibros contexto;

        ArrayList<Libro> libros_econtrados = new ArrayList<>();

        ArrayList<Libro> libros = new ArrayList<>(((ModeloArchivo) modelo).getMap_libros().values());
        ArrayList<Libro> libros_aux = new ArrayList<>(libros);

        for (String key : busqueda.keySet()) {

            switch (key) {
                case "titulo" -> {
                    contexto = new BusquedaLibros(new BuscaLibroTitulo());
                    libros_econtrados = contexto.busca(libros_aux, busqueda.get(key));
                }
                case "autor" -> {
                    contexto = new BusquedaLibros(new BuscaLibroAutor());
                    libros_econtrados = contexto.busca(libros_aux, busqueda.get(key));
                }
                case "publicacion" -> {
                    contexto = new BusquedaLibros(new BuscaLibroPublicacion());
                    libros_econtrados = contexto.busca(libros_aux, busqueda.get(key));
                }
                case "simple" ->
                    libros_econtrados = buscaLibroTitulo(busqueda.get(key));
            }
            libros_aux.clear();
            libros_aux.addAll(libros_econtrados);
        }
        return libros_econtrados;
    }

    public void buscaLibros(HashMap<String, String> busqueda) {
        vista.actualizaBusquedaLibros(buscaLibrosArray(busqueda));
    }

    public void buscaAlquileres(HashMap<String, String> busqueda) {
        ArrayList<Alquiler> alquileres_encontrados = new ArrayList<>();
        ArrayList<Libro> libros_econtrados = buscaLibrosArray(busqueda);
        ArrayList<Usuario> usuarios_encontrados = buscaUsuariosArray(busqueda);

        for (Alquiler alquiler : ((ModeloArchivo) modelo).getLista_alquileres()) {
            Libro libro = alquiler.getLibro();
            Usuario usuario = alquiler.getUsuario();

            for (Usuario u : usuarios_encontrados) {
                if (u.equals(usuario) && !alquileres_encontrados.contains(alquiler)) {
                    alquileres_encontrados.add(alquiler);
                }
            }

            for (Libro l : libros_econtrados) {
                if (l.equals(libro) && !alquileres_encontrados.contains(alquiler)) {
                    alquileres_encontrados.add(alquiler);
                }
            }
        }

        vista.actualizaBusquedaAlquiler(alquileres_encontrados);
    }

    public ArrayList<Alquiler> buscaAlquileres(String busqueda) {
        ArrayList<Alquiler> alquileres_encontrados = new ArrayList<>();

        if (busqueda.isBlank()) {
            return alquileres_encontrados;
        }

        for (Alquiler alquiler : ((ModeloArchivo) modelo).getLista_alquileres()) {
            String libro = alquiler.getLibro().toString();
            String usuario = alquiler.getUsuario().toString();

            if (libro.toLowerCase().contains(busqueda) && !alquileres_encontrados.contains(alquiler)) {
                alquileres_encontrados.add(alquiler);
            }

            if (usuario.toLowerCase().contains(busqueda) && !alquileres_encontrados.contains(alquiler)) {
                alquileres_encontrados.add(alquiler);
            }
        }

        return alquileres_encontrados;
    }

    public ArrayList<Alquiler> getInfoAlquileres(String isbn_buscado) {
        ArrayList<Alquiler> alquileres_libro = new ArrayList<>();
        Libro libro_buscado = new Libro(isbn_buscado);

        for (Alquiler alquiler : ((ModeloArchivo) modelo).getLista_alquileres()) {
            if (alquiler.getLibro().equals(libro_buscado)) {
                alquileres_libro.add(alquiler);
            }
        }

        return alquileres_libro;
    }

    public ArrayList<Alquiler> getInfoAlquileres(Libro libro) {
        ArrayList<Alquiler> alquileres_libro = new ArrayList<>();

        for (Alquiler alquiler : ((ModeloArchivo) modelo).getLista_alquileres()) {
            if (alquiler.getLibro().equals(libro)) {
                alquileres_libro.add(alquiler);
            }
        }
        return alquileres_libro;
    }

    @Override
    public void cambioUsuario() {
        vista.cambioEnUsuarios();
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
