/*
 */
package observer;

/**
 *
 * @author Escoz
 */
public interface EventoLibro {

    public void suscribirseLibro(ObservadorLibros o);

    public void notificaCambioLibro();

}
