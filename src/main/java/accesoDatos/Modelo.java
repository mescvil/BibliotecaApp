package accesoDatos;

import excepciones.CargaDatosException;
import excepciones.GuardaDatosException;
import modelo.Alquiler;
import modelo.Libro;
import modelo.Usuario;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Escoz
 */
public interface Modelo {

    Map<String, Libro> cargaLibros() throws CargaDatosException;

    Map<String, Usuario> cargaUsuarios() throws CargaDatosException;

    ArrayList<Alquiler> cargaAlquileres() throws CargaDatosException;

    void guardaLibros(Map<String, Libro> libros) throws GuardaDatosException;

    void guardaLibro(Libro libro) throws GuardaDatosException;

    void eliminaLibro(Libro libro) throws GuardaDatosException;

    void actualizaLibros() throws GuardaDatosException;

    void guardaUsuarios(Map<String, Usuario> personas) throws GuardaDatosException;

    void guardaUsuario(Usuario usuario) throws GuardaDatosException;

    void eliminaUsuario(Usuario usuario) throws GuardaDatosException;

    void guardaAlquileres(ArrayList<Alquiler> alquileres) throws GuardaDatosException;

    void guardaAlquiler(Alquiler alquiler) throws GuardaDatosException;

    void eliminaAlquiler(Alquiler alquiler) throws GuardaDatosException;
}
