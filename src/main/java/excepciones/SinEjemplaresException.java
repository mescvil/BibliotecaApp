/*
 */
package excepciones;

/**
 *
 * @author Escoz
 */
public class SinEjemplaresException extends Exception {

    public SinEjemplaresException(String titulo_libro) {
        super("No quedan ejemplares de " + titulo_libro);
    }

}
