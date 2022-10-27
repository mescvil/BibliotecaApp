/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Map;

/**
 *
 * @author Escoz
 */
public class Persona {

    /* --------------- ATRIBUTOS ---------------
    - dni
    - nombre 
    - apellido_1
    - apellido_2
    - telefono
    - correo
    - fecha_nacimiento
     */
    private Map detalles_persona;

    // --------------- CONSTRUCTORES ---------------
    public Persona() {
    }

    public Persona(Map detalles_persona) {
        this.detalles_persona = detalles_persona;
    }

    @Override
    public String toString() {
        return (String) detalles_persona.get("dni");
    }

    public String getNombreCompleto() {
        String nombre = (String) getDetalles_persona().get("nombre");
        String apellido_1 = (String) getDetalles_persona().get("apellido_1");
        String apellido_2 = (String) getDetalles_persona().get("apellido_2");

        return String.format("%s %s %s", nombre, apellido_1, apellido_2);
    }

    public Map getDetalles_persona() {
        return detalles_persona;
    }

}
