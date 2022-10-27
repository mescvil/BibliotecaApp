package modelo;

import excepciones.SinEjemplaresException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author Escoz
 */
public class Alquiler implements Serializable {

    // -------------- ATRIBUTOS ---------------
    private Libro libro;
    private Usuario persona;
    private GregorianCalendar fecha_limite;
    private int n_ejemplares;

    // -------------- CONSTRUCTORES ---------------
    public Alquiler(Libro libro, Usuario persona) throws SinEjemplaresException {
        setLibro(libro);
        setPersona(persona);
        creaAlquiler(n_ejemplares = 1);

        fecha_limite = new GregorianCalendar();
        fecha_limite.add(GregorianCalendar.DAY_OF_MONTH, 15);
    }

    public Alquiler(Libro libro, Usuario persona, GregorianCalendar fecha_limite, int n_ejemplares) throws SinEjemplaresException {
        setLibro(libro);
        setPersona(persona);
        setFecha_limite(fecha_limite);
        creaAlquiler(n_ejemplares);
        setN_ejemplares(n_ejemplares);
    }

    // -------------- METODOS---------------
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setCalendar(fecha_limite);
        String fecha_entrega = dateFormat.format(fecha_limite.getTime());

        return String.format("(%d) %s %s, %s - %s", n_ejemplares, persona.getApellido_1(),
                persona.getApellido_2(),
                persona.getNombre(), fecha_entrega);
    }

    private void creaAlquiler(int n_ejemplares) throws SinEjemplaresException {
        libro.eliminaEjemplar(n_ejemplares);
    }

    public void elimaAlquiler() {
        libro.aniadeEjemplar(n_ejemplares);
    }

    @Override
    public boolean equals(Object obj) {
        Alquiler comparado = (Alquiler) obj;

        if (!comparado.getLibro().equals(libro)) {
            return false;
        }
        if (!comparado.getPersona().equals(persona)) {
            return false;
        }
        if (!comparado.getFecha_limite().equals(getFecha_limite())) {
            return false;
        }
        if (comparado.getN_ejemplares() != getN_ejemplares()) {
            return false;
        }

        return true;
    }

    // -------------- GETTERS & SETTERS ---------------
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getPersona() {
        return persona;
    }

    public void setPersona(Usuario persona) {
        this.persona = persona;
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

}
