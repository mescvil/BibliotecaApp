/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @author Escoz
 */
public class Usuario implements Serializable {

    // -------------- ATRIBUTOS ---------------
    private String dni;
    private String nombre;
    private String apellido_1;
    private String apellido_2;
    private String telefono;
    private String correo;
    private GregorianCalendar fecha_nacimiento;

    // --------------- CONSTRUCTORES ---------------
    public Usuario(String dni, String nombre, String apellido_1, String apellido_2, String telefono, String correo, GregorianCalendar fecha_nacimiento) {
        setDni(dni);
        setNombre(nombre);
        setApellido_1(apellido_1);
        setApellido_2(apellido_2);
        setTelefono(telefono);
        setCorreo(correo);
        setFecha_nacimiento(fecha_nacimiento);

    }

    public Usuario() {
        setFecha_nacimiento(new GregorianCalendar());
    }

    // --------------- MÉTODOS ---------------
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Usuario)) {
            return false;
        }
        return this.getDni().equalsIgnoreCase(((Usuario) obj).getDni());
    }

    public String getNombreCompleto() {
        return String.format("%s %s %s", nombre, apellido_1, apellido_2);
    }

    @Override
    public String toString() {
        return getNombreCompleto();
    }

    // --------------- GETTERS & SETTERS ---------------
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_1() {
        return apellido_1;
    }

    public void setApellido_1(String apellido_1) {
        this.apellido_1 = apellido_1;
    }

    public String getApellido_2() {
        return apellido_2;
    }

    public void setApellido_2(String apellido_2) {
        this.apellido_2 = apellido_2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public GregorianCalendar getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(GregorianCalendar fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

}
