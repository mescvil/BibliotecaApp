/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import modelo.Usuario;

/**
 *
 * @author theky
 */
public class BuscaUsuarioAnio implements InterfazBusquedaUsuario {
    
    @Override
    public ArrayList<Usuario> buscaUsuarios(ArrayList<Usuario> lista_usuarios, String busqueda) {
        ArrayList<Usuario> usuarios_encontrados = new ArrayList<>();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        Date date;
        String anio;
        
        if (busqueda.isEmpty()) {
            return usuarios_encontrados;
        }
        for (Usuario usuario : lista_usuarios) {
            date = usuario.getFecha_nacimiento().getTime();
            cal.setTime(date);
            anio = String.valueOf(cal.get(Calendar.YEAR));
            
            if (anio.equals(busqueda)) {
                usuarios_encontrados.add(usuario);
            }
        }
        return usuarios_encontrados;
    }
    
}
