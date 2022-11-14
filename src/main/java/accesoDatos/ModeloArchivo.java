package accesoDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import modelo.Alquiler;
import modelo.Libro;
import modelo.Usuario;
import observer.EventoAlquiler;
import observer.EventoLibro;
import observer.ObservadorAlquiler;
import observer.ObservadorLibros;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Escoz
 */
public class ModeloArchivo implements Modelo, EventoLibro, EventoAlquiler {

    private final String ruta_configuracion = "config.ini";
    private String ruta_libros;
    private String ruta_usuarios;
    private String ruta_alquileres;

    private HashMap<String, Libro> libros;
    private HashMap<String, Usuario> personas;

    private final ArrayList<ObservadorLibros> lista_observadoresLibro;
    private final ArrayList<ObservadorAlquiler> lista_observadoresAlquiler;

    public ModeloArchivo() {
        libros = new HashMap<>();
        personas = new HashMap<>();
        lista_observadoresLibro = new ArrayList<>();
        lista_observadoresAlquiler = new ArrayList<>();

        ruta_alquileres = "";
        ruta_usuarios = "";
        ruta_libros = "";

        leeFicheroConfig();
    }

    private void leeFicheroConfig() {
        try {
            FileReader fileReader = new FileReader(ruta_configuracion);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea = bufferedReader.readLine();

            while (linea != null) {
                String[] partes = linea.split("=");
                switch (partes[0]) {
                    case "rutaUsuarios" ->
                        ruta_usuarios = partes[1];
                    case "rutaLibros" ->
                        ruta_libros = partes[1];
                    case "rutaAlquileres" ->
                        ruta_alquileres = partes[1];
                    default ->
                        throw new IOException();
                }

                linea = bufferedReader.readLine();
            }

            /* Si no existe crea un fichero de configuracion */
        } catch (FileNotFoundException ex) {
            try {
                new File(ruta_configuracion).createNewFile();
            } catch (IOException ex1) {
                Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void escribeFicheroConfig() {
        try {
            try ( FileWriter fileWriter = new FileWriter(ruta_configuracion)) {
                String r_usuarios = "rutaUsuarios=" + ruta_usuarios;
                String r_libros = "rutaLibros=" + ruta_libros;
                String r_alquileres = "rutaAlquileres=" + ruta_alquileres;

                fileWriter.write(r_usuarios + "\n");
                fileWriter.write(r_libros + "\n");
                fileWriter.write(r_alquileres + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void creaFicherosDatos(String ruta) {
        /* Creamos un directorio nuevo para los archivos */
        ruta += "/DatosBiblioteca/";
        File file = new File(ruta);
        file.mkdir();

        /* Creamos los archivos */
        setRuta(ruta);
        try {
            new File(ruta_libros).createNewFile();
            new File(ruta_usuarios).createNewFile();
            new File(ruta_alquileres).createNewFile();

            escribeFicheroConfig();

        } catch (IOException ex) {
            // TODO crear una excepcion
        }
    }

    public String getRutaAnterior() {
        File file = new File(ruta_libros);
        return file.getParent();
    }

    @Override
    public Map<String, Libro> cargaLibros() {
        Libro libro;
        HashMap<String, Libro> mapa_libros = new HashMap<>();

        if (ruta_libros != null || !ruta_libros.isBlank()) {

        }
        return this.libros = mapa_libros;
    }

    @Override
    public HashMap<String, Usuario> cargaPersonas() {
        Usuario persona;
        HashMap<String, Usuario> mapa_personas = new HashMap<>();

        if (ruta_usuarios != null || !ruta_usuarios.isBlank()) {

        }
        return this.personas = mapa_personas;
    }

    @Override
    public ArrayList<Alquiler> cargaAlquileres() {
        ArrayList<Alquiler> alquileres = new ArrayList<>();

        if (ruta_alquileres != null || !ruta_alquileres.isBlank()) {

        }
        return alquileres;
    }

    public void setRuta(String nueva_ruta) {
        ruta_libros = nueva_ruta + "libros.csv";
        ruta_usuarios = nueva_ruta + "usuarios.csv";
        ruta_alquileres = nueva_ruta + "alquileres.csv";
    }

    @Override
    public void guardaLibros(Map<String, Libro> libros) {
        notificaCambioLibro();
    }

    @Override
    public void guardaPersonas(Map<String, Usuario> usuario) {
        // TODO
    }

    @Override
    public void guardaAlquileres(ArrayList<Alquiler> alquileres) {
        notificaCambioAlquiler();
    }

    @Override
    public void suscribirseLibro(ObservadorLibros o) {
        lista_observadoresLibro.add(o);
    }

    @Override
    public void notificaCambioLibro() {
        for (ObservadorLibros observadorLibros : lista_observadoresLibro) {
            observadorLibros.cambioLibro();
        }
    }

    @Override
    public void suscribirseAlquiler(ObservadorAlquiler o) {
        lista_observadoresAlquiler.add(o);
    }

    @Override
    public void notificaCambioAlquiler() {
        for (ObservadorAlquiler observadorAlquiler : lista_observadoresAlquiler) {
            observadorAlquiler.cambioAlquiler();
        }
    }

}
