/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import java.util.ArrayList;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;
import observer.ObservadorPila;

/**
 *
 * @author theky
 */
public class PilaCommand {

    private static final PilaCommand singleton = new PilaCommand();

    private final UndoManager pila_deshacer;
    private final ArrayList<ObservadorPila> observadores;

    private PilaCommand() {
        pila_deshacer = new UndoManager();
        observadores = new ArrayList<>();
    }

    public static void addOrden(AbstractUndoableEdit orden) {
        singleton.pila_deshacer.addEdit(orden);
        singleton.notificaCambio();

    }

    public static void undo() {
        singleton.pila_deshacer.undo();
        singleton.notificaCambio();
    }

    public static void redo() {
        singleton.pila_deshacer.redo();
        singleton.notificaCambio();
    }

    public static boolean canUndo() {
        return singleton.pila_deshacer.canUndo();
    }

    public static boolean canRedo() {
        return singleton.pila_deshacer.canRedo();
    }

    public static String nombreOrdenDeshacer() {
        return "Deshacer " + singleton.pila_deshacer.getPresentationName();
    }

    public static String nombreOrdenRehacer() {
        return "Rehacer " + singleton.pila_deshacer.getPresentationName();
    }

    private void notificaCambio() {
        for (ObservadorPila observador : observadores) {
            observador.cambioPila();
        }
    }

    public static void suscribirse(ObservadorPila observador) {
        singleton.observadores.add(observador);
        observador.cambioPila();
    }

    public static void eliminaObservadorPilaDeshacer(ObservadorPila observador) {
        singleton.observadores.remove(observador);
    }
}
