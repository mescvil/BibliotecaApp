/*
 */
package observer;

/**
 * @author Escoz
 */
public interface EventoLibro {

    void suscribirseLibro(ObservadorLibros o);

    void notificaCambioLibro();

}
