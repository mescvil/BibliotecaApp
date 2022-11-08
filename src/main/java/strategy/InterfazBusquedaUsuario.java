package strategy;

import modelo.Usuario;

import java.util.ArrayList;

/**
 * @author theky
 */
public interface InterfazBusquedaUsuario {

    ArrayList<Usuario> buscaUsuarios(ArrayList<Usuario> lista_usuarios, String busqueda);

}
