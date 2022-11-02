/*
 */
package observer;

/**
 *
 * @author Escoz
 */
public interface EventoAlquiler {

    public void suscribirseAlquiler(ObservadorAlquiler o);

    public void notificaCambioAlquiler();

}
