/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import excepciones.GuardaDatosException;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import static extras.Colores_Dimensiones.*;

/**
 *
 * @author Escoz
 */
public class DialogoPersona extends javax.swing.JDialog {

    private VistaPrincipal vista_padre;

    private DefaultListModel modelo_lista;
    private DefaultListModel modelo_busqueda;

    /**
     * Creates new form DialogoPersona
     */
    public DialogoPersona(VistaPrincipal parent, boolean modal, DefaultListModel modelo_lista) {
        super(parent, modal);
        this.vista_padre = parent;

        initComponents();
        this.lista_Usuarios.setModel(this.modelo_lista = modelo_lista);
        modelo_busqueda = new DefaultListModel();

    }

    public void muestraModoAniadir() {

        // Campos del usuario
        campo_nombre.setEditable(true);
        campo_segApellido.setEditable(true);
        campo_priApellido.setEditable(true);
        campo_dni.setEditable(true);
        campo_email.setEditable(true);
        campo_telefono.setEditable(true);
        dateChooser_nacimiento.setEnabled(true);
        dateChooser_nacimiento.setDate(new Date());
        rellenaDatosPersona(new Usuario()); // Si se pasa un usurio vacia resetea los campos

        // Boton de uso multiple
        boton_multiple.setVisible(true);
        boton_multiple.setText("Guardar");
        boton_multiple.setBackground(VERDE);

        // Lista de usuarios
        lista_Usuarios.setEnabled(false);
        lista_Usuarios.clearSelection();

        // Busqueda y filtros
        panel_busquedaSimple.setVisible(false);
        panel_busquedaAvanzada.setVisible(false);
        check_filtros.setVisible(false);

        // General 
        setPreferredSize(DIMENSION_GRANDE);
        this.setTitle("Nuevo usuario");

        setLocationRelativeTo(vista_padre);
        pack();
        setVisible(true);

    }

    public void muestraModoVer() {

        // Campos del usuario
        campo_nombre.setEditable(false);
        campo_segApellido.setEditable(false);
        campo_priApellido.setEditable(false);
        campo_dni.setEditable(false);
        campo_email.setEditable(false);
        campo_telefono.setEditable(false);
        dateChooser_nacimiento.setEnabled(false);
        rellenaDatosPersona(new Usuario());

        // Boton de uso multiple
        boton_multiple.setVisible(false);
        boton_multiple.setText("Cerrar");
        boton_multiple.setBackground(ROJO);

        // Lista de usuarios
        lista_Usuarios.setEnabled(true);
        panel_busquedaAvanzada.setVisible(true);
        lista_Usuarios.setModel(modelo_lista);

        // Busqueda y filtros
        panel_busquedaSimple.setVisible(true);
        boton_limpiar.setVisible(false);
        boton_busqueda.setVisible(true);
        panel_busquedaAvanzada.setVisible(false);
        filler_busqueda.setVisible(true);
        check_filtros.setVisible(true);
        campo_busquedaSimple.setVisible(true);
        reseteaPanelFiltros();

        // General 
        this.setTitle("Usuarios registrados");
        setPreferredSize(DIMENSION_GRANDE);
        setLocationRelativeTo(vista_padre);
        lista_Usuarios.requestFocus();

        pack();
        setVisible(true);
    }

    public void muestraModoVer(Usuario usuario) {
        muestraModoVer();
        rellenaDatosPersona(usuario);
    }

    private void reseteaPanelFiltros() {
        check_filtros.setSelected(false);
        resetaFiltrosBusquedaDuro();

    }

    public void resetaFiltrosBusquedaDuro() {
        // Reseteamos todos los componentes de tipo JCheckBox dentro del panel de busqueda
        for (Component component : panel_busquedaAvanzada.getComponents()) {
            if (component instanceof JCheckBox) {
                ((JCheckBox) component).setSelected(false);

            }
            if (component instanceof JTextField) {
                component.setEnabled(false);
            }
        }
        dateChooser_busqueda.setEnabled(false);

        campo_busquedaNombre.setText("Introduce un nombre...");
        campo_busquedaApellidos.setText("Introduce un apellido...");
        campo_busquedaTelefono.setText("Introduce un telefono...");
        dateChooser_busqueda.setYear(Calendar.getInstance().get(Calendar.YEAR));

        campo_busquedaSimple.setText("Introduce tu buqueda...");

        boton_limpiar.setVisible(false);
        boton_busqueda.setVisible(true);
    }

    public void actualizaListaUsuarios(ArrayList<Usuario> usuarios_encontrados) {

        if (!usuarios_encontrados.isEmpty()) {
            modelo_busqueda.clear();
            modelo_busqueda.addAll(usuarios_encontrados);
            lista_Usuarios.setModel(modelo_busqueda);
            boton_limpiar.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Sin resultados",
                    "Busqueda de usuarios", JOptionPane.INFORMATION_MESSAGE);
        }
        pack();
        repaint();
    }

    public void rellenaDatosPersona(Usuario usuario) {
        String nombre = usuario.getNombre();
        String pri_apellido = usuario.getApellido_1();
        String seg_apellido = usuario.getApellido_2();
        String dni = usuario.getDni();
        String telefono = usuario.getTelefono();
        String email = usuario.getCorreo();
        Date nacimiento = usuario.getFecha_nacimiento().getTime();

        campo_nombre.setText(nombre);
        campo_segApellido.setText(seg_apellido);
        campo_priApellido.setText(pri_apellido);
        campo_dni.setText(dni);
        campo_email.setText(email);
        campo_telefono.setText(telefono);
        dateChooser_nacimiento.setDate(nacimiento);
    }

    private void guardaUsuario() {
        try {
            String nombre = campo_nombre.getText();
            String apellido_1 = campo_priApellido.getText();
            String apellido_2 = campo_segApellido.getText();
            String dni = campo_dni.getText();
            String telf = campo_telefono.getText();
            String correo = campo_email.getText();
            GregorianCalendar f_nacimiento = new GregorianCalendar();

            /* Comprobamos todos los campos */
            if (nombre.isBlank() || apellido_1.isBlank() || apellido_2.isBlank() || dni.isBlank() || telf.isBlank() || correo.isBlank()) {
                JOptionPane.showMessageDialog(this, "Rellena todos los campos",
                        "Nuevo usuario", JOptionPane.WARNING_MESSAGE);
                return;
            }

            f_nacimiento.setTime(dateChooser_nacimiento.getDate());
            Usuario u = new Usuario(dni, nombre, apellido_1, apellido_2, telf, correo, f_nacimiento);

            vista_padre.guardaUsuario(u);
            modelo_lista.addElement(u);

            rellenaDatosPersona(new Usuario());

            int resultado = JOptionPane.showConfirmDialog(this, "Agregado con éxito, ¿Desea agregar más?",
                    "", JOptionPane.YES_NO_OPTION);

            if (resultado == 1) {
                dispose();
            }
        } catch (GuardaDatosException ex) {
            JOptionPane.showMessageDialog(this, "No ha sido posible crear un nuevo usuario",
                    "Nuevo usuario", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        label_dni = new javax.swing.JLabel();
        campo_dni = new javax.swing.JTextField();
        label_priApellido = new javax.swing.JLabel();
        label_telefono = new javax.swing.JLabel();
        campo_priApellido = new javax.swing.JTextField();
        campo_telefono = new javax.swing.JTextField();
        label_email = new javax.swing.JLabel();
        label_fechNacimiento = new javax.swing.JLabel();
        campo_email = new javax.swing.JTextField();
        boton_multiple = new javax.swing.JButton();
        label_segApellido = new javax.swing.JLabel();
        campo_segApellido = new javax.swing.JTextField();
        label_nombre = new javax.swing.JLabel();
        campo_nombre = new javax.swing.JTextField();
        dateChooser_nacimiento = new com.toedter.calendar.JDateChooser();
        panel_lista = new javax.swing.JScrollPane();
        lista_Usuarios = new javax.swing.JList<>();
        panel_busquedaAvanzada = new javax.swing.JPanel();
        campo_busquedaNombre = new javax.swing.JTextField();
        campo_busquedaTelefono = new javax.swing.JTextField();
        campo_busquedaApellidos = new javax.swing.JTextField();
        check_nacimiento = new javax.swing.JCheckBox();
        check_telefono = new javax.swing.JCheckBox();
        check_apellidos = new javax.swing.JCheckBox();
        chek_nombre = new javax.swing.JCheckBox();
        dateChooser_busqueda = new com.toedter.calendar.JYearChooser();
        panel_busquedaSimple = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        campo_busquedaSimple = new javax.swing.JTextField();
        filler_busqueda = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        boton_busqueda = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(10, 32767));
        boton_limpiar = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        check_filtros = new javax.swing.JCheckBox();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos de usuario");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        label_dni.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_dni.setText("DNI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 9);
        getContentPane().add(label_dni, gridBagConstraints);

        campo_dni.setEditable(false);
        campo_dni.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 5, 10);
        getContentPane().add(campo_dni, gridBagConstraints);

        label_priApellido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_priApellido.setText("Primer Apellido");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_priApellido, gridBagConstraints);

        label_telefono.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_telefono.setText("Teléfono");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_telefono, gridBagConstraints);

        campo_priApellido.setEditable(false);
        campo_priApellido.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_priApellido, gridBagConstraints);

        campo_telefono.setEditable(false);
        campo_telefono.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_telefono, gridBagConstraints);

        label_email.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_email.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_email, gridBagConstraints);

        label_fechNacimiento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_fechNacimiento.setText("Fecha nacimiento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_fechNacimiento, gridBagConstraints);

        campo_email.setEditable(false);
        campo_email.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_email, gridBagConstraints);

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
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(boton_multiple, gridBagConstraints);

        label_segApellido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_segApellido.setText("Segundo Apellido");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_segApellido, gridBagConstraints);

        campo_segApellido.setEditable(false);
        campo_segApellido.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_segApellido, gridBagConstraints);

        label_nombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_nombre.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_nombre, gridBagConstraints);

        campo_nombre.setEditable(false);
        campo_nombre.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_nombre, gridBagConstraints);

        dateChooser_nacimiento.setForeground(new java.awt.Color(255, 255, 255));
        dateChooser_nacimiento.setDateFormatString("d/MM/y");
        dateChooser_nacimiento.setMaxSelectableDate(new Date());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(dateChooser_nacimiento, gridBagConstraints);

        panel_lista.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuarios"));
        panel_lista.setPreferredSize(new java.awt.Dimension(100, 146));

        lista_Usuarios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lista_Usuarios.setMinimumSize(new java.awt.Dimension(50, 90));
        lista_Usuarios.setPreferredSize(new java.awt.Dimension(50, 90));
        lista_Usuarios.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lista_UsuariosValueChanged(evt);
            }
        });
        panel_lista.setViewportView(lista_Usuarios);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(panel_lista, gridBagConstraints);

        panel_busquedaAvanzada.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));
        panel_busquedaAvanzada.setLayout(new java.awt.GridBagLayout());

        campo_busquedaNombre.setText("Introduce un nombre...");
        campo_busquedaNombre.setToolTipText("Busca un usuario por nombre");
        campo_busquedaNombre.setPreferredSize(new java.awt.Dimension(250, 22));
        campo_busquedaNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoBusquedaClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panel_busquedaAvanzada.add(campo_busquedaNombre, gridBagConstraints);

        campo_busquedaTelefono.setText("Introduce un telefono...");
        campo_busquedaTelefono.setToolTipText("Busca un usuario por teléfono");
        campo_busquedaTelefono.setPreferredSize(new java.awt.Dimension(250, 22));
        campo_busquedaTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoBusquedaClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panel_busquedaAvanzada.add(campo_busquedaTelefono, gridBagConstraints);

        campo_busquedaApellidos.setText("Introduce un apellido...");
        campo_busquedaApellidos.setToolTipText("Busca un usuario por apellidos");
        campo_busquedaApellidos.setPreferredSize(new java.awt.Dimension(250, 22));
        campo_busquedaApellidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoBusquedaClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panel_busquedaAvanzada.add(campo_busquedaApellidos, gridBagConstraints);

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
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        panel_busquedaAvanzada.add(check_nacimiento, gridBagConstraints);

        check_telefono.setText("Teléfono");
        check_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_telefonoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        panel_busquedaAvanzada.add(check_telefono, gridBagConstraints);

        check_apellidos.setText("Apellidos");
        check_apellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_apellidosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        panel_busquedaAvanzada.add(check_apellidos, gridBagConstraints);

        chek_nombre.setText("Nombre");
        chek_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chek_nombreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        panel_busquedaAvanzada.add(chek_nombre, gridBagConstraints);

        dateChooser_busqueda.setToolTipText("Busca un usuario por año de nacimiento");
        dateChooser_busqueda.setMaximum(Calendar.getInstance().get(Calendar.YEAR));
        dateChooser_busqueda.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        panel_busquedaAvanzada.add(dateChooser_busqueda, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        getContentPane().add(panel_busquedaAvanzada, gridBagConstraints);

        panel_busquedaSimple.setLayout(new javax.swing.BoxLayout(panel_busquedaSimple, javax.swing.BoxLayout.LINE_AXIS));
        panel_busquedaSimple.add(filler2);

        campo_busquedaSimple.setText("Introduce tu buqueda...");
        campo_busquedaSimple.setToolTipText("Busca un usuario por nombre o apellidos");
        campo_busquedaSimple.setMaximumSize(new java.awt.Dimension(2147483647, 27));
        campo_busquedaSimple.setMinimumSize(new java.awt.Dimension(64, 27));
        campo_busquedaSimple.setPreferredSize(new java.awt.Dimension(200, 20));
        campo_busquedaSimple.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoBusquedaClicked(evt);
            }
        });
        panel_busquedaSimple.add(campo_busquedaSimple);
        panel_busquedaSimple.add(filler_busqueda);

        boton_busqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar_pequenio.png"))); // NOI18N
        boton_busqueda.setText("Buscar");
        boton_busqueda.setToolTipText("Buscar");
        boton_busqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        boton_busqueda.setFocusPainted(false);
        boton_busqueda.setPreferredSize(new java.awt.Dimension(90, 25));
        boton_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaUsuarios(evt);
            }
        });
        panel_busquedaSimple.add(boton_busqueda);
        panel_busquedaSimple.add(filler5);

        boton_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/limpiar_pequenio.png"))); // NOI18N
        boton_limpiar.setText("Limpiar");
        boton_limpiar.setToolTipText("Limpiar");
        boton_limpiar.setFocusPainted(false);
        boton_limpiar.setPreferredSize(new java.awt.Dimension(94, 25));
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

    private void boton_multipleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_multipleActionPerformed
        String texto_boton = boton_multiple.getText();

        if (texto_boton.equals("Guardar")) {
            guardaUsuario();
        } else {
            dispose();
        }
    }//GEN-LAST:event_boton_multipleActionPerformed

    private void lista_UsuariosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lista_UsuariosValueChanged
        JList lista = (JList) evt.getSource();
        if (lista.hasFocus()) {
            if (!lista.getValueIsAdjusting()) {

                Usuario usuario_seleccionado = (Usuario) lista.getSelectedValue();
                rellenaDatosPersona(usuario_seleccionado);
            }
        }
    }//GEN-LAST:event_lista_UsuariosValueChanged

    private void boton_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_limpiarActionPerformed
        resetaFiltrosBusquedaDuro();
        lista_Usuarios.setModel(modelo_lista);
        pack();
        repaint();
    }//GEN-LAST:event_boton_limpiarActionPerformed

    private void campoBusquedaClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campoBusquedaClicked
        JTextField campo = (JTextField) evt.getSource();
        if (campo.isEnabled())
            campo.setText("");
    }//GEN-LAST:event_campoBusquedaClicked

    private void check_filtrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_filtrosActionPerformed
        if (check_filtros.isSelected()) {
            panel_busquedaAvanzada.setVisible(true);
            this.setPreferredSize(DIMENSION_GRANDE_BUSQUEDA);
            campo_busquedaSimple.setVisible(false);
            filler_busqueda.setVisible(false);
        } else {
            panel_busquedaAvanzada.setVisible(false);
            this.setPreferredSize(DIMENSION_GRANDE);
            campo_busquedaSimple.setVisible(true);
            filler_busqueda.setVisible(true);
        }
        repaint();
        pack();
    }//GEN-LAST:event_check_filtrosActionPerformed

    private void chek_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chek_nombreActionPerformed
        campo_busquedaNombre.setEnabled(chek_nombre.isSelected());
    }//GEN-LAST:event_chek_nombreActionPerformed

    private void check_apellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_apellidosActionPerformed
        campo_busquedaApellidos.setEnabled(check_apellidos.isSelected());
    }//GEN-LAST:event_check_apellidosActionPerformed

    private void check_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_telefonoActionPerformed
        campo_busquedaTelefono.setEnabled(check_telefono.isSelected());
    }//GEN-LAST:event_check_telefonoActionPerformed

    private void check_nacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_nacimientoActionPerformed
        dateChooser_busqueda.setEnabled(check_nacimiento.isSelected());
    }//GEN-LAST:event_check_nacimientoActionPerformed

    private void busquedaUsuarios(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaUsuarios
        HashMap<String, String> busqueda = new HashMap<>();

        if (panel_busquedaAvanzada.isVisible()) {
            if (chek_nombre.isSelected()) {
                busqueda.put("nombre", campo_busquedaNombre.getText());
            }
            if (check_apellidos.isSelected()) {
                busqueda.put("apellidos", campo_busquedaApellidos.getText());
            }
            if (check_telefono.isSelected()) {
                busqueda.put("telefono", campo_busquedaTelefono.getText());
            }
            if (check_nacimiento.isSelected()) {
                busqueda.put("anio", String.valueOf(dateChooser_busqueda.getYear()));
            }
        } else if (campo_busquedaSimple.isVisible()) {
            busqueda.put("simple", campo_busquedaSimple.getText());
        }

        vista_padre.buscaUsuarios(busqueda);
    }//GEN-LAST:event_busquedaUsuarios

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_busqueda;
    private javax.swing.JButton boton_limpiar;
    private javax.swing.JButton boton_multiple;
    private javax.swing.JTextField campo_busquedaApellidos;
    private javax.swing.JTextField campo_busquedaNombre;
    private javax.swing.JTextField campo_busquedaSimple;
    private javax.swing.JTextField campo_busquedaTelefono;
    private javax.swing.JTextField campo_dni;
    private javax.swing.JTextField campo_email;
    private javax.swing.JTextField campo_nombre;
    private javax.swing.JTextField campo_priApellido;
    private javax.swing.JTextField campo_segApellido;
    private javax.swing.JTextField campo_telefono;
    private javax.swing.JCheckBox check_apellidos;
    private javax.swing.JCheckBox check_filtros;
    private javax.swing.JCheckBox check_nacimiento;
    private javax.swing.JCheckBox check_telefono;
    private javax.swing.JCheckBox chek_nombre;
    private com.toedter.calendar.JYearChooser dateChooser_busqueda;
    private com.toedter.calendar.JDateChooser dateChooser_nacimiento;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler_busqueda;
    private javax.swing.JLabel label_dni;
    private javax.swing.JLabel label_email;
    private javax.swing.JLabel label_fechNacimiento;
    private javax.swing.JLabel label_nombre;
    private javax.swing.JLabel label_priApellido;
    private javax.swing.JLabel label_segApellido;
    private javax.swing.JLabel label_telefono;
    private javax.swing.JList<String> lista_Usuarios;
    private javax.swing.JPanel panel_busquedaAvanzada;
    private javax.swing.JPanel panel_busquedaSimple;
    private javax.swing.JScrollPane panel_lista;
    // End of variables declaration//GEN-END:variables
}
