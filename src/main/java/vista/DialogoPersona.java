/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import excepciones.GuardaDatosException;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.JList;
import modelo.Usuario;
import static extras.Colores_Dimensiones.ROJO;
import static extras.Colores_Dimensiones.VERDE;
import static extras.Colores_Dimensiones.DIMENSION_GRANDE;
import static extras.Colores_Dimensiones.DIMENSION_PEQUENIA;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextField;

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
        this.setTitle("Nuevo usuario");

        campo_nombre.setEditable(true);
        campo_segApellido.setEditable(true);
        campo_priApellido.setEditable(true);
        campo_dni.setEditable(true);
        campo_email.setEditable(true);
        campo_telefono.setEditable(true);
        dateChooser_nacimiento.setEnabled(true);
        dateChooser_nacimiento.setDate(new Date());

        boton_multiple.setText("Guardar");
        boton_multiple.setBackground(VERDE);

        lista_Usuarios.setEnabled(false);
        panel_busqueda.setVisible(false);

        setPreferredSize(DIMENSION_PEQUENIA);
        lista_Usuarios.clearSelection();
        rellenaDatosPersona(new Usuario());

        setLocationRelativeTo(vista_padre);
        pack();
        setVisible(true);

    }

    public void muestraModoVer() {
        this.setTitle("Usuarios registrados");

        campo_nombre.setEditable(false);
        campo_segApellido.setEditable(false);
        campo_priApellido.setEditable(false);
        campo_dni.setEditable(false);
        campo_email.setEditable(false);
        campo_telefono.setEditable(false);
        dateChooser_nacimiento.setEnabled(false);

        boton_multiple.setText("Cerrar");
        boton_multiple.setBackground(ROJO);

        lista_Usuarios.setEnabled(true);
        panel_busqueda.setVisible(true);

        boton_limpiar.setVisible(false);
        boton_busqueda.setVisible(true);
        lista_Usuarios.setModel(modelo_lista);
        campo_busqueda.setText("Introduce tu busqueda...");

        setPreferredSize(DIMENSION_GRANDE);
        setLocationRelativeTo(vista_padre);
        panel_lista.requestFocus();
        pack();
        setVisible(true);

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
        panel_busqueda = new javax.swing.JPanel();
        campo_busqueda = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        boton_busqueda = new javax.swing.JButton();
        boton_limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos de usuario");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        label_dni.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_dni.setText("DNI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_dni, gridBagConstraints);

        campo_dni.setEditable(false);
        campo_dni.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_dni, gridBagConstraints);

        label_priApellido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_priApellido.setText("Primer Apellido");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_priApellido, gridBagConstraints);

        label_telefono.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_telefono.setText("Teléfono");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_telefono, gridBagConstraints);

        campo_priApellido.setEditable(false);
        campo_priApellido.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_priApellido, gridBagConstraints);

        campo_telefono.setEditable(false);
        campo_telefono.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_telefono, gridBagConstraints);

        label_email.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_email.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_email, gridBagConstraints);

        label_fechNacimiento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_fechNacimiento.setText("Fecha nacimiento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_fechNacimiento, gridBagConstraints);

        campo_email.setEditable(false);
        campo_email.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
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
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(boton_multiple, gridBagConstraints);

        label_segApellido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_segApellido.setText("Segundo Apellido");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_segApellido, gridBagConstraints);

        campo_segApellido.setEditable(false);
        campo_segApellido.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_segApellido, gridBagConstraints);

        label_nombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_nombre.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_nombre, gridBagConstraints);

        campo_nombre.setEditable(false);
        campo_nombre.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_nombre, gridBagConstraints);

        dateChooser_nacimiento.setForeground(new java.awt.Color(255, 255, 255));
        dateChooser_nacimiento.setDateFormatString("d/MM/y");
        dateChooser_nacimiento.setMaxSelectableDate(new Date());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
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
        lista_Usuarios.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lista_UsuariosValueChanged(evt);
            }
        });
        panel_lista.setViewportView(lista_Usuarios);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(panel_lista, gridBagConstraints);

        panel_busqueda.setLayout(new javax.swing.BoxLayout(panel_busqueda, javax.swing.BoxLayout.LINE_AXIS));

        campo_busqueda.setText("Introduce tu busqueda...");
        campo_busqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campo_busquedaMouseClicked(evt);
            }
        });
        panel_busqueda.add(campo_busqueda);
        panel_busqueda.add(filler1);

        boton_busqueda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar_pequenio.png"))); // NOI18N
        boton_busqueda.setToolTipText("Buscar");
        boton_busqueda.setBorderPainted(false);
        boton_busqueda.setContentAreaFilled(false);
        boton_busqueda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        boton_busqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEncima(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseFuera(evt);
            }
        });
        boton_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_busquedaActionPerformed(evt);
            }
        });
        panel_busqueda.add(boton_busqueda);

        boton_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/limpiar_pequenio.png"))); // NOI18N
        boton_limpiar.setToolTipText("Limpiar");
        boton_limpiar.setBorderPainted(false);
        boton_limpiar.setContentAreaFilled(false);
        boton_limpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mouseEncima(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mouseFuera(evt);
            }
        });
        boton_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_limpiarActionPerformed(evt);
            }
        });
        panel_busqueda.add(boton_limpiar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        getContentPane().add(panel_busqueda, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_multipleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_multipleActionPerformed
        String texto_boton = boton_multiple.getText();

        if (texto_boton.equals("Guardar")) {

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

    private void boton_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_busquedaActionPerformed

        String texto = campo_busqueda.getText();
        ArrayList<Usuario> usuarios_encontrados = vista_padre.buscaUsuarios(texto);

        if (usuarios_encontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sin resultados",
                    "Busqueda de usuarios", JOptionPane.INFORMATION_MESSAGE);
        } else {
            modelo_busqueda.clear();
            modelo_busqueda.addAll(usuarios_encontrados);
            lista_Usuarios.setModel(modelo_busqueda);
            boton_busqueda.setVisible(false);
            boton_limpiar.setVisible(true);
            pack();

        }


    }//GEN-LAST:event_boton_busquedaActionPerformed

    private void mouseEncima(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseEncima
        JButton boton = (JButton) evt.getSource();
        boton.setContentAreaFilled(true);
    }//GEN-LAST:event_mouseEncima

    private void mouseFuera(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseFuera
        JButton boton = (JButton) evt.getSource();
        boton.setContentAreaFilled(false);
    }//GEN-LAST:event_mouseFuera

    private void campo_busquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campo_busquedaMouseClicked
        JTextField campo = (JTextField) evt.getSource();
        campo.setText("");
    }//GEN-LAST:event_campo_busquedaMouseClicked

    private void boton_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_limpiarActionPerformed
        boton_limpiar.setVisible(false);
        boton_busqueda.setVisible(true);
        lista_Usuarios.setModel(modelo_lista);
        campo_busqueda.setText("Introduce tu busqueda...");
        pack();
    }//GEN-LAST:event_boton_limpiarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_busqueda;
    private javax.swing.JButton boton_limpiar;
    private javax.swing.JButton boton_multiple;
    private javax.swing.JTextField campo_busqueda;
    private javax.swing.JTextField campo_dni;
    private javax.swing.JTextField campo_email;
    private javax.swing.JTextField campo_nombre;
    private javax.swing.JTextField campo_priApellido;
    private javax.swing.JTextField campo_segApellido;
    private javax.swing.JTextField campo_telefono;
    private com.toedter.calendar.JDateChooser dateChooser_nacimiento;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel label_dni;
    private javax.swing.JLabel label_email;
    private javax.swing.JLabel label_fechNacimiento;
    private javax.swing.JLabel label_nombre;
    private javax.swing.JLabel label_priApellido;
    private javax.swing.JLabel label_segApellido;
    private javax.swing.JLabel label_telefono;
    private javax.swing.JList<String> lista_Usuarios;
    private javax.swing.JPanel panel_busqueda;
    private javax.swing.JScrollPane panel_lista;
    // End of variables declaration//GEN-END:variables
}
