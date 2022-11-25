/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import accesoDatos.Modelo;
import excepciones.DuplicadoException;
import excepciones.GuardaDatosException;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import modelo.Libro;
import observer.ObservadorPilaLibro;

/**
 *
 * @author theky
 */
public class OrdenAddLibro extends AbstractUndoableEdit {

    private static ObservadorPilaLibro observador;

    private final Modelo receptor;
    private final Libro nuevo_libro;

    public OrdenAddLibro(Modelo receptor, Libro nuevo_libro) {
        this.receptor = receptor;
        this.nuevo_libro = nuevo_libro;
    }

    @Override
    public String getPresentationName() {
        return "añadir un libro";
    }

    @Override
    public void undo() throws CannotUndoException {
        try {
            super.undo();
            receptor.eliminaLibro(nuevo_libro);
            notifica();
        } catch (GuardaDatosException ex) {
            throw new CannotUndoException();
        }
    }

    @Override
    public void redo() throws CannotRedoException {
        try {
            super.redo();
            receptor.guardaLibro(nuevo_libro);
            notifica();
        } catch (GuardaDatosException | DuplicadoException ex) {
            throw new CannotRedoException();
        }
    }

    public void execute() throws GuardaDatosException, DuplicadoException {
        receptor.guardaLibro(nuevo_libro);
        PilaCommand.addOrden(this);

    }

    public static void suscribirse(ObservadorPilaLibro o) {
        observador = o;
    }

    private void notifica() {
        observador.cambioEnPilaLibro();
    }
}
