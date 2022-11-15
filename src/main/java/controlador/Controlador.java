package controlador;

import accesoDatos.Modelo;
import accesoDatos.ModeloArchivo;
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
import vista.DialogoArchivos;

/**
 * @author Escoz
 */
public class Controlador implements ObservadorLibros, ObservadorAlquiler {

    private final Modelo modelo;
    private final VistaPrincipal vista;

    private Map<String, Usuario> mapa_usuarios;
    private Map<String, Libro> mapa_libros;
    private ArrayList<Alquiler> lista_alquileres;

    public Controlador() {
        modelo = new ModeloArchivo();
        ((ModeloArchivo) modelo).suscribirseLibro(this);
        ((ModeloArchivo) modelo).suscribirseAlquiler(this);

        mapa_usuarios = new HashMap<>();
        mapa_libros = new HashMap<>();
        lista_alquileres = new ArrayList<>();

        vista = new VistaPrincipal(this);
        vista.setVisible(true);

        /* Dialgo para cargar los archivos */
        new DialogoArchivos(vista, true, this).setVisible(true);
    }

    /* ================== MODELO DE ARCHIVOS ================== */
    public void cargaDatosNotify() throws CargaDatosException {

        mapa_usuarios = modelo.cargaUsuarios();
        mapa_libros = modelo.cargaLibros();
        lista_alquileres = modelo.cargaAlquileres();

        cambioUsuario();
        cambioLibro();
        cambioAlquiler();
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
    public void guardaUsuario(Usuario usuario) throws GuardaDatosException {
        mapa_usuarios.put(usuario.getDni(), usuario);
        modelo.guardaPersonas(mapa_usuarios);
    }

    public ArrayList<Libro> getLibros() {
        return new ArrayList<>(mapa_libros.values());
    }

    public ArrayList<Usuario> getUsuarios() {
        return new ArrayList<>(mapa_usuarios.values());
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

    /* ================== BUSQUEDAS ================== */
    public ArrayList<Libro> buscaLibroTitulo(String busqueda) {
        ArrayList<Libro> libros_encontrados = new ArrayList<>();

        if (busqueda.isEmpty()) {
            return libros_encontrados;
        }

        for (Libro object : mapa_libros.values()) {
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

        for (Usuario o : mapa_usuarios.values()) {

            if (o.getNombreCompleto().toLowerCase().contains(busqueda.toLowerCase())) {
                usuarios_encontrados.add(o);
            }
        }

        return usuarios_encontrados;
    }

    private ArrayList<Usuario> buscaUsuariosArray(HashMap<String, String> busqueda) {
        BusquedaUsuarios contexto;

        ArrayList<Usuario> usuarios_econtrados = new ArrayList<>();

        ArrayList<Usuario> usuarios = new ArrayList<>(mapa_usuarios.values());
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

    private ArrayList<Libro> buscaLibrosArray(HashMap<String, String> busqueda) {
        BusquedaLibros contexto;

        ArrayList<Libro> libros_econtrados = new ArrayList<>();

        ArrayList<Libro> libros = new ArrayList<>(mapa_libros.values());
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

        for (Alquiler alquiler : lista_alquileres) {
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

        for (Alquiler alquiler : lista_alquileres) {
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
