/*
 */
package excepciones;

/**
 *
 * @author Escoz
 */
public class DuplicadoException extends Exception {
    
    public DuplicadoException(String clase) {
        super(clase + " duplicado");
    }
    
}
