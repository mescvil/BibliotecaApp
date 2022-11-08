/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
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
