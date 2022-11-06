/*
 */
package accesoDatos;

import excepciones.CargaDatosException;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Alquiler;
import modelo.Libro;
import modelo.Usuario;

/**
 *
 * @author Escoz
 */
public class ModeloArchivo implements Modelo {

    private final static String RUTA_LIBROS = "DatosBiblioteca\\libros.dat";
    private final static String RUTA_USUARIOS = "DatosBiblioteca\\usuarios.dat";
    private final static String RUTA_ALQUILER = "DatosBiblioteca\\alquileres.dat";

    @Override
    public Map cargaLibros() throws CargaDatosException {
        HashMap<String, Libro> mapa_libros = new HashMap<>();
        FileInputStream inputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            inputStream = new FileInputStream(RUTA_LIBROS);
            objectInputStream = new ObjectInputStream(inputStream);

            ArrayList<Libro> libros = (ArrayList<Libro>) objectInputStream.readObject();

            for (Libro libro : libros) {
                mapa_libros.put(libro.getIsbn(), libro);
            }

        } catch (FileNotFoundException ex) {
            try {
                creaCarpetaDatos();
                new File(RUTA_LIBROS).createNewFile();

            } catch (IOException ex1) {
                Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } catch (EOFException ex) {
        } catch (IOException | ClassNotFoundException ex) {
            throw new CargaDatosException("LIBRO");

        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new CargaDatosException("LIBRO");
            }
        }

        return mapa_libros;
    }

    @Override
    public Map cargaPersonas() throws CargaDatosException {
        HashMap<String, Usuario> mapa_usuario = new HashMap<>();
        FileInputStream inputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            inputStream = new FileInputStream(RUTA_USUARIOS);
            objectInputStream = new ObjectInputStream(inputStream);

            ArrayList<Usuario> usuarios = (ArrayList<Usuario>) objectInputStream.readObject();

            for (Usuario usuario : usuarios) {
                mapa_usuario.put(usuario.getDni(), usuario);
            }

        } catch (FileNotFoundException ex) {
            try {
                creaCarpetaDatos();
                new File(RUTA_USUARIOS).createNewFile();

            } catch (IOException ex1) {
                Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } catch (EOFException ex) {
        } catch (IOException | ClassNotFoundException ex) {
            throw new CargaDatosException("USUARIO");

        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new CargaDatosException("USUARIO");
            }
        }
        return mapa_usuario;
    }

    @Override
    public ArrayList cargaAlquileres() throws CargaDatosException {
        ArrayList<Alquiler> lista_alquileres = new ArrayList<>();
        FileInputStream inputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            inputStream = new FileInputStream(RUTA_ALQUILER);
            objectInputStream = new ObjectInputStream(inputStream);

            lista_alquileres = (ArrayList<Alquiler>) objectInputStream.readObject();

        } catch (FileNotFoundException ex) {
            try {
                creaCarpetaDatos();
                new File(RUTA_ALQUILER).createNewFile();

            } catch (IOException ex1) {
                Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } catch (EOFException ex) {
        } catch (IOException | ClassNotFoundException ex) {
            throw new CargaDatosException("ALQUILER");

        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new CargaDatosException("ALQUILER");
            }
        }
        return lista_alquileres;
    }

    @Override
    public void guardaLibros(Map libros) {
        ArrayList<Libro> lista_libros = new ArrayList<>();
        FileOutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            outputStream = new FileOutputStream(RUTA_LIBROS);
            objectOutputStream = new ObjectOutputStream(outputStream);

            for (Object o : libros.values()) {
                lista_libros.add((Libro) o);
            }

            objectOutputStream.writeObject(lista_libros);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public void guardaPersonas(Map personas) {
        ArrayList<Usuario> lista_usuarios = new ArrayList<>();
        FileOutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            outputStream = new FileOutputStream(RUTA_USUARIOS);
            objectOutputStream = new ObjectOutputStream(outputStream);

            for (Object o : personas.values()) {
                lista_usuarios.add((Usuario) o);
            }

            objectOutputStream.writeObject(lista_usuarios);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public void guardaAlquileres(ArrayList<Alquiler> alquileres) {
        FileOutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            outputStream = new FileOutputStream(RUTA_ALQUILER);
            objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(alquileres);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                Logger.getLogger(ModeloArchivo.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    private boolean creaCarpetaDatos() {
        File carpeta_datos = new File("DatosBiblioteca");

        if (!carpeta_datos.exists()) {
            carpeta_datos.mkdir();
            return true;
        } else {
            return false;
        }
    }
}
