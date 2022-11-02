/*
 */
package controlador;

/**
 *
 * @author Escoz
 */
public interface EventoAlquiler {

    public void suscribirse(ObservadorAlquiler o);

    public void notificaCambioAlquiler();

}
