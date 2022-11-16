/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package observer;

/**
 *
 * @author theky
 */
public interface EventoUsuario {

    void suscribirseUsuario(ObservadorUsuario o);

    void notificaCambioUsuario();
}
