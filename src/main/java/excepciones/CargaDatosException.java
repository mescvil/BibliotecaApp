/*
 */
package excepciones;

/**
 *
 * @author Escoz
 */
public class CargaDatosException extends Exception {

    public CargaDatosException(String clase) {
        super("No es posible cargar los datos de " + clase);
    }

}
