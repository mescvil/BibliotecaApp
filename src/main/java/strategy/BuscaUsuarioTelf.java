package strategy;

import modelo.Usuario;

import java.util.ArrayList;

/**
 * @author theky
 */
public class BuscaUsuarioTelf implements InterfazBusquedaUsuario {

    @Override
    public ArrayList<Usuario> buscaUsuarios(ArrayList<Usuario> lista_usuarios, String busqueda) {
        ArrayList<Usuario> usuarios_encontrados = new ArrayList<>();

        if (busqueda.isEmpty()) {
            return usuarios_encontrados;
        }
        for (Usuario usuario : lista_usuarios) {
            if (usuario.getTelefono().toLowerCase().contains(busqueda.toLowerCase())) {
                usuarios_encontrados.add(usuario);
            }
        }
        return usuarios_encontrados;
    }

}
