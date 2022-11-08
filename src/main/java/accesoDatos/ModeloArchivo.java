/*
 */
package accesoDatos;

import excepciones.CargaDatosException;
import modelo.Alquiler;
import modelo.Libro;
import modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Escoz
 */
public class ModeloArchivo implements Modelo {

    private final static String RUTA_LIBROS = "DatosBiblioteca\\libros.dat";
    private final static String RUTA_USUARIOS = "DatosBiblioteca\\usuarios.dat";
    private final static String RUTA_ALQUILER = "DatosBiblioteca\\alquileres.dat";

    @Override
    public Map<String, Libro> cargaLibros() throws CargaDatosException {
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

        } catch (EOFException ignored) {
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
    public Map<String, Usuario> cargaPersonas() throws CargaDatosException {
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

        } catch (EOFException ignored) {
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
    public ArrayList<Alquiler> cargaAlquileres() throws CargaDatosException {
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

        } catch (EOFException ignored) {
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
    public void guardaLibros(Map<String, Libro> libros) {
        FileOutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            outputStream = new FileOutputStream(RUTA_LIBROS);
            objectOutputStream = new ObjectOutputStream(outputStream);

            ArrayList<Libro> lista_libros = new ArrayList<>(libros.values());

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
    public void guardaPersonas(Map<String, Usuario> personas) {
        FileOutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            outputStream = new FileOutputStream(RUTA_USUARIOS);
            objectOutputStream = new ObjectOutputStream(outputStream);

            ArrayList<Usuario> lista_usuarios = new ArrayList<>(personas.values());

            objectOutputStream.writeObject(lista_usuarios);

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

    private void creaCarpetaDatos() {
        File carpeta_datos = new File("DatosBiblioteca");

        if (!carpeta_datos.exists()) {
            carpeta_datos.mkdir();
        }
    }
}
