/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import controlador.Controlador;
import excepciones.GuardaDatosException;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.Alquiler;
import modelo.Libro;
import modelo.Usuario;

/**
 *
 * @author Escoz
 */
public class VistaPrincipal extends javax.swing.JFrame {

    private Controlador controlador;

    private DefaultListModel modelo_listaLibros;
    private DefaultListModel modelo_listaAlquileres;
    private DefaultListModel modelo_listaUsuarios;

    private DialogoPersona dialogoPersona;
    private DialogoLibro dialogoLibro;
    private DialogoPrestamo dialogoPrestamo;
    private DialogoAlquiler dialogoAlquileres;

    private boolean tiene_alquileres;

    /**
     * Creates new form Vista
     */
    public VistaPrincipal(Controlador controlador) {
        this.controlador = controlador;
        this.modelo_listaLibros = new DefaultListModel();
        this.modelo_listaAlquileres = new DefaultListModel();
        this.modelo_listaUsuarios = new DefaultListModel();

        this.dialogoLibro = new DialogoLibro(this, true, modelo_listaLibros);
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

        UIManager.put("OptionPane.yesButtonText", "Si");
        UIManager.put("OptionPane.noButtonText", "No");

    }

    public void iconoAplicacion() {
        Image icono = new ImageIcon(getClass().getResource("/icono_app.png")).getImage();

        this.setIconImage(icono);
        dialogoLibro.setIconImage(icono);
        dialogoPersona.setIconImage(icono);
        dialogoPrestamo.setIconImage(icono);
        dialogoAlquileres.setIconImage(icono);
    }

    public void guardaUsuario(Usuario usuario) throws GuardaDatosException {
        controlador.guardaUsuario(usuario);
    }

    public void guardaLibro(Libro libro) throws GuardaDatosException {
        controlador.guardaLibro(libro);
    }

    public void guardaAlquiler(Alquiler alquiler) throws GuardaDatosException {
        controlador.guardaAlquiler(alquiler);
        setModeloListaAlquileres(controlador.getInfoAlquileres(alquiler.getLibro().getIsbn()));
        rellenaCamposLibro(alquiler.getLibro());

    }

    private void modeloDefectoUsuarios() {
        modelo_listaUsuarios.clear();
        modelo_listaUsuarios.addAll(controlador.getPersonas());
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
        } else {
            JOptionPane.showMessageDialog(this, "Sin resultados",
                    "Busqueda de libros", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void rellenaCamposLibro(Libro libro) {

        String isbn = libro.getIsbn();
        String titulo = libro.getTitulo();
        String autor = libro.getAutor();
        String n_ejemplares;
        String fecha;
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
    }

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toolBar = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        boton_listaLibros = new javax.swing.JButton();
        boton_aniadeLibro = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        boton_verUsuarios = new javax.swing.JButton();
        boton_aniadeUsuario = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        boton_alquileres = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        boton_destruccion = new javax.swing.JButton();
        panel_buscador = new javax.swing.JPanel();
        campo_busquedaLibro = new javax.swing.JTextField();
        boton_buscarLibro = new javax.swing.JButton();
        boton_limpiarBusqueda = new javax.swing.JButton();
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
        menu_usuarios = new javax.swing.JMenu();
        menu_aniadeUsuario = new javax.swing.JMenuItem();
        menu_verUsuarios = new javax.swing.JMenuItem();
        menu_libros = new javax.swing.JMenu();
        menu_aniadeLibro = new javax.swing.JMenuItem();
        menu_alquileres = new javax.swing.JMenu();
        menu_listaAlquileres = new javax.swing.JMenuItem();
        menu_ajustes = new javax.swing.JMenu();
        checkMenu_modoOscuro = new javax.swing.JCheckBoxMenuItem();
        menu_salir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca App 1.2");
        setMinimumSize(new java.awt.Dimension(700, 425));
        setPreferredSize(new java.awt.Dimension(600, 500));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        panel_toolBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 10, 0, 5));
        panel_toolBar.setMinimumSize(new java.awt.Dimension(600, 46));
        panel_toolBar.setLayout(new java.awt.GridLayout(1, 0));

        toolBar.setRollover(true);
        toolBar.setMinimumSize(new java.awt.Dimension(137, 44));

        boton_listaLibros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lista_libros.png"))); // NOI18N
        boton_listaLibros.setToolTipText("Listado de libros");
        boton_listaLibros.setFocusable(false);
        boton_listaLibros.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_listaLibros.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(boton_listaLibros);

        boton_aniadeLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add_libro.png"))); // NOI18N
        boton_aniadeLibro.setToolTipText("Añadir un libro");
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
        boton_aniadeUsuario.setToolTipText("Añadir un usuario");
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
        boton_destruccion.setToolTipText("Autodestrucción");
        boton_destruccion.setFocusable(false);
        boton_destruccion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_destruccion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_destruccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_destruccionActionPerformed(evt);
            }
        });
        toolBar.add(boton_destruccion);

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
        gridBagConstraints.weightx = 0.1;
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

        boton_limpiarBusqueda.setText("Limpiar");
        boton_limpiarBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_limpiarBusquedaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        panel_buscador.add(boton_limpiarBusqueda, gridBagConstraints);

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
        boton_nuevoPrestamo.setText("Nuevo prestamo");
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

        boton_devolucion.setBackground(new java.awt.Color(153, 0, 153));
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

        menu_archivo.add(menu_libros);

        menu_alquileres.setText("Alquileres");

        menu_listaAlquileres.setText("Listado de alquileres...");
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
                String isbn_libro = libro_seleccionado.getIsbn();
                ArrayList<Alquiler> alquileres = controlador.getInfoAlquileres(isbn_libro);

                rellenaCamposLibro(libro_seleccionado);
                setModeloListaAlquileres(alquileres);
                boton_nuevoPrestamo.setEnabled(true);
                boton_devolucion.setEnabled(false);

            }
        }
    }//GEN-LAST:event_libroSeleccionado

    private void campo_busquedaLibroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campo_busquedaLibroMouseClicked
        JTextField campo = (JTextField) evt.getSource();
        campo.setText("");
    }//GEN-LAST:event_campo_busquedaLibroMouseClicked

    private void boton_buscarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_buscarLibroActionPerformed
        String busqueda = campo_busquedaLibro.getText();

        ArrayList<Libro> libros_encontrados = controlador.buscaLibroTitulo(busqueda);
        setModeloListaLibros(libros_encontrados);

    }//GEN-LAST:event_boton_buscarLibroActionPerformed

    private void boton_limpiarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_limpiarBusquedaActionPerformed
        modeloDefectoLibros();
        campo_busquedaLibro.setText("Introduce un titulo...");
        boton_nuevoPrestamo.setEnabled(false);
        modelo_listaAlquileres.clear();
        boton_devolucion.setEnabled(false);

        rellenaCamposLibro(new Libro());
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
        abreDialogoVerUsuarios(null);
    }//GEN-LAST:event_menu_verUsuariosActionPerformed

    private void abreDialogoVerUsuarios(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abreDialogoVerUsuarios
        dialogoPersona.muestraModoVer();
    }//GEN-LAST:event_abreDialogoVerUsuarios

    private void boton_aniadeLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_aniadeLibroActionPerformed
        dialogoLibro.muestraModoAniadir();
    }//GEN-LAST:event_boton_aniadeLibroActionPerformed

    private void menu_aniadeLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_aniadeLibroActionPerformed
        boton_aniadeLibroActionPerformed(null);
    }//GEN-LAST:event_menu_aniadeLibroActionPerformed

    private void boton_nuevoPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_nuevoPrestamoActionPerformed
        JList lista = lista_libros;
        Libro libro_seleccionado = (Libro) lista.getSelectedValue();

        if (libro_seleccionado.getN_ejemplares() > 0) {
            dialogoPrestamo.muestraDialogo(libro_seleccionado);
        } else {
            JOptionPane.showMessageDialog(this, "No quedan ejemplares",
                    "Nuevo prestamo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_boton_nuevoPrestamoActionPerformed

    private void boton_devolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_devolucionActionPerformed
        if (lista_alquileres.getSelectedIndex() != -1) {
            try {
                JList lista = lista_alquileres;

                Alquiler alquiler = (Alquiler) lista.getSelectedValue();
                controlador.realizaDevolucion(alquiler);

                setModeloListaAlquileres(controlador.getInfoAlquileres(alquiler.getLibro().getIsbn()));
                rellenaCamposLibro(alquiler.getLibro());
                boton_devolucion.setEnabled(false);

                JOptionPane.showMessageDialog(this, "Operación realizada con éxito",
                        "Devolución", JOptionPane.INFORMATION_MESSAGE);
            } catch (GuardaDatosException ex) {
                JOptionPane.showMessageDialog(this, "No ha sido posible realizar la devolución",
                        "Devolución", JOptionPane.ERROR_MESSAGE);
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

    private void boton_destruccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_destruccionActionPerformed

    }//GEN-LAST:event_boton_destruccionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton boton_alquileres;
    private javax.swing.JButton boton_aniadeLibro;
    private javax.swing.JButton boton_aniadeUsuario;
    private javax.swing.JButton boton_buscarLibro;
    private javax.swing.JButton boton_destruccion;
    private javax.swing.JButton boton_devolucion;
    private javax.swing.JButton boton_limpiarBusqueda;
    private javax.swing.JButton boton_listaLibros;
    private javax.swing.JButton boton_nuevoPrestamo;
    private javax.swing.JButton boton_verUsuarios;
    private javax.swing.JTextField campo_autor;
    private javax.swing.JTextField campo_busquedaLibro;
    private javax.swing.JTextField campo_fecha;
    private javax.swing.JTextField campo_isbn;
    private javax.swing.JTextField campo_nEjemplares;
    private javax.swing.JTextField campo_titulo;
    private javax.swing.JCheckBoxMenuItem checkMenu_modoOscuro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JLabel label_autor;
    private javax.swing.JLabel label_ejemplares;
    private javax.swing.JLabel label_fecha;
    private javax.swing.JLabel label_isbn;
    private javax.swing.JLabel label_titulo;
    private javax.swing.JList<String> lista_alquileres;
    private javax.swing.JList<String> lista_libros;
    private javax.swing.JMenu menu_ajustes;
    private javax.swing.JMenu menu_alquileres;
    private javax.swing.JMenuItem menu_aniadeLibro;
    private javax.swing.JMenuItem menu_aniadeUsuario;
    private javax.swing.JMenu menu_archivo;
    private javax.swing.JMenu menu_libros;
    private javax.swing.JMenuItem menu_listaAlquileres;
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
