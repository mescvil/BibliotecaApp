package accesoDatos;

import excepciones.GuardaDatosException;
import excepciones.SinEjemplaresException;
import modelo.Alquiler;
import modelo.Libro;
import modelo.Usuario;
import observer.EventoAlquiler;
import observer.EventoLibro;
import observer.ObservadorAlquiler;
import observer.ObservadorLibros;

import java.util.*;

/**
 * @author Escoz
 */
@Deprecated
public class ModeloSimple implements Modelo, EventoLibro, EventoAlquiler {

    private HashMap<String, Libro> libros;
    private HashMap<String, Usuario> personas;

    private final ArrayList<ObservadorLibros> lista_observadoresLibro;
    private final ArrayList<ObservadorAlquiler> lista_observadoresAlquiler;

    public ModeloSimple() {
        libros = new HashMap<>();
        personas = new HashMap<>();
        lista_observadoresLibro = new ArrayList<>();
        lista_observadoresAlquiler = new ArrayList<>();
    }

    @Override
    public Map<String, Libro> cargaLibros() {
        Libro libro;
        HashMap<String, Libro> mapa_libros = new HashMap<>();

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
    public HashMap<String, Usuario> cargaUsuarios() {

        Usuario persona;
        HashMap<String, Usuario> mapa_personas = new HashMap<>();

        persona = new Usuario("26512969H", "Miguel", "Escoz", "Vilches", "630919015",
                "mescvil@gmail.com", new GregorianCalendar(1997, Calendar.MAY, 28));
        mapa_personas.put(persona.getDni(), persona);

        persona = new Usuario("00000000A", "Jose Luis", "Torre", "Alva", "953000000",
                "admin@google.es", new GregorianCalendar(1900, Calendar.FEBRUARY, 1));
        mapa_personas.put(persona.getDni(), persona);

        return this.personas = mapa_personas;
    }

    @Override
    public ArrayList<Alquiler> cargaAlquileres() {
        try {
            ArrayList<Alquiler> alquileres = new ArrayList<>();

            alquileres.add(new Alquiler(libros.get("0000001"), personas.get("26512969H")));
            alquileres.add(new Alquiler(libros.get("0000000"), personas.get("00000000A")));

            return alquileres;

        } catch (SinEjemplaresException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void guardaLibros(Map<String, Libro> libros) {
        notificaCambioLibro();
    }

    @Override
    public void guardaUsuarios(Map<String, Usuario> usuario) {
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

    @Override
    public void guardaLibro(Libro libro) throws GuardaDatosException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminaLibro(Libro libro) throws GuardaDatosException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardaUsuario(Usuario usuario) throws GuardaDatosException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminaUsuario(Usuario usuario) throws GuardaDatosException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void guardaAlquiler(Alquiler alquiler) throws GuardaDatosException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminaAlquiler(Alquiler alquiler) throws GuardaDatosException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
