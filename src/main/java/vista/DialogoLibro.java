package vista;

import excepciones.DuplicadoException;
import excepciones.GuardaDatosException;
import modelo.Libro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static extras.Colores_Dimensiones.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Escoz
 */
public class DialogoLibro extends javax.swing.JDialog {

    private final VistaPrincipal vista_padre;

    private final DefaultListModel<Libro> modelo_lista;
    private final DefaultListModel<Libro> modelo_busqueda;

    private final String texto_titulo = "Introduce un titulo...";
    private final String texto_autor = "Introduce un autor...";
    private final String texto_simple = "Introduce tu búsqueda...";

    /**
     * Creates new form DialogoPersona
     */
    public DialogoLibro(VistaPrincipal parent, boolean modal) {
        super(parent, modal);
        this.vista_padre = parent;

        initComponents();
        this.lista_libros.setModel(this.modelo_lista = new DefaultListModel<>());
        modelo_busqueda = new DefaultListModel<>();

        aniadeListeners();
    }

    private void aniadeListeners() {
        for (Component component : panel_busquedaAvanzada.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).getDocument().addDocumentListener(new ListenerCampos());
            }
        }
        campo_busquedaSimple.getDocument().addDocumentListener(new ListenerCampos());
    }

    public void muestraModoAniadir() {

        // Campos del libro
        campo_titulo.setEditable(true);
        campo_autor.setEditable(true);
        campo_isbn.setEditable(true);
        yearChooser_anio.setEnabled(true);
        spiner_ejemplares.setEnabled(true);
        rellenaDatosLibros(new Libro()); // Si esta vacío resetea los campos

        // Botón de uso multiple
        boton_multiple.setVisible(true);
        boton_multiple.setText("Guardar");
        boton_multiple.setForeground(new Color(51, 51, 51));
        boton_multiple.setBackground(VERDE);

        // Lista de libros
        lista_libros.setEnabled(false);
        lista_libros.clearSelection();

        // Búsqueda y filtros
        panel_busquedaSimple.setVisible(false);
        panel_busquedaAvanzada.setVisible(false);
        check_filtros.setVisible(false);

        // General
        this.setTitle("Nuevo libro");
        setPreferredSize(DIMENSION_GRANDE);
        SpinnerNumberModel snm = (SpinnerNumberModel) spiner_ejemplares.getModel();
        snm.setMinimum(1);
        snm.setValue(1);

        setLocationRelativeTo(vista_padre);
        pack();
        setVisible(true);

        boton_multiple.requestFocus();
    }

    public void muestraModoVer(ArrayList<Libro> array_libros) {

        // Campos del libro
        campo_titulo.setEditable(false);
        campo_autor.setEditable(false);
        campo_isbn.setEditable(false);
        spiner_ejemplares.setEnabled(false);
        yearChooser_anio.setEnabled(false);
        rellenaDatosLibros(new Libro());

        // Botón multiple
        boton_multiple.setVisible(true);
        boton_multiple.setForeground(Color.WHITE);
        boton_multiple.setText("Nuevo préstamo");
        boton_multiple.setBackground(AZUL);

        // Lista de libros
        modelo_lista.clear();
        modelo_lista.addAll(array_libros);
        lista_libros.setEnabled(true);

        // Filtros y búsqueda
        panel_busquedaSimple.setVisible(true);
        panel_busquedaAvanzada.setVisible(false);
        check_filtros.setVisible(true);
        campo_busquedaSimple.setVisible(true);
        reseteaPanelFiltros();

        // General
        setTitle("Libros registrados");
        setPreferredSize(DIMENSION_GRANDE);
        lista_libros.requestFocus();
        SpinnerNumberModel snm = (SpinnerNumberModel) spiner_ejemplares.getModel();
        snm.setMinimum(0);

        setLocationRelativeTo(vista_padre);
        pack();
        setVisible(true);

        boton_multiple.requestFocus();
    }

    private void reseteaPanelFiltros() {
        check_filtros.setSelected(false);
        resetaFiltrosBusquedaDuro();

    }

    public void muestraModoVer(Libro libro, ArrayList<Libro> array_libros) {

        // Campos del libro
        campo_titulo.setEditable(false);
        campo_autor.setEditable(false);
        campo_isbn.setEditable(false);
        spiner_ejemplares.setEnabled(false);
        yearChooser_anio.setEnabled(false);
        rellenaDatosLibros(libro);

        // Botón multiple
        boton_multiple.setVisible(true);
        boton_multiple.setForeground(Color.WHITE);
        boton_multiple.setText("Nuevo préstamo");
        boton_multiple.setBackground(AZUL);

        // Lista de libros
        modelo_lista.clear();
        modelo_lista.addAll(array_libros);
        lista_libros.setEnabled(true);

        // Filtros y búsqueda
        panel_busquedaSimple.setVisible(true);
        panel_busquedaAvanzada.setVisible(false);
        check_filtros.setVisible(true);
        campo_busquedaSimple.setVisible(true);
        reseteaPanelFiltros();

        // General
        setTitle("Libros registrados");
        setPreferredSize(DIMENSION_GRANDE);
        lista_libros.requestFocus();
        lista_libros.setSelectedValue(libro, true);

        SpinnerNumberModel snm = (SpinnerNumberModel) spiner_ejemplares.getModel();
        snm.setMinimum(0);

        setLocationRelativeTo(vista_padre);
        pack();
        setVisible(true);

        boton_multiple.requestFocus();
    }

    public void actualizaListaLibros(ArrayList<Libro> libros) {
        modelo_lista.clear();
        modelo_lista.addAll(libros);
    }

    public void actualizaListaLibrosBusqueda(ArrayList<Libro> libros_econtrados) {
        modelo_busqueda.clear();

        if (!libros_econtrados.isEmpty()) {
            modelo_busqueda.addAll(libros_econtrados);
            lista_libros.setModel(modelo_busqueda);

        } else if (this.isVisible()) {
            modelo_busqueda.addAll(libros_econtrados);
            lista_libros.setModel(modelo_busqueda);

        }

        pack();
        repaint();
    }

    public void rellenaDatosLibros(Libro libro) {
        campo_titulo.setText(libro.getTitulo());
        campo_autor.setText(libro.getAutor());
        campo_isbn.setText(libro.getIsbn());
        yearChooser_anio.setValue(Integer.parseInt(libro.getAnio_publicacion()));
        spiner_ejemplares.setValue(libro.getN_ejemplares());
    }

    public void resetaFiltrosBusquedaDuro() {
        // Reseteamos todos los componentes de tipo JCheckBox dentro del panel de busqueda
        for (Component component : panel_busquedaAvanzada.getComponents()) {
            if (component instanceof JCheckBox) {
                ((JCheckBox) component).setSelected(false);

            }
        }
        dateChooser_busqueda.setEnabled(false);

        campo_busquedaTitulo.setText(texto_titulo);
        campo_busquedaAutor.setText(texto_autor);
        dateChooser_busqueda.setYear(Calendar.getInstance().get(Calendar.YEAR));

        campo_busquedaSimple.setText(texto_simple);

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        label_isbn = new javax.swing.JLabel();
        campo_isbn = new javax.swing.JTextField();
        label_autor = new javax.swing.JLabel();
        label_ejemplares = new javax.swing.JLabel();
        campo_autor = new javax.swing.JTextField();
        boton_multiple = new javax.swing.JButton();
        label_anio = new javax.swing.JLabel();
        label_titulo = new javax.swing.JLabel();
        campo_titulo = new javax.swing.JTextField();
        panel_lista = new javax.swing.JScrollPane();
        lista_libros = new javax.swing.JList<>();
        yearChooser_anio = new com.toedter.calendar.JYearChooser();
        spiner_ejemplares = new javax.swing.JSpinner();
        panel_busquedaAvanzada = new javax.swing.JPanel();
        campo_busquedaTitulo = new javax.swing.JTextField();
        campo_busquedaAutor = new javax.swing.JTextField();
        check_publicacion = new javax.swing.JCheckBox();
        dateChooser_busqueda = new com.toedter.calendar.JYearChooser();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panel_busquedaSimple = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        campo_busquedaSimple = new javax.swing.JTextField();
        boton_busqueda = new javax.swing.JButton();
        boton_limpiar = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(32767, 0));
        check_filtros = new javax.swing.JCheckBox();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));

        setTitle("Datos de usuario");
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        label_isbn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_isbn.setText("ISBN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_isbn, gridBagConstraints);

        campo_isbn.setEditable(false);
        campo_isbn.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_isbn, gridBagConstraints);

        label_autor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_autor.setText("Autor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_autor, gridBagConstraints);

        label_ejemplares.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_ejemplares.setText("Ejemplares");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_ejemplares, gridBagConstraints);

        campo_autor.setEditable(false);
        campo_autor.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_autor, gridBagConstraints);

        boton_multiple.setForeground(new java.awt.Color(51, 51, 51));
        boton_multiple.setText("Boton");
        boton_multiple.setPreferredSize(new java.awt.Dimension(72, 25));
        boton_multiple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_multipleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 10);
        getContentPane().add(boton_multiple, gridBagConstraints);

        label_anio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_anio.setText("Año");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_anio, gridBagConstraints);

        label_titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_titulo.setText("Titulo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_titulo, gridBagConstraints);

        campo_titulo.setEditable(false);
        campo_titulo.setColumns(12);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_titulo, gridBagConstraints);

        panel_lista.setBorder(javax.swing.BorderFactory.createTitledBorder("Libros"));
        panel_lista.setPreferredSize(new java.awt.Dimension(100, 146));

        lista_libros.setModel(new DefaultListModel<Libro>()
        );
        lista_libros.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lista_librosValueChanged(evt);
            }
        });
        panel_lista.setViewportView(lista_libros);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(panel_lista, gridBagConstraints);

        yearChooser_anio.setMaximum(Calendar.getInstance().get(Calendar.YEAR));
        yearChooser_anio.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(yearChooser_anio, gridBagConstraints);

        spiner_ejemplares.setModel(new javax.swing.SpinnerNumberModel(1, 0, 999, 1));
        spiner_ejemplares.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(spiner_ejemplares, gridBagConstraints);

        panel_busquedaAvanzada.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));
        panel_busquedaAvanzada.setLayout(new java.awt.GridBagLayout());

        campo_busquedaTitulo.setText("Introduce un titulo...");
        campo_busquedaTitulo.setToolTipText("Busca un usuario por titulo");
        campo_busquedaTitulo.setPreferredSize(new java.awt.Dimension(300, 22));
        campo_busquedaTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campo_busquedaTitulocampoBusquedaClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panel_busquedaAvanzada.add(campo_busquedaTitulo, gridBagConstraints);

        campo_busquedaAutor.setText("Introduce un autor...");
        campo_busquedaAutor.setToolTipText("Busca un libro por titulo");
        campo_busquedaAutor.setPreferredSize(new java.awt.Dimension(300, 22));
        campo_busquedaAutor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campo_busquedaAutorcampoBusquedaClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panel_busquedaAvanzada.add(campo_busquedaAutor, gridBagConstraints);

        check_publicacion.setText("Año");
        check_publicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_publicacionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        panel_busquedaAvanzada.add(check_publicacion, gridBagConstraints);

        dateChooser_busqueda.setToolTipText("Busca un libro por año de publicacion");
        dateChooser_busqueda.setMaximum(Calendar.getInstance().get(Calendar.YEAR));
        dateChooser_busqueda.setPreferredSize(new java.awt.Dimension(100, 22));
        dateChooser_busqueda.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateChooser_busquedaPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panel_busquedaAvanzada.add(dateChooser_busqueda, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        panel_busquedaAvanzada.add(filler4, gridBagConstraints);

        jLabel1.setText("Titulo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_busquedaAvanzada.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Autor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_busquedaAvanzada.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.05;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        getContentPane().add(panel_busquedaAvanzada, gridBagConstraints);

        panel_busquedaSimple.setLayout(new javax.swing.BoxLayout(panel_busquedaSimple, javax.swing.BoxLayout.LINE_AXIS));
        panel_busquedaSimple.add(filler2);

        campo_busquedaSimple.setText("Introduce tu buqueda...");
        campo_busquedaSimple.setToolTipText("Busca un libro por titulo");
        campo_busquedaSimple.setMaximumSize(new java.awt.Dimension(2147483647, 25));
        campo_busquedaSimple.setMinimumSize(new java.awt.Dimension(64, 25));
        campo_busquedaSimple.setPreferredSize(new java.awt.Dimension(200, 25));
        campo_busquedaSimple.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campo_busquedaSimplecampoBusquedaClicked(evt);
            }
        });
        panel_busquedaSimple.add(campo_busquedaSimple);

        boton_busqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar_pequenio.png"))); // NOI18N
        boton_busqueda.setText("Buscar");
        boton_busqueda.setToolTipText("Buscar");
        boton_busqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        boton_busqueda.setFocusPainted(false);
        boton_busqueda.setPreferredSize(new java.awt.Dimension(90, 25));
        boton_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_busquedabusquedaUsuarios(evt);
            }
        });
        boton_busqueda.setVisible(false);
        panel_busquedaSimple.add(boton_busqueda);

        boton_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/limpiar_pequenio.png"))); // NOI18N
        boton_limpiar.setToolTipText("Limpiar");
        boton_limpiar.setBorderPainted(false);
        boton_limpiar.setContentAreaFilled(false);
        boton_limpiar.setFocusPainted(false);
        boton_limpiar.setPreferredSize(new java.awt.Dimension(40, 25));
        boton_limpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton_limpiarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton_limpiarMouseExited(evt);
            }
        });
        boton_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_limpiarActionPerformed(evt);
            }
        });
        panel_busquedaSimple.add(boton_limpiar);
        panel_busquedaSimple.add(filler1);

        check_filtros.setText("Busqueda avanzada");
        check_filtros.setToolTipText("Despliega la busqueda avanzada");
        check_filtros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_filtrosActionPerformed(evt);
            }
        });
        panel_busquedaSimple.add(check_filtros);
        panel_busquedaSimple.add(filler3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        getContentPane().add(panel_busquedaSimple, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        setLocationRelativeTo(vista_padre);
    }//GEN-LAST:event_formComponentShown

    private void boton_multipleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_multipleActionPerformed
        String texto_boton = boton_multiple.getText();

        if (texto_boton.equals("Guardar")) {

            try {
                String titulo = campo_titulo.getText();
                String autor = campo_autor.getText();
                String isbn = campo_isbn.getText();
                int n_ejemplares = (Integer) spiner_ejemplares.getValue();
                String anio = String.valueOf(yearChooser_anio.getValue());

                /* Comprobamos todos los campos */
                if (titulo.isBlank() || autor.isBlank() || isbn.isBlank()) {
                    JOptionPane.showMessageDialog(this, "Rellena todos los campos",
                            "Nuevo libro", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Libro libro = new Libro(isbn, titulo, autor, anio, n_ejemplares);
                vista_padre.guardaLibro(libro);
                rellenaDatosLibros(new Libro());

                int resultado = JOptionPane.showConfirmDialog(this, "Agregado con éxito, ¿Desea agregar más?",
                        "", JOptionPane.YES_NO_OPTION);

                if (resultado == 1) {
                    dispose();
                }
            } catch (GuardaDatosException ex) {
                JOptionPane.showMessageDialog(this, "No ha sido posible crear el libro",
                        "Nuevo libro", JOptionPane.ERROR_MESSAGE);
            } catch (DuplicadoException ex) {
                JOptionPane.showMessageDialog(this, "ISBN ya registrado",
                        "Nuevo libro", JOptionPane.ERROR_MESSAGE);
            }

        } else if (texto_boton.equals("Nuevo préstamo")) {
            if (lista_libros.getSelectedIndex() != -1) {
                Libro libro = lista_libros.getSelectedValue();

                if (libro.getN_ejemplares() < 1) {
                    JOptionPane.showMessageDialog(this, "No quedan ejemplares",
                            "Nuevo préstamo", JOptionPane.ERROR_MESSAGE);
                } else {
                    vista_padre.abreDialogoPrestamo(libro);
                    vista_padre.cambioEnListaLibros();

                }

            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un libro",
                        "Nuevo préstamo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_boton_multipleActionPerformed

    private void lista_librosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lista_librosValueChanged
        JList lista = (JList) evt.getSource();
        if (lista.hasFocus()) {
            if (!lista.getValueIsAdjusting()) {

                if (lista_libros.getSelectedIndex() != -1) {
                    Libro libro_seleccionado = (Libro) lista.getSelectedValue();
                    rellenaDatosLibros(libro_seleccionado);
                }
            }
        }
    }//GEN-LAST:event_lista_librosValueChanged

    private void campo_busquedaTitulocampoBusquedaClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campo_busquedaTitulocampoBusquedaClicked
        JTextField campo = (JTextField) evt.getSource();
        if (campo.isEnabled())
            campo.setText("");
    }//GEN-LAST:event_campo_busquedaTitulocampoBusquedaClicked

    private void campo_busquedaAutorcampoBusquedaClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campo_busquedaAutorcampoBusquedaClicked
        JTextField campo = (JTextField) evt.getSource();
        if (campo.isEnabled())
            campo.setText("");
    }//GEN-LAST:event_campo_busquedaAutorcampoBusquedaClicked

    private void check_publicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_publicacionActionPerformed
        dateChooser_busqueda.setEnabled(check_publicacion.isSelected());
        boton_busquedabusquedaUsuarios(null);
    }//GEN-LAST:event_check_publicacionActionPerformed

    private void campo_busquedaSimplecampoBusquedaClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campo_busquedaSimplecampoBusquedaClicked
        JTextField campo = (JTextField) evt.getSource();
        if (campo.isEnabled())
            campo.setText("");
    }//GEN-LAST:event_campo_busquedaSimplecampoBusquedaClicked

    private void boton_busquedabusquedaUsuarios(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_busquedabusquedaUsuarios
        HashMap<String, String> busqueda = new HashMap<>();
        String busqueda_titulo = campo_busquedaTitulo.getText();
        String busqueda_autor = campo_busquedaAutor.getText();
        String busqueda_simple = campo_busquedaSimple.getText();

        if (panel_busquedaAvanzada.isVisible()) {
            if (!busqueda_titulo.equals(texto_titulo) && !busqueda_titulo.isBlank()) {
                busqueda.put("titulo", busqueda_titulo);
            }
            if (!busqueda_autor.equals(texto_autor) && !busqueda_autor.isBlank()) {
                busqueda.put("autor", busqueda_autor);
            }
            if (check_publicacion.isSelected()) {
                busqueda.put("publicacion", String.valueOf(dateChooser_busqueda.getYear()));
            }
            if (!busqueda_simple.equals(texto_simple) && !busqueda_simple.isBlank()) {
                busqueda.put("simple", busqueda_simple);
            }

        } else if (campo_busquedaSimple.isVisible()) {
            if (!busqueda_simple.equals(texto_simple) && !busqueda_simple.isBlank()) {
                busqueda.put("simple", busqueda_simple);
            }
        }

        if (!busqueda.isEmpty()) {
            vista_padre.buscaLibros(busqueda);
        } else {
            lista_libros.setModel(modelo_lista);
        }
    }//GEN-LAST:event_boton_busquedabusquedaUsuarios

    private void boton_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_limpiarActionPerformed
        resetaFiltrosBusquedaDuro();
        lista_libros.setModel(modelo_lista);
        rellenaDatosLibros(new Libro());
        pack();
        repaint();
    }//GEN-LAST:event_boton_limpiarActionPerformed

    private void check_filtrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_filtrosActionPerformed
        if (check_filtros.isSelected()) {
            panel_busquedaAvanzada.setVisible(true);
            this.setPreferredSize(DIMENSION_GRANDE_BUSQUEDA);

        } else {
            panel_busquedaAvanzada.setVisible(false);
            this.setPreferredSize(DIMENSION_GRANDE);
        }

        repaint();
        pack();
    }//GEN-LAST:event_check_filtrosActionPerformed

    private void dateChooser_busquedaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooser_busquedaPropertyChange
        if (check_publicacion.isSelected()) {
            boton_busquedabusquedaUsuarios(null);
        }
    }//GEN-LAST:event_dateChooser_busquedaPropertyChange

    private void boton_limpiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_limpiarMouseEntered
        JButton boton = (JButton) evt.getSource();
        boton.setContentAreaFilled(true);
    }//GEN-LAST:event_boton_limpiarMouseEntered

    private void boton_limpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_limpiarMouseExited
        JButton boton = (JButton) evt.getSource();
        boton.setContentAreaFilled(false);
    }//GEN-LAST:event_boton_limpiarMouseExited

    private class ListenerCampos implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            System.out.println(e.getType());
            boton_busquedabusquedaUsuarios(null);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            boton_busquedabusquedaUsuarios(null);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // De momento no hace nada
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_busqueda;
    private javax.swing.JButton boton_limpiar;
    private javax.swing.JButton boton_multiple;
    private javax.swing.JTextField campo_autor;
    private javax.swing.JTextField campo_busquedaAutor;
    private javax.swing.JTextField campo_busquedaSimple;
    private javax.swing.JTextField campo_busquedaTitulo;
    private javax.swing.JTextField campo_isbn;
    private javax.swing.JTextField campo_titulo;
    private javax.swing.JCheckBox check_filtros;
    private javax.swing.JCheckBox check_publicacion;
    private com.toedter.calendar.JYearChooser dateChooser_busqueda;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel label_anio;
    private javax.swing.JLabel label_autor;
    private javax.swing.JLabel label_ejemplares;
    private javax.swing.JLabel label_isbn;
    private javax.swing.JLabel label_titulo;
    private javax.swing.JList<Libro> lista_libros;
    private javax.swing.JPanel panel_busquedaAvanzada;
    private javax.swing.JPanel panel_busquedaSimple;
    private javax.swing.JScrollPane panel_lista;
    private javax.swing.JSpinner spiner_ejemplares;
    private com.toedter.calendar.JYearChooser yearChooser_anio;
    // End of variables declaration//GEN-END:variables
}
