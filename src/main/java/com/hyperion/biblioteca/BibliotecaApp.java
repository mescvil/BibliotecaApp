package com.hyperion.biblioteca;

import com.formdev.flatlaf.FlatLightLaf;
import controlador.Controlador;

import javax.swing.*;

/**
 * @author Escoz
 */
public class BibliotecaApp {

    public static void main(String[] args) {

        try {
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
            UIManager.put("TextComponent.arc", 10);
            UIManager.setLookAndFeel(new FlatLightLaf());

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        new Controlador();

    }
}
