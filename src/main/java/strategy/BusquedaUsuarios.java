package strategy;

import modelo.Usuario;

import java.util.ArrayList;

/**
 * @author theky
 */
public class BusquedaUsuarios {

    private final InterfazBusquedaUsuario estrategia;

    public BusquedaUsuarios(InterfazBusquedaUsuario estrategia) {
        this.estrategia = estrategia;
    }

    public ArrayList<Usuario> busca(ArrayList<Usuario> lista_usuarios, String busqueda) {
        return estrategia.buscaUsuarios(lista_usuarios, busqueda);
    }
}
