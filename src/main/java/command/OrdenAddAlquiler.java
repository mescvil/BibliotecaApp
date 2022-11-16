/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import accesoDatos.Modelo;
import excepciones.GuardaDatosException;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import modelo.Alquiler;

/**
 *
 * @author theky
 */
public class OrdenAddAlquiler extends AbstractUndoableEdit {

    private final Modelo receptor;
    private final Alquiler nuevo_alquiler;

    public OrdenAddAlquiler(Modelo receptor, Alquiler nuevo_libro) {
        this.receptor = receptor;
        this.nuevo_alquiler = nuevo_libro;
    }

    @Override
    public String getPresentationName() {
        return "crear un alquiler";
    }

    @Override
    public void undo() throws CannotUndoException {
        try {
            super.undo();
            receptor.eliminaAlquiler(nuevo_alquiler);
        } catch (GuardaDatosException ex) {
            throw new CannotUndoException();
        }
    }

    @Override
    public void redo() throws CannotRedoException {
        try {
            super.redo();
            nuevo_alquiler.restauraAlquiler();
            receptor.guardaAlquiler(nuevo_alquiler);
        } catch (GuardaDatosException ex) {
            throw new CannotRedoException();
        }
    }

    public void execute() throws GuardaDatosException {
        receptor.guardaAlquiler(nuevo_alquiler);
        PilaCommand.addOrden(this);

    }
}
