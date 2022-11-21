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
import modelo.Usuario;

/**
 *
 * @author theky
 */
public class OrdenAddUsuario extends AbstractUndoableEdit {

    private final Modelo receptor;
    private final Usuario nuevo_usuario;

    public OrdenAddUsuario(Modelo receptor, Usuario nuevo_usuario) {
        this.receptor = receptor;
        this.nuevo_usuario = nuevo_usuario;
    }

    @Override
    public String getPresentationName() {
        return "añadir un usuario";
    }

    @Override
    public void undo() throws CannotUndoException {
        try {
            super.undo();
            receptor.eliminaUsuario(nuevo_usuario);
        } catch (GuardaDatosException ex) {
            throw new CannotUndoException();
        }
    }

    @Override
    public void redo() throws CannotRedoException {
        try {
            super.redo();
            receptor.guardaUsuario(nuevo_usuario);
        } catch (GuardaDatosException | DuplicadoException ex) {
            throw new CannotRedoException();
        }
    }

    public void execute() throws GuardaDatosException, DuplicadoException {
        receptor.guardaUsuario(nuevo_usuario);
        PilaCommand.addOrden(this);

    }
}
