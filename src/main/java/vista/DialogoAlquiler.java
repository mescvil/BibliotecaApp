package vista;

import static extras.Colores_Dimensiones.AZUL;
import extras.ModeloAlquiler;
import modelo.Alquiler;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

import static extras.Colores_Dimensiones.DIMENSION_EXTRA;
import static extras.Colores_Dimensiones.DIMENSION_EXTRA_BUSQUEDA;

import java.awt.Component;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Escoz
 */
public class DialogoAlquiler extends javax.swing.JDialog {
    
    private final VistaPrincipal vista_padre;
    private final ModeloAlquiler modelo_tabla;
    private ModeloAlquiler modelo_busqueda;
    
    private final String texto_titulo = "Introduce un titulo...";
    private final String texto_autor = "Introduce un autor...";
    private final String texto_nombre = "Introduce un nombre...";
    private final String texto_apellido = "Introduce un apellido...";
    private final String texto_telefono = "Introduce un teléfono...";
    private final String texto_simple = "Introduce tu búsqueda...";

    /**
     * Creates new form DialogoAlquiler
     */
    public DialogoAlquiler(VistaPrincipal padre, boolean modal) {
        super(padre, modal);
        
        this.vista_padre = padre;
        this.modelo_tabla = new ModeloAlquiler();
        this.modelo_busqueda = new ModeloAlquiler();
        
        initComponents();
        setMinimumSize(DIMENSION_EXTRA);
        
        aniadeListeners();
    }
    
    private void aniadeListeners() {
        for (Component component : panel_busquedaUsuario.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).getDocument().addDocumentListener(new listenerCampos());
            }
        }
        
        for (Component component : panel_busquedaLibro.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).getDocument().addDocumentListener(new listenerCampos());
            }
        }
        
        campo_busquedaSimple.getDocument().addDocumentListener(new listenerCampos());
    }
    
    public void muestraAlquileres(ArrayList<Alquiler> alquileres) {
        
        boton_limpiarActionPerformed(null);
        
        panel_busquedaSimple.requestFocus();
        setLocationRelativeTo(vista_padre);
        ajustaTabla();
        setVisible(true);
        
        reseteaPanelFiltros();
        panel_filtros.setVisible(false);
        check_avanzadaActionPerformed(null);
        
        modelo_tabla.addAlquileres(alquileres);
        tabla_alquileres.getRowSorter().setSortKeys(null);
        tabla_alquileres.setModel(modelo_tabla);
        
        ajustaTabla();
        pack();
        repaint();
    }
    
    private void ajustaTabla() {
        
        TableColumnModel modelo_columna = tabla_alquileres.getColumnModel();
        
        modelo_columna.getColumn(1).setMinWidth(150);
        modelo_columna.getColumn(3).setMinWidth(180);
        
    }
    
    @Deprecated
    public void actualizaTabla(ArrayList<Alquiler> alquileres) {
        if (this.isVisible()) {
            ModeloAlquiler modelo = (ModeloAlquiler) tabla_alquileres.getModel();
            tabla_alquileres.getRowSorter().setSortKeys(null);
            modelo.addAlquileres(alquileres);
            ajustaTabla();
        }
    }
    
    public void actualizaTablaBusqueda(ArrayList<Alquiler> alquileres_encontrados) {
        modelo_busqueda = new ModeloAlquiler();
        
        if (!alquileres_encontrados.isEmpty()) {
            modelo_busqueda.addAlquileres(alquileres_encontrados);
            tabla_alquileres.setModel(modelo_busqueda);
            ajustaTabla();
            
        } else if (this.isVisible()) {
            modelo_busqueda.addAlquileres(alquileres_encontrados);
            tabla_alquileres.setModel(modelo_busqueda);
            ajustaTabla();
            
        }
        
        repaint();
    }
    
    public void actualizaTablaDevolucion(ArrayList<Alquiler> alquileres_actualizados) {
        modelo_tabla.addAlquileres(alquileres_actualizados);
        boton_busquedaActionPerformed(null);
        ajustaTabla();
    }
    
    private void reseteaPanelFiltros() {
        check_avanzada.setSelected(false);
        reseteaFiltrosBusquedaUsuario();
        reseteaFiltrosBusquedaLibro();
        
    }
    
    public void reseteaFiltrosBusquedaUsuario() {
        // Reseteamos todos los componentes de tipo JCheckBox dentro del panel de búsqueda
        for (Component component : panel_busquedaUsuario.getComponents()) {
            if (component instanceof JCheckBox) {
                ((JCheckBox) component).setSelected(false);
                
            }
        }
        dateChooser_busquedaUsuario.setEnabled(false);
        
        campo_busquedaNombre.setText(texto_nombre);
        campo_busquedaApellidos.setText(texto_apellido);
        campo_busquedaTelefono.setText(texto_telefono);
        dateChooser_busquedaUsuario.setYear(Calendar.getInstance().get(Calendar.YEAR));
        
        campo_busquedaSimple.setText(texto_simple);
        
    }
    
    public void reseteaFiltrosBusquedaLibro() {
        // Reseteamos todos los componentes de tipo JCheckBox dentro del panel de búsqueda
        for (Component component : panel_busquedaLibro.getComponents()) {
            if (component instanceof JCheckBox) {
                ((JCheckBox) component).setSelected(false);
                
            }
        }
        dateChooser_busquedaLibro.setEnabled(false);
        
        campo_busquedaTitulo.setText(texto_titulo);
        campo_busquedaAutor.setText(texto_autor);
        dateChooser_busquedaLibro.setYear(Calendar.getInstance().get(Calendar.YEAR));
        
        campo_busquedaSimple.setText(texto_simple);
        
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pop_alquileres = new javax.swing.JPopupMenu();
        popMenu_devolucion = new javax.swing.JMenuItem();
        popMenu_verLibro = new javax.swing.JMenuItem();
        popMenu_verUsuario = new javax.swing.JMenuItem();
        grupo_buscador = new javax.swing.ButtonGroup();
        panel_busquedaSimple = new javax.swing.JPanel();
        campo_busquedaSimple = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        boton_busqueda = new javax.swing.JButton();
        boton_limpiar = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(32767, 0));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0));
        check_avanzada = new javax.swing.JCheckBox();
        panel_filtros = new javax.swing.JPanel();
        panel_busquedaUsuario = new javax.swing.JPanel();
        campo_busquedaNombre = new javax.swing.JTextField();
        campo_busquedaTelefono = new javax.swing.JTextField();
        campo_busquedaApellidos = new javax.swing.JTextField();
        check_nacimiento = new javax.swing.JCheckBox();
        dateChooser_busquedaUsuario = new com.toedter.calendar.JYearChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panel_busquedaLibro = new javax.swing.JPanel();
        campo_busquedaTitulo = new javax.swing.JTextField();
        campo_busquedaAutor = new javax.swing.JTextField();
        check_publicacion = new javax.swing.JCheckBox();
        dateChooser_busquedaLibro = new com.toedter.calendar.JYearChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_alquileres = new javax.swing.JTable();

        popMenu_devolucion.setText("Devolver");
        popMenu_devolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMenu_devolucionActionPerformed(evt);
            }
        });
        pop_alquileres.add(popMenu_devolucion);

        popMenu_verLibro.setText("Datos del libro...");
        popMenu_verLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMenu_verLibroActionPerformed(evt);
            }
        });
        pop_alquileres.add(popMenu_verLibro);

        popMenu_verUsuario.setText("Datos del usuario...");
        popMenu_verUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popMenu_verUsuarioActionPerformed(evt);
            }
        });
        pop_alquileres.add(popMenu_verUsuario);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de alquileres");
        setPreferredSize(new java.awt.Dimension(800, 400));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        panel_busquedaSimple.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 0, 10));
        panel_busquedaSimple.setMaximumSize(new java.awt.Dimension(2147483647, 32));
        panel_busquedaSimple.setLayout(new javax.swing.BoxLayout(panel_busquedaSimple, javax.swing.BoxLayout.LINE_AXIS));

        campo_busquedaSimple.setText("Introduce tu busqueda...");
        campo_busquedaSimple.setToolTipText("Busca un alquiler por un usuario o libro");
        campo_busquedaSimple.setMinimumSize(new java.awt.Dimension(64, 27));
        campo_busquedaSimple.setPreferredSize(new java.awt.Dimension(141, 27));
        campo_busquedaSimple.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campo_busquedaSimpleMouseClicked(evt);
            }
        });
        panel_busquedaSimple.add(campo_busquedaSimple);
        panel_busquedaSimple.add(filler1);

        boton_busqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar_pequenio.png"))); // NOI18N
        boton_busqueda.setToolTipText("Buscar");
        boton_busqueda.setBorderPainted(false);
        boton_busqueda.setContentAreaFilled(false);
        boton_busqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        boton_busqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton_busquedamouseEncima(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton_busquedamouseFuera(evt);
            }
        });
        boton_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_busquedaActionPerformed(evt);
            }
        });
        boton_busqueda.setVisible(false);
        panel_busquedaSimple.add(boton_busqueda);

        boton_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/limpiar_pequenio.png"))); // NOI18N
        boton_limpiar.setToolTipText("Limpiar");
        boton_limpiar.setBorderPainted(false);
        boton_limpiar.setContentAreaFilled(false);
        boton_limpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton_limpiarmouseEncima(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton_limpiarmouseFuera(evt);
            }
        });
        boton_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_limpiarActionPerformed(evt);
            }
        });
        panel_busquedaSimple.add(boton_limpiar);
        panel_busquedaSimple.add(filler5);
        panel_busquedaSimple.add(filler4);

        check_avanzada.setText("Avanzada");
        check_avanzada.setToolTipText("Despliega el menu de busqueda avanzada");
        check_avanzada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_avanzadaActionPerformed(evt);
            }
        });
        panel_busquedaSimple.add(check_avanzada);

        getContentPane().add(panel_busquedaSimple);

        panel_filtros.setLayout(new java.awt.GridLayout());

        panel_busquedaUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 0, 5), javax.swing.BorderFactory.createTitledBorder("Filtros de usuarios")));
        panel_busquedaUsuario.setLayout(new java.awt.GridBagLayout());

        campo_busquedaNombre.setText("Introduce un nombre...");
        campo_busquedaNombre.setToolTipText("Busca un alquiler por nombre");
        campo_busquedaNombre.setPreferredSize(new java.awt.Dimension(300, 22));
        campo_busquedaNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campo_busquedaNombrecampoBusquedaClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panel_busquedaUsuario.add(campo_busquedaNombre, gridBagConstraints);

        campo_busquedaTelefono.setText("Introduce un telefono...");
        campo_busquedaTelefono.setToolTipText("Busca un alquiler por teléfono");
        campo_busquedaTelefono.setPreferredSize(new java.awt.Dimension(300, 22));
        campo_busquedaTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campo_busquedaTelefonocampoBusquedaClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panel_busquedaUsuario.add(campo_busquedaTelefono, gridBagConstraints);

        campo_busquedaApellidos.setText("Introduce un apellido...");
        campo_busquedaApellidos.setToolTipText("Busca un alquiler por apellidos");
        campo_busquedaApellidos.setPreferredSize(new java.awt.Dimension(300, 22));
        campo_busquedaApellidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campo_busquedaApellidoscampoBusquedaClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panel_busquedaUsuario.add(campo_busquedaApellidos, gridBagConstraints);

        check_nacimiento.setText("Nacimiento");
        check_nacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_nacimientoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 0);
        panel_busquedaUsuario.add(check_nacimiento, gridBagConstraints);

        dateChooser_busquedaUsuario.setToolTipText("Busca un alquiler por año de nacimiento");
        dateChooser_busquedaUsuario.setMaximum(Calendar.getInstance().get(Calendar.YEAR));
        dateChooser_busquedaUsuario.setPreferredSize(new java.awt.Dimension(100, 22));
        dateChooser_busquedaUsuario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateChooser_busquedaUsuarioPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 5);
        panel_busquedaUsuario.add(dateChooser_busquedaUsuario, gridBagConstraints);

        jLabel4.setText("Apellidos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_busquedaUsuario.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Teléfono");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_busquedaUsuario.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_busquedaUsuario.add(jLabel6, gridBagConstraints);

        panel_filtros.add(panel_busquedaUsuario);

        panel_busquedaLibro.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 0, 5), javax.swing.BorderFactory.createTitledBorder("Filtros de libros")));
        panel_busquedaLibro.setLayout(new java.awt.GridBagLayout());

        campo_busquedaTitulo.setText("Introduce un titulo...");
        campo_busquedaTitulo.setToolTipText("Busca un alquiler por titulo");
        campo_busquedaTitulo.setPreferredSize(new java.awt.Dimension(300, 22));
        campo_busquedaTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campo_busquedaTitulocampoBusquedaClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panel_busquedaLibro.add(campo_busquedaTitulo, gridBagConstraints);

        campo_busquedaAutor.setText("Introduce un autor...");
        campo_busquedaAutor.setToolTipText("Busca un alquiler por autor");
        campo_busquedaAutor.setPreferredSize(new java.awt.Dimension(300, 22));
        campo_busquedaAutor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campo_busquedaAutorcampoBusquedaClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panel_busquedaLibro.add(campo_busquedaAutor, gridBagConstraints);

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
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 3, 0);
        panel_busquedaLibro.add(check_publicacion, gridBagConstraints);

        dateChooser_busquedaLibro.setToolTipText("Busca un alquiler por año de publicacion");
        dateChooser_busquedaLibro.setMaximum(Calendar.getInstance().get(Calendar.YEAR));
        dateChooser_busquedaLibro.setPreferredSize(new java.awt.Dimension(100, 22));
        dateChooser_busquedaLibro.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateChooser_busquedaLibroPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 3, 5);
        panel_busquedaLibro.add(dateChooser_busquedaLibro, gridBagConstraints);

        jLabel2.setText("Autor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_busquedaLibro.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Titulo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_busquedaLibro.add(jLabel3, gridBagConstraints);

        panel_filtros.add(panel_busquedaLibro);

        getContentPane().add(panel_filtros);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10), javax.swing.BorderFactory.createTitledBorder("")));

        tabla_alquileres.setAutoCreateRowSorter(true);
        tabla_alquileres.setModel(modelo_tabla);
        tabla_alquileres.setComponentPopupMenu(pop_alquileres);
        tabla_alquileres.getTableHeader().setReorderingAllowed(false);
        tabla_alquileres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_alquileresMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_alquileres);

        getContentPane().add(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        setLocationRelativeTo(vista_padre);
    }//GEN-LAST:event_formComponentShown

    private void campo_busquedaSimpleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campo_busquedaSimpleMouseClicked
        JTextField campo = (JTextField) evt.getSource();
        campo.setText("");
    }//GEN-LAST:event_campo_busquedaSimpleMouseClicked

    private void boton_busquedamouseEncima(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_busquedamouseEncima
        JButton boton = (JButton) evt.getSource();
        boton.setContentAreaFilled(true);
    }//GEN-LAST:event_boton_busquedamouseEncima

    private void boton_busquedamouseFuera(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_busquedamouseFuera
        JButton boton = (JButton) evt.getSource();
        boton.setContentAreaFilled(false);
    }//GEN-LAST:event_boton_busquedamouseFuera

    private void boton_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_busquedaActionPerformed
        HashMap<String, String> busqueda = new HashMap<>();
        String busqueda_simple = campo_busquedaSimple.getText();
        
        if (!check_avanzada.isSelected()) {
            if (!busqueda_simple.equals(texto_simple) && !busqueda_simple.isBlank()) {
                busqueda.put("simple", busqueda_simple);
            }
            
        } else {
            String busqueda_titulo = campo_busquedaTitulo.getText();
            String busqueda_autor = campo_busquedaAutor.getText();
            String busqueda_nombre = campo_busquedaNombre.getText();
            String busqueda_apellidos = campo_busquedaApellidos.getText();
            String busqueda_telefono = campo_busquedaTelefono.getText();
            
            if (!busqueda_titulo.equals(texto_titulo) && !busqueda_titulo.isBlank()) {
                busqueda.put("titulo", busqueda_titulo);
            }
            if (!busqueda_autor.equals(texto_autor) && !busqueda_autor.isBlank()) {
                busqueda.put("autor", busqueda_autor);
            }
            if (check_publicacion.isSelected()) {
                busqueda.put("publicacion", String.valueOf(dateChooser_busquedaLibro.getYear()));
            }
            if (!busqueda_nombre.equals(texto_nombre) && !busqueda_nombre.isBlank()) {
                busqueda.put("nombre", busqueda_nombre);
            }
            if (!busqueda_apellidos.equals(texto_apellido) && !busqueda_apellidos.isBlank()) {
                busqueda.put("apellidos", busqueda_apellidos);
            }
            if (!busqueda_telefono.equals(texto_telefono) && !busqueda_telefono.isBlank()) {
                busqueda.put("telefono", busqueda_telefono);
            }
            if (check_nacimiento.isSelected()) {
                busqueda.put("anio", String.valueOf(dateChooser_busquedaUsuario.getYear()));
            }
            if (!busqueda_simple.equals(texto_simple) && !busqueda_simple.isBlank()) {
                busqueda.put("simple", busqueda_simple);
            }
        }
        
        if (!busqueda.isEmpty()) {
            vista_padre.buscaAlquileres(busqueda);
        } else {
            tabla_alquileres.setModel(modelo_tabla);
            ajustaTabla();
        }
    }//GEN-LAST:event_boton_busquedaActionPerformed

    private void boton_limpiarmouseEncima(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_limpiarmouseEncima
        JButton boton = (JButton) evt.getSource();
        boton.setContentAreaFilled(true);
    }//GEN-LAST:event_boton_limpiarmouseEncima

    private void boton_limpiarmouseFuera(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_limpiarmouseFuera
        JButton boton = (JButton) evt.getSource();
        boton.setContentAreaFilled(false);
    }//GEN-LAST:event_boton_limpiarmouseFuera

    private void boton_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_limpiarActionPerformed
        reseteaFiltrosBusquedaLibro();
        reseteaFiltrosBusquedaUsuario();
        
        tabla_alquileres.setModel(modelo_tabla);
        ajustaTabla();
    }//GEN-LAST:event_boton_limpiarActionPerformed

    private void tabla_alquileresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_alquileresMousePressed
        int fila_actual = tabla_alquileres.rowAtPoint(evt.getPoint());
        tabla_alquileres.setRowSelectionInterval(fila_actual, fila_actual);
    }//GEN-LAST:event_tabla_alquileresMousePressed

    private void popMenu_verLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMenu_verLibroActionPerformed
        int fila = tabla_alquileres.getSelectedRow();
        ModeloAlquiler modelo = (ModeloAlquiler) tabla_alquileres.getModel();
        Alquiler alquiler_seleccionado = modelo.getAlquiler(fila);
        
        vista_padre.abreDialogoVerLibros(alquiler_seleccionado.getLibro());
    }//GEN-LAST:event_popMenu_verLibroActionPerformed

    private void popMenu_verUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMenu_verUsuarioActionPerformed
        int fila = tabla_alquileres.getSelectedRow();
        ModeloAlquiler modelo = (ModeloAlquiler) tabla_alquileres.getModel();
        Alquiler alquiler_seleccionado = modelo.getAlquiler(fila);
        
        vista_padre.abreDialogoVerUsuarios(alquiler_seleccionado.getUsuario());
    }//GEN-LAST:event_popMenu_verUsuarioActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void popMenu_devolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popMenu_devolucionActionPerformed
        int fila = tabla_alquileres.getSelectedRow();
        ModeloAlquiler modelo = (ModeloAlquiler) tabla_alquileres.getModel();
        Alquiler alquiler_seleccionado = modelo.getAlquiler(fila);
        
        vista_padre.realizaDevolucion(alquiler_seleccionado);
    }//GEN-LAST:event_popMenu_devolucionActionPerformed

    private void campo_busquedaNombrecampoBusquedaClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campo_busquedaNombrecampoBusquedaClicked
        JTextField campo = (JTextField) evt.getSource();
        if (campo.isEnabled())
            campo.setText("");
    }//GEN-LAST:event_campo_busquedaNombrecampoBusquedaClicked

    private void campo_busquedaTelefonocampoBusquedaClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campo_busquedaTelefonocampoBusquedaClicked
        JTextField campo = (JTextField) evt.getSource();
        if (campo.isEnabled())
            campo.setText("");
    }//GEN-LAST:event_campo_busquedaTelefonocampoBusquedaClicked

    private void campo_busquedaApellidoscampoBusquedaClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campo_busquedaApellidoscampoBusquedaClicked
        JTextField campo = (JTextField) evt.getSource();
        if (campo.isEnabled())
            campo.setText("");
    }//GEN-LAST:event_campo_busquedaApellidoscampoBusquedaClicked

    private void check_nacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_nacimientoActionPerformed
        dateChooser_busquedaUsuario.setEnabled(check_nacimiento.isSelected());
    }//GEN-LAST:event_check_nacimientoActionPerformed

    private void check_avanzadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_avanzadaActionPerformed
        if (check_avanzada.isSelected()) {
            this.setPreferredSize(DIMENSION_EXTRA_BUSQUEDA);
            panel_filtros.setVisible(true);
            
        } else {
            panel_filtros.setVisible(false);
            this.setPreferredSize(DIMENSION_EXTRA);
        }
        
        pack();
        repaint();
    }//GEN-LAST:event_check_avanzadaActionPerformed

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
        dateChooser_busquedaLibro.setEnabled(check_publicacion.isSelected());
    }//GEN-LAST:event_check_publicacionActionPerformed

    private void dateChooser_busquedaLibroPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooser_busquedaLibroPropertyChange
        if (check_publicacion.isSelected()) {
            boton_busquedaActionPerformed(null);
        }
    }//GEN-LAST:event_dateChooser_busquedaLibroPropertyChange

    private void dateChooser_busquedaUsuarioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooser_busquedaUsuarioPropertyChange
        if (check_nacimiento.isSelected()) {
            boton_busquedaActionPerformed(null);
        }
    }//GEN-LAST:event_dateChooser_busquedaUsuarioPropertyChange
    
    private class listenerCampos implements DocumentListener {
        
        @Override
        public void insertUpdate(DocumentEvent e) {
            boton_busquedaActionPerformed(null);
        }
        
        @Override
        public void removeUpdate(DocumentEvent e) {
            boton_busquedaActionPerformed(null);
        }
        
        @Override
        public void changedUpdate(DocumentEvent e) {
            // De momento no hace nada
        }
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_busqueda;
    private javax.swing.JButton boton_limpiar;
    private javax.swing.JTextField campo_busquedaApellidos;
    private javax.swing.JTextField campo_busquedaAutor;
    private javax.swing.JTextField campo_busquedaNombre;
    private javax.swing.JTextField campo_busquedaSimple;
    private javax.swing.JTextField campo_busquedaTelefono;
    private javax.swing.JTextField campo_busquedaTitulo;
    private javax.swing.JCheckBox check_avanzada;
    private javax.swing.JCheckBox check_nacimiento;
    private javax.swing.JCheckBox check_publicacion;
    private com.toedter.calendar.JYearChooser dateChooser_busquedaLibro;
    private com.toedter.calendar.JYearChooser dateChooser_busquedaUsuario;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.ButtonGroup grupo_buscador;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_busquedaLibro;
    private javax.swing.JPanel panel_busquedaSimple;
    private javax.swing.JPanel panel_busquedaUsuario;
    private javax.swing.JPanel panel_filtros;
    private javax.swing.JMenuItem popMenu_devolucion;
    private javax.swing.JMenuItem popMenu_verLibro;
    private javax.swing.JMenuItem popMenu_verUsuario;
    private javax.swing.JPopupMenu pop_alquileres;
    private javax.swing.JTable tabla_alquileres;
    // End of variables declaration//GEN-END:variables
}
