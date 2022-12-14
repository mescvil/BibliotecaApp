package modelo;

import excepciones.SinEjemplaresException;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import extras.Utilidades;

/**
 * @author Escoz
 */
public class Alquiler implements Serializable {

    // -------------- ATRIBUTOS ---------------
    private Libro libro;
    private Usuario usuario;
    private GregorianCalendar fecha_limite;
    private GregorianCalendar fecha_creacion;
    private int n_ejemplares;

    // -------------- CONSTRUCTORES ---------------
    public Alquiler(Libro libro, Usuario persona) throws SinEjemplaresException {
        setLibro(libro);
        setUsuario(persona);
        creaAlquiler(n_ejemplares = 1);

        fecha_creacion = new GregorianCalendar();
        fecha_limite = new GregorianCalendar();
        fecha_limite.add(GregorianCalendar.DAY_OF_MONTH, 15);
    }

    public Alquiler(Libro libro, Usuario persona, GregorianCalendar fecha_limite, int n_ejemplares) throws SinEjemplaresException {
        setLibro(libro);
        setUsuario(persona);
        setFecha_limite(fecha_limite);
        creaAlquiler(n_ejemplares);
        setN_ejemplares(n_ejemplares);
        fecha_creacion = new GregorianCalendar();
    }

    public Alquiler(Libro libro, Usuario persona, GregorianCalendar fecha_limite, GregorianCalendar fecha_creacion, int n_ejemplares) {
        setLibro(libro);
        setUsuario(persona);
        setFecha_limite(fecha_limite);
        setN_ejemplares(n_ejemplares);
        setFecha_creacion(fecha_creacion);
    }

    // -------------- MÉTODOS---------------
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setCalendar(fecha_limite);
        String fecha_entrega = dateFormat.format(fecha_limite.getTime());

        return String.format("(%d) %s %s, %s - %s", n_ejemplares, usuario.getApellido_1(),
                usuario.getApellido_2(), usuario.getNombre(), fecha_entrega);
    }

    public String toCSV() {
        return String.format("%s,%s,%s,%s,%s", libro.getIsbn(), usuario.getDni(),
                Utilidades.gregorianCalendarToString(fecha_limite), Utilidades.gregorianCalendarToString(fecha_creacion),
                n_ejemplares);
    }

    private void creaAlquiler(int n_ejemplares) throws SinEjemplaresException {
        libro.eliminaEjemplar(n_ejemplares);
    }

    public void elimaAlquiler() {
        libro.aniadeEjemplar(n_ejemplares);
    }

    public void restauraAlquiler() {
        libro.aniadeEjemplar(-n_ejemplares);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Alquiler)) {
            return false;
        }

        Alquiler comparado = (Alquiler) obj;
        if (!comparado.getLibro().equals(libro)) {
            return false;
        }
        if (!comparado.getUsuario().equals(usuario)) {
            return false;
        }
        if (!comparado.getFecha_limite().equals(getFecha_limite())) {
            return false;
        }
        return comparado.getN_ejemplares() == getN_ejemplares();
    }

    // -------------- GETTERS & SETTERS ---------------
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario persona) {
        this.usuario = persona;
    }

    public GregorianCalendar getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(GregorianCalendar fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public void setN_ejemplares(int n_ejemplares) {
        this.n_ejemplares = n_ejemplares;
    }

    public int getN_ejemplares() {
        return n_ejemplares;
    }

    public void setFecha_creacion(GregorianCalendar fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public GregorianCalendar getFecha_creacion() {
        return fecha_creacion;
    }

}
