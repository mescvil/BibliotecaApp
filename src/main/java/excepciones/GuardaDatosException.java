/*
 */
package excepciones;

/**
 *
 * @author Escoz
 */
public class GuardaDatosException extends Exception {

    public GuardaDatosException(String clase) {
        super("No ha sido posible guardar datos de " + clase);
    }

}
