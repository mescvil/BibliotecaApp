/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author theky
 */
public class BuscaUsuarioNombre implements InterfazBusquedaUsuario {

    @Override
    public ArrayList<Usuario> buscaUsuarios(ArrayList<Usuario> lista_usuarios, String busqueda) {
        ArrayList<Usuario> usuarios_encontrados = new ArrayList<>();

        if (busqueda.isEmpty()) {
            return usuarios_encontrados;
        }
        for (Usuario usuario : lista_usuarios) {
            if (usuario.getNombre().toLowerCase().contains(busqueda.toLowerCase())) {
                usuarios_encontrados.add(usuario);
            }
        }
        return usuarios_encontrados;
    }

}
