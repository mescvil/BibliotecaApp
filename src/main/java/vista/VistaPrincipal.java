/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import command.OrdenAddLibro;
import command.PilaCommand;
import controlador.Controlador;
import excepciones.CargaDatosException;
import excepciones.DuplicadoException;
import excepciones.GuardaDatosException;

import static extras.Colores_Dimensiones.MORADO;

import modelo.Alquiler;
import modelo.Libro;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import observer.ObservadorPila;
import observer.ObservadorPilaLibro;

/**
 * @author Escoz
 */
public class VistaPrincipal extends JFrame implements ObservadorPila, ObservadorPilaLibro {

    private final Controlador controlador;

    private final DefaultListModel<Libro> modelo_listaLibros;
    private final DefaultListModel<Object> modelo_listaAlquileres;
    private final DefaultListModel<Usuario> modelo_listaUsuarios;

    private final DialogoPersona dialogoPersona;
    private final DialogoLibro dialogoLibro;
    private final DialogoPrestamo dialogoPrestamo;
    private final DialogoAlquiler dialogoAlquileres;

    private boolean tiene_alquileres;

    private int contador_clicks = 4;
    private final String texto_busqueda = "Introduce un titulo...";

    /**
     * Creates new form Vista
     */
    public VistaPrincipal(Controlador controlador) {

        this.controlador = controlador;
        this.modelo_listaLibros = new DefaultListModel<>();
        this.modelo_listaAlquileres = new DefaultListModel<>();
        this.modelo_listaUsuarios = new DefaultListModel<>();

        this.dialogoLibro = new DialogoLibro(this, true);
        this.dialogoPersona = new DialogoPersona(this, true, modelo_listaUsuarios);
        this.dialogoPrestamo = new DialogoPrestamo(this, true, modelo_listaUsuarios);
        this.dialogoAlquileres = new DialogoAlquiler(this, false);

        initComponents();
        iconoAplicacion();

        lista_libros.setModel(modelo_listaLibros);
        lista_alquileres.setModel(modelo_listaAlquileres);

        modeloDefectoLibros();
        modeloDefectoAlquileres();
        modeloDefectoUsuarios();

        campo_busquedaLibro.getDocument().addDocumentListener(new ListenerCampos());
        boton_buscarLibro.setVisible(false);

        PilaCommand.suscribirse(this);
        OrdenAddLibro.suscribirse(this);

        boton_limpiarBusqueda.doClick();
    }

    private void iconoAplicacion() {
        Image icono = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icono_app.png"))).getImage();

        this.setIconImage(icono);
        dialogoLibro.setIconImage(icono);
        dialogoPersona.setIconImage(icono);
        dialogoPrestamo.setIconImage(icono);
        dialogoAlquileres.setIconImage(icono);
    }

    public void guardaUsuario(Usuario usuario) throws GuardaDatosException, DuplicadoException {
        controlador.guardaUsuario(usuario);
    }

    public void guardaLibro(Libro libro) throws GuardaDatosException, DuplicadoException {
        controlador.guardaLibro(libro);
    }

    public void guardaAlquiler(Alquiler alquiler) throws GuardaDatosException {
        controlador.guardaAlquiler(alquiler);
        setModeloListaAlquileres(controlador.getInfoAlquileres(alquiler.getLibro().getIsbn()));
        rellenaCamposLibro(alquiler.getLibro());

    }

    public void realizaDevolucion(Alquiler alquiler) {
        try {
            controlador.realizaDevolucion(alquiler);

            JOptionPane.showMessageDialog(dialogoAlquileres, "Operaci??n realizada con ??xito",
                    "Devoluci??n", JOptionPane.INFORMATION_MESSAGE);

        } catch (GuardaDatosException ex) {
            JOptionPane.showMessageDialog(dialogoAlquileres, "No ha sido posible realizar la devoluci??n",
                    "Devoluci??n", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Alquiler> buscaAlquileres(String busqueda) {
        return controlador.buscaAlquileres(busqueda);
    }

    public ArrayList<Usuario> buscaUsuarios(String busqueda) {
        return controlador.buscaUsuarios(busqueda);
    }

    public void buscaUsuarios(HashMap<String, String> busqueda) {
        controlador.buscaUsuarios(busqueda);
    }

    public void buscaLibros(HashMap<String, String> busqueda) {
        controlador.buscaLibros(busqueda);
    }

    public void buscaAlquileres(HashMap<String, String> busqueda) {
        controlador.buscaAlquileres(busqueda);
    }

    private void modeloDefectoUsuarios() {
        modelo_listaUsuarios.clear();
        modelo_listaUsuarios.addAll(controlador.getUsuarios());
    }

    private void modeloDefectoLibros() {
        modelo_listaLibros.clear();
        ArrayList<Libro> libros = controlador.getLibros();
        modelo_listaLibros.addAll(libros);

    }

    private void modeloDefectoAlquileres() {
        modelo_listaAlquileres.clear();
    }

    private void setModeloListaAlquileres(ArrayList<Alquiler> alquileres) {
        modelo_listaAlquileres.clear();

        if (!alquileres.isEmpty()) {
            modelo_listaAlquileres.addAll(alquileres);
            tiene_alquileres = true;
        } else {
            modelo_listaAlquileres.addElement("Sin alquileres");
            tiene_alquileres = false;

        }
    }

    private void setModeloListaLibros(ArrayList<Libro> libros_encontrados) {
        modelo_listaLibros.clear();

        if (!libros_encontrados.isEmpty()) {
            modelo_listaLibros.addAll(libros_encontrados);
        }
    }

    public void cambioEnUsuarios() {
        modeloDefectoUsuarios();
    }

    public void cambioEnListaLibros() {
        ArrayList<Libro> libros = controlador.getLibros();
        modelo_listaLibros.clear();
        modelo_listaLibros.addAll(libros);
        dialogoLibro.actualizaListaLibros(libros);
    }

    public void cambioDeAlquiler() {
        JList lista = lista_libros;

        rellenaCamposLibro((Libro) lista.getSelectedValue());
        boton_devolucion.setEnabled(false);
        dialogoAlquileres.actualizaTablaDevolucion(controlador.getAlquileres());
    }

    @Override
    public void cambioPila() {
        boton_deshacer.setEnabled(PilaCommand.canUndo());
        boton_deshacer.setToolTipText(PilaCommand.nombreOrdenDeshacer());
        boton_rehacer.setEnabled(PilaCommand.canRedo());
        boton_rehacer.setToolTipText(PilaCommand.nombreOrdenRehacer());
    }

    private void rellenaCamposLibro(Libro libro) {

        if (libro != null) {
            String isbn = libro.getIsbn();
            String titulo = libro.getTitulo();
            String autor = libro.getAutor();
            String n_ejemplares;
            String fecha;
            ArrayList<Alquiler> lista_alquileres = controlador.getInfoAlquileres(libro);

            if (isbn != null) {
                fecha = libro.getAnio_publicacion();
                n_ejemplares = String.valueOf(libro.getN_ejemplares());
            } else {
                fecha = "";
                n_ejemplares = "";
            }

            campo_isbn.setText(isbn);
            campo_titulo.setText(titulo);
            campo_fecha.setText(fecha);
            campo_autor.setText(autor);
            campo_nEjemplares.setText(n_ejemplares);
            setModeloListaAlquileres(lista_alquileres);
        }
    }

    public void actualizaBusquedaUsuarios(ArrayList<Usuario> usuarios_econtrados) {
        dialogoPersona.actualizaListaUsuarios(usuarios_econtrados);
    }

    public void actualizaBusquedaLibros(ArrayList<Libro> libros_encontrados) {
        dialogoLibro.actualizaListaLibrosBusqueda(libros_encontrados);
    }

    public void actualizaBusquedaAlquiler(ArrayList<Alquiler> alquileres_econtrados) {
        dialogoAlquileres.actualizaTablaBusqueda(alquileres_econtrados);
    }

    public void abreDialogoPrestamo(Libro libro_prestado) {
        dialogoPrestamo.muestraDialogo(libro_prestado);
        dialogoLibro.rellenaDatosLibros(libro_prestado);
    }

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toolBar = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        boton_deshacer = new javax.swing.JButton();
        boton_rehacer = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        boton_listaLibros = new javax.swing.JButton();
        boton_aniadeLibro = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        boton_verUsuarios = new javax.swing.JButton();
        boton_aniadeUsuario = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        boton_alquileres = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        boton_destruccion = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        logo = new javax.swing.JLabel();
        panel_buscador = new javax.swing.JPanel();
        campo_busquedaLibro = new javax.swing.JTextField();
        boton_buscarLibro = new javax.swing.JButton();
        boton_limpiarBusqueda = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        panel_datos = new javax.swing.JPanel();
        panel_listadoLibros = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_libros = new javax.swing.JList<>();
        panel_detallesLibro = new javax.swing.JPanel();
        label_isbn = new javax.swing.JLabel();
        campo_isbn = new javax.swing.JTextField();
        label_titulo = new javax.swing.JLabel();
        campo_titulo = new javax.swing.JTextField();
        label_autor = new javax.swing.JLabel();
        label_ejemplares = new javax.swing.JLabel();
        campo_autor = new javax.swing.JTextField();
        campo_fecha = new javax.swing.JTextField();
        boton_nuevoPrestamo = new javax.swing.JButton();
        campo_nEjemplares = new javax.swing.JTextField();
        label_fecha = new javax.swing.JLabel();
        panel_datosUsuario = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista_alquileres = new javax.swing.JList<>();
        boton_devolucion = new javax.swing.JButton();
        barraMenu = new javax.swing.JMenuBar();
        menu_archivo = new javax.swing.JMenu();
        menu_cargar = new javax.swing.JMenuItem();
        menu_guardar = new javax.swing.JMenuItem();
        menu_usuarios = new javax.swing.JMenu();
        menu_aniadeUsuario = new javax.swing.JMenuItem();
        menu_verUsuarios = new javax.swing.JMenuItem();
        menu_libros = new javax.swing.JMenu();
        menu_aniadeLibro = new javax.swing.JMenuItem();
        menu_listaLibros = new javax.swing.JMenuItem();
        menu_alquileres = new javax.swing.JMenu();
        menu_listaAlquileres = new javax.swing.JMenuItem();
        menu_ajustes = new javax.swing.JMenu();
        checkMenu_modoOscuro = new javax.swing.JCheckBoxMenuItem();
        menu_salir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca App 1.5 Fantaremix HD Collection - Hyperion");
        setMinimumSize(new java.awt.Dimension(700, 425));
        setPreferredSize(new java.awt.Dimension(900, 600));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        panel_toolBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 10, 0, 5));
        panel_toolBar.setMinimumSize(new java.awt.Dimension(600, 46));
        panel_toolBar.setLayout(new java.awt.GridLayout(1, 0));

        toolBar.setRollover(true);
        toolBar.setMinimumSize(new java.awt.Dimension(137, 44));

        boton_deshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/undo.png"))); // NOI18N
        boton_deshacer.setFocusable(false);
        boton_deshacer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_deshacer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_deshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_deshacerActionPerformed(evt);
            }
        });
        toolBar.add(boton_deshacer);

        boton_rehacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redo.png"))); // NOI18N
        boton_rehacer.setFocusable(false);
        boton_rehacer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_rehacer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_rehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_rehacerActionPerformed(evt);
            }
        });
        toolBar.add(boton_rehacer);
        toolBar.add(jSeparator4);

        boton_listaLibros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lista_libros.png"))); // NOI18N
        boton_listaLibros.setToolTipText("Listado de libros");
        boton_listaLibros.setFocusable(false);
        boton_listaLibros.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_listaLibros.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_listaLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_listaLibrosActionPerformed(evt);
            }
        });
        toolBar.add(boton_listaLibros);

        boton_aniadeLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add_libro.png"))); // NOI18N
        boton_aniadeLibro.setToolTipText("A??adir un libro");
        boton_aniadeLibro.setFocusable(false);
        boton_aniadeLibro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_aniadeLibro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_aniadeLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_aniadeLibroActionPerformed(evt);
            }
        });
        toolBar.add(boton_aniadeLibro);

        jSeparator1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5));
        toolBar.add(jSeparator1);

        boton_verUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lista_usuarios.png"))); // NOI18N
        boton_verUsuarios.setToolTipText("Lista de usuarios");
        boton_verUsuarios.setFocusable(false);
        boton_verUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_verUsuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_verUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abreDialogoVerUsuarios(evt);
            }
        });
        toolBar.add(boton_verUsuarios);

        boton_aniadeUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add_usuario.png"))); // NOI18N
        boton_aniadeUsuario.setToolTipText("A??adir un usuario");
        boton_aniadeUsuario.setFocusable(false);
        boton_aniadeUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_aniadeUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_aniadeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abreDialogoNuevoUsuario(evt);
            }
        });
        toolBar.add(boton_aniadeUsuario);
        toolBar.add(jSeparator2);

        boton_alquileres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lista_alquileres.png"))); // NOI18N
        boton_alquileres.setToolTipText("Listado de alquileres");
        boton_alquileres.setFocusable(false);
        boton_alquileres.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_alquileres.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_alquileres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_alquileresActionPerformed(evt);
            }
        });
        toolBar.add(boton_alquileres);
        toolBar.add(jSeparator3);

        boton_destruccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/destruccion.png"))); // NOI18N
        boton_destruccion.setToolTipText("Encuentra el activador secreto");
        boton_destruccion.setEnabled(false);
        boton_destruccion.setFocusable(false);
        boton_destruccion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_destruccion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_destruccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_destruccionActionPerformed(evt);
            }
        });
        toolBar.add(boton_destruccion);
        toolBar.add(filler1);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoMouseClicked(evt);
            }
        });
        toolBar.add(logo);

        panel_toolBar.add(toolBar);

        getContentPane().add(panel_toolBar);

        panel_buscador.setMaximumSize(new java.awt.Dimension(2147483647, 40));
        panel_buscador.setPreferredSize(new java.awt.Dimension(600, 40));
        panel_buscador.setLayout(new java.awt.GridBagLayout());

        campo_busquedaLibro.setText("Introduce un titulo...");
        campo_busquedaLibro.setToolTipText("Busca un libro por titulo");
        campo_busquedaLibro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campo_busquedaLibroMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        panel_buscador.add(campo_busquedaLibro, gridBagConstraints);

        boton_buscarLibro.setBackground(new java.awt.Color(51, 153, 255));
        boton_buscarLibro.setForeground(new java.awt.Color(255, 255, 255));
        boton_buscarLibro.setText("Buscar");
        boton_buscarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_buscarLibroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        panel_buscador.add(boton_buscarLibro, gridBagConstraints);

        boton_limpiarBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        boton_limpiarBusqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/limpiar_pequenio.png"))); // NOI18N
        boton_limpiarBusqueda.setBorderPainted(false);
        boton_limpiarBusqueda.setContentAreaFilled(false);
        boton_limpiarBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton_limpiarBusquedaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton_limpiarBusquedaMouseExited(evt);
            }
        });
        boton_limpiarBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_limpiarBusquedaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        panel_buscador.add(boton_limpiarBusqueda, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        panel_buscador.add(filler2, gridBagConstraints);

        getContentPane().add(panel_buscador);

        panel_datos.setLayout(new java.awt.GridBagLayout());

        panel_listadoLibros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 5, 1), javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Libros"), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5))));
        panel_listadoLibros.setPreferredSize(new java.awt.Dimension(300, 360));
        panel_listadoLibros.setLayout(new java.awt.GridLayout(1, 0));

        lista_libros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lista_libros.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                libroSeleccionado(evt);
            }
        });
        jScrollPane1.setViewportView(lista_libros);

        panel_listadoLibros.add(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        panel_datos.add(panel_listadoLibros, gridBagConstraints);

        panel_detallesLibro.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 5, 5), javax.swing.BorderFactory.createTitledBorder("Detalles del Libro")));
        panel_detallesLibro.setPreferredSize(new java.awt.Dimension(300, 200));
        panel_detallesLibro.setLayout(new java.awt.GridBagLayout());

        label_isbn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_isbn.setText("ISBN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.14;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        panel_detallesLibro.add(label_isbn, gridBagConstraints);

        campo_isbn.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.14;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        panel_detallesLibro.add(campo_isbn, gridBagConstraints);

        label_titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_titulo.setText("Titulo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.14;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        panel_detallesLibro.add(label_titulo, gridBagConstraints);

        campo_titulo.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.14;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        panel_detallesLibro.add(campo_titulo, gridBagConstraints);

        label_autor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_autor.setText("Autor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.14;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        panel_detallesLibro.add(label_autor, gridBagConstraints);

        label_ejemplares.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_ejemplares.setText("Ejemplares");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.14;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        panel_detallesLibro.add(label_ejemplares, gridBagConstraints);

        campo_autor.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.14;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        panel_detallesLibro.add(campo_autor, gridBagConstraints);

        campo_fecha.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.14;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        panel_detallesLibro.add(campo_fecha, gridBagConstraints);

        boton_nuevoPrestamo.setBackground(new java.awt.Color(51, 153, 255));
        boton_nuevoPrestamo.setForeground(new java.awt.Color(255, 255, 255));
        boton_nuevoPrestamo.setText("Nuevo pr??stamo");
        boton_nuevoPrestamo.setEnabled(false);
        boton_nuevoPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_nuevoPrestamoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panel_detallesLibro.add(boton_nuevoPrestamo, gridBagConstraints);

        campo_nEjemplares.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.14;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        panel_detallesLibro.add(campo_nEjemplares, gridBagConstraints);

        label_fecha.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_fecha.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.14;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        panel_detallesLibro.add(label_fecha, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        panel_datos.add(panel_detallesLibro, gridBagConstraints);

        panel_datosUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 5, 5), javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Alquileres"), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5))));
        panel_datosUsuario.setName(""); // NOI18N
        panel_datosUsuario.setPreferredSize(new java.awt.Dimension(300, 200));
        panel_datosUsuario.setLayout(new java.awt.GridBagLayout());

        lista_alquileres.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lista_alquileresValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lista_alquileres);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        panel_datosUsuario.add(jScrollPane2, gridBagConstraints);

        boton_devolucion.setBackground(MORADO);
        boton_devolucion.setForeground(new java.awt.Color(255, 255, 255));
        boton_devolucion.setText("Devolver");
        boton_devolucion.setEnabled(false);
        boton_devolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_devolucionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        panel_datosUsuario.add(boton_devolucion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.5;
        panel_datos.add(panel_datosUsuario, gridBagConstraints);

        getContentPane().add(panel_datos);

        menu_archivo.setText("Archivo");

        menu_cargar.setText("Cargar datos...");
        menu_cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_cargarActionPerformed(evt);
            }
        });
        menu_archivo.add(menu_cargar);

        menu_guardar.setText("Guardar como...");
        menu_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_guardarActionPerformed(evt);
            }
        });
        menu_archivo.add(menu_guardar);

        menu_usuarios.setText("Usuarios");

        menu_aniadeUsuario.setText("Agregar usuario...");
        menu_aniadeUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_aniadeUsuarioActionPerformed(evt);
            }
        });
        menu_usuarios.add(menu_aniadeUsuario);

        menu_verUsuarios.setText("Ver usuarios...");
        menu_verUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_verUsuariosActionPerformed(evt);
            }
        });
        menu_usuarios.add(menu_verUsuarios);

        menu_archivo.add(menu_usuarios);

        menu_libros.setText("Libros");

        menu_aniadeLibro.setText("Nuevo libro...");
        menu_aniadeLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_aniadeLibroActionPerformed(evt);
            }
        });
        menu_libros.add(menu_aniadeLibro);

        menu_listaLibros.setText("Ver libros...");
        menu_listaLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_listaLibrosActionPerformed(evt);
            }
        });
        menu_libros.add(menu_listaLibros);

        menu_archivo.add(menu_libros);

        menu_alquileres.setText("Alquileres");

        menu_listaAlquileres.setText("Listado de alquileres...");
        menu_listaAlquileres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_listaAlquileresActionPerformed(evt);
            }
        });
        menu_alquileres.add(menu_listaAlquileres);

        menu_archivo.add(menu_alquileres);

        barraMenu.add(menu_archivo);

        menu_ajustes.setText("Ajustes");

        checkMenu_modoOscuro.setText("Modo oscuro");
        checkMenu_modoOscuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkMenu_modoOscuroActionPerformed(evt);
            }
        });
        menu_ajustes.add(checkMenu_modoOscuro);

        barraMenu.add(menu_ajustes);

        menu_salir.setText("Salir");
        menu_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_salirMouseClicked(evt);
            }
        });
        barraMenu.add(menu_salir);

        setJMenuBar(barraMenu);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void libroSeleccionado(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_libroSeleccionado
        JList lista = (JList) evt.getSource();

        if (lista.hasFocus()) {
            if (!lista.getValueIsAdjusting()) {

                Libro libro_seleccionado = (Libro) lista.getSelectedValue();

                rellenaCamposLibro(libro_seleccionado);
                boton_nuevoPrestamo.setEnabled(true);
                boton_devolucion.setEnabled(false);

            }
        }
    }//GEN-LAST:event_libroSeleccionado

    private void boton_buscarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarLibroActionPerformed
        ArrayList<Libro> libros_encontrados;
        HashMap<String, String> busqueda = new HashMap<>();
        String busqueda_titulo = campo_busquedaLibro.getText();

        if (!busqueda_titulo.equals(texto_busqueda) && !busqueda_titulo.isBlank()) {
            busqueda.put("titulo", busqueda_titulo);
        }

        if (!busqueda.isEmpty()) {
            libros_encontrados = controlador.buscaLibrosArray(busqueda);
            setModeloListaLibros(libros_encontrados);
        } else {
            modeloDefectoLibros();
        }
    }//GEN-LAST:event_boton_buscarLibroActionPerformed

    private void boton_limpiarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_limpiarBusquedaActionPerformed
        modeloDefectoLibros();
        campo_busquedaLibro.setText(texto_busqueda);
    }//GEN-LAST:event_boton_limpiarBusquedaActionPerformed

    private void menu_salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_salirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_menu_salirMouseClicked

    private void checkMenu_modoOscuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkMenu_modoOscuroActionPerformed
        try {
            if (checkMenu_modoOscuro.isSelected()) {
                UIManager.setLookAndFeel(new FlatDarkLaf());

            } else {
                UIManager.setLookAndFeel(new FlatLightLaf());
            }
            SwingUtilities.updateComponentTreeUI(this);
            SwingUtilities.updateComponentTreeUI(dialogoLibro);
            SwingUtilities.updateComponentTreeUI(dialogoPersona);
            SwingUtilities.updateComponentTreeUI(dialogoPrestamo);
            SwingUtilities.updateComponentTreeUI(dialogoAlquileres);

        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(this, "Error al cambiar de aspecto",
                    "Modo oscuro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_checkMenu_modoOscuroActionPerformed

    private void abreDialogoNuevoUsuario(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abreDialogoNuevoUsuario
        dialogoPersona.muestraModoAniadir();
    }//GEN-LAST:event_abreDialogoNuevoUsuario

    private void menu_aniadeUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_aniadeUsuarioActionPerformed
        abreDialogoNuevoUsuario(null);
    }//GEN-LAST:event_menu_aniadeUsuarioActionPerformed

    private void menu_verUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_verUsuariosActionPerformed
        dialogoPersona.muestraModoVer();
    }//GEN-LAST:event_menu_verUsuariosActionPerformed

    private void abreDialogoVerUsuarios(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abreDialogoVerUsuarios
        dialogoPersona.muestraModoVer();
    }//GEN-LAST:event_abreDialogoVerUsuarios

    public void abreDialogoVerUsuarios(Usuario usuario) {
        dialogoPersona.muestraModoVer(usuario);
    }

    private void boton_aniadeLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_aniadeLibroActionPerformed
        dialogoLibro.muestraModoAniadir();
    }//GEN-LAST:event_boton_aniadeLibroActionPerformed

    private void menu_aniadeLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_aniadeLibroActionPerformed
        boton_aniadeLibroActionPerformed(null);
    }//GEN-LAST:event_menu_aniadeLibroActionPerformed

    public void abreDialogoVerLibros(Libro libro) {
        dialogoLibro.muestraModoVer(libro, controlador.getLibros());
    }

    private void boton_nuevoPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_nuevoPrestamoActionPerformed
        JList lista = lista_libros;
        Libro libro_seleccionado = (Libro) lista.getSelectedValue();

        if (libro_seleccionado.getN_ejemplares() > 0) {
            dialogoPrestamo.muestraDialogo(libro_seleccionado);
        } else {
            JOptionPane.showMessageDialog(this, "No quedan ejemplares",
                    "Nuevo pr??stamo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_boton_nuevoPrestamoActionPerformed

    private void boton_devolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_devolucionActionPerformed
        if (lista_alquileres.getSelectedIndex() != -1) {
            try {
                JList lista = lista_alquileres;

                Alquiler alquiler = (Alquiler) lista.getSelectedValue();
                controlador.realizaDevolucion(alquiler);

                JOptionPane.showMessageDialog(this, "Operaci??n realizada con ??xito",
                        "Devoluci??n", JOptionPane.INFORMATION_MESSAGE);
            } catch (GuardaDatosException ex) {
                JOptionPane.showMessageDialog(this, "No ha sido posible realizar la devoluci??n",
                        "Devoluci??n", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_boton_devolucionActionPerformed

    private void lista_alquileresValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lista_alquileresValueChanged
        JList lista = (JList) evt.getSource();

        if (lista.hasFocus()) {
            if (!lista.getValueIsAdjusting()) {
                if (tiene_alquileres) {
                    boton_devolucion.setEnabled(true);
                }

            }
        }

    }//GEN-LAST:event_lista_alquileresValueChanged

    private void boton_alquileresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_alquileresActionPerformed
        dialogoAlquileres.muestraAlquileres(controlador.getAlquileres());
    }//GEN-LAST:event_boton_alquileresActionPerformed

    private void boton_listaLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_listaLibrosActionPerformed
        dialogoLibro.muestraModoVer(controlador.getLibros());
    }//GEN-LAST:event_boton_listaLibrosActionPerformed

    private void menu_listaAlquileresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_listaAlquileresActionPerformed
        dialogoAlquileres.muestraAlquileres(controlador.getAlquileres());
    }//GEN-LAST:event_menu_listaAlquileresActionPerformed

    private void menu_listaLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_listaLibrosActionPerformed
        dialogoLibro.muestraModoVer(controlador.getLibros());
    }//GEN-LAST:event_menu_listaLibrosActionPerformed

    private void boton_destruccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_destruccionActionPerformed
        ImageIcon icono = new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(
                "/destruccion_grande.png")));

        int resultado = JOptionPane.showConfirmDialog(this, "??Seguro?",
                "Autodestrucci??n", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, icono);

        if (resultado == 0) {
            resultado = JOptionPane.showConfirmDialog(this, "??De verdad de la buena?",
                    "Autodestrucci??n", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, icono);
            if (resultado == 0) {
                sonidoNuke();
                try {
                    controlador.autodestruccion();
                } catch (CargaDatosException | GuardaDatosException ex) {
                    Logger.getLogger(VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_boton_destruccionActionPerformed

    private void menu_cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_cargarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int opcion = fileChooser.showDialog(this, "Seleccionar");

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            controlador.setRutaFicheros(file.getAbsolutePath());

            try {
                controlador.cargaDatosNotify();

            } catch (CargaDatosException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Carga de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_menu_cargarActionPerformed

    private void menu_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_guardarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int opcion = fileChooser.showDialog(this, "Seleccionar");

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                controlador.creaNuevaRuta(file.getAbsolutePath());

            } catch (CargaDatosException | GuardaDatosException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Carga de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_menu_guardarActionPerformed

    private void boton_deshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_deshacerActionPerformed
        PilaCommand.undo();
    }//GEN-LAST:event_boton_deshacerActionPerformed

    private void boton_rehacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_rehacerActionPerformed
        PilaCommand.redo();
    }//GEN-LAST:event_boton_rehacerActionPerformed

    private void logoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoMouseClicked
        if (contador_clicks > 0) {
            contador_clicks--;
            creaDialogoClicks(contador_clicks);

            if (contador_clicks == 0) {
                boton_destruccion.setEnabled(true);
            }
        }
    }//GEN-LAST:event_logoMouseClicked

    private void campo_busquedaLibroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campo_busquedaLibroMouseClicked
        JTextField text = (JTextField) evt.getSource();
        text.setText("");
    }//GEN-LAST:event_campo_busquedaLibroMouseClicked

    private void boton_limpiarBusquedaMouseEntered(java.awt.event.MouseEvent evt) {
        JButton boton = (JButton) evt.getSource();
        boton.setContentAreaFilled(true);
    }

    private void boton_limpiarBusquedaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_limpiarBusquedaMouseExited
        JButton boton = (JButton) evt.getSource();
        boton.setContentAreaFilled(false);
    }//GEN-LAST:event_boton_limpiarBusquedaMouseExited

    private void creaDialogoClicks(int n_clicks) {
        ImageIcon icono = new javax.swing.ImageIcon(getClass().getResource("/destruccion_grande.png"));
        String mensaje;

        if (contador_clicks > 0) {
            mensaje = String.format("Estas a %s clicks de la autodestrucci??n", n_clicks);
        } else {
            mensaje = "Autodestrucci??n activada!";
        }

        JOptionPane panel_informativo = new JOptionPane(mensaje, JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION, icono, new Object[]{});
        JDialog dialogo = panel_informativo.createDialog("");
        dialogo.setModal(true);

        dialogo.addWindowListener(null);
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        Timer timer = new Timer(1250, (ActionEvent e) -> {
            dialogo.setVisible(false);
            dialogo.dispose();
        }
        );

        timer.start();
        dialogo.setVisible(true);
    }

    private void sonidoNuke() {
        AudioInputStream sonido = null;
        try {
            sonido = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource("/nuke.wav")));
            Clip clip = AudioSystem.getClip();
            clip.open(sonido);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(VistaPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (sonido != null) {
                    sonido.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(VistaPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    @Override
    public void cambioEnPilaLibro() {
        rellenaCamposLibro(new Libro());
        boton_nuevoPrestamo.setEnabled(false);
        boton_devolucion.setEnabled(false);

    }

    private class ListenerCampos implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            boton_buscarLibroActionPerformed(null);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            boton_buscarLibroActionPerformed(null);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // No hace nada
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton boton_alquileres;
    private javax.swing.JButton boton_aniadeLibro;
    private javax.swing.JButton boton_aniadeUsuario;
    private javax.swing.JButton boton_buscarLibro;
    private javax.swing.JButton boton_deshacer;
    private javax.swing.JButton boton_destruccion;
    private javax.swing.JButton boton_devolucion;
    public javax.swing.JButton boton_limpiarBusqueda;
    private javax.swing.JButton boton_listaLibros;
    private javax.swing.JButton boton_nuevoPrestamo;
    private javax.swing.JButton boton_rehacer;
    private javax.swing.JButton boton_verUsuarios;
    private javax.swing.JTextField campo_autor;
    private javax.swing.JTextField campo_busquedaLibro;
    private javax.swing.JTextField campo_fecha;
    private javax.swing.JTextField campo_isbn;
    private javax.swing.JTextField campo_nEjemplares;
    private javax.swing.JTextField campo_titulo;
    private javax.swing.JCheckBoxMenuItem checkMenu_modoOscuro;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JLabel label_autor;
    private javax.swing.JLabel label_ejemplares;
    private javax.swing.JLabel label_fecha;
    private javax.swing.JLabel label_isbn;
    private javax.swing.JLabel label_titulo;
    private javax.swing.JList<Object> lista_alquileres;
    private javax.swing.JList<Libro> lista_libros;
    private javax.swing.JLabel logo;
    private javax.swing.JMenu menu_ajustes;
    private javax.swing.JMenu menu_alquileres;
    private javax.swing.JMenuItem menu_aniadeLibro;
    private javax.swing.JMenuItem menu_aniadeUsuario;
    private javax.swing.JMenu menu_archivo;
    private javax.swing.JMenuItem menu_cargar;
    private javax.swing.JMenuItem menu_guardar;
    private javax.swing.JMenu menu_libros;
    private javax.swing.JMenuItem menu_listaAlquileres;
    private javax.swing.JMenuItem menu_listaLibros;
    private javax.swing.JMenu menu_salir;
    private javax.swing.JMenu menu_usuarios;
    private javax.swing.JMenuItem menu_verUsuarios;
    private javax.swing.JPanel panel_buscador;
    private javax.swing.JPanel panel_datos;
    private javax.swing.JPanel panel_datosUsuario;
    private javax.swing.JPanel panel_detallesLibro;
    private javax.swing.JPanel panel_listadoLibros;
    private javax.swing.JPanel panel_toolBar;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

}
