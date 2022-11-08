/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import modelo.Usuario;

import java.util.ArrayList;

/**
 *
 * @author theky
 */
public class BusquedaUsuarios {

    private InterfazBusquedaUsuario estrategia;

    public BusquedaUsuarios(InterfazBusquedaUsuario estrategia) {
        this.estrategia = estrategia;
    }

    public ArrayList<Usuario> busca(ArrayList<Usuario> lista_usuarios, String busqueda) {
        return estrategia.buscaUsuarios(lista_usuarios, busqueda);
    }
}
