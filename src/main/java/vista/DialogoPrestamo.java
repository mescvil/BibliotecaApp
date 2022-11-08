package vista;

import excepciones.GuardaDatosException;
import excepciones.SinEjemplaresException;
import modelo.Alquiler;
import modelo.Libro;
import modelo.Usuario;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;

import static extras.Colores_Dimensiones.AZUL;
import static extras.Colores_Dimensiones.DIMENSION_GRANDE;

/**
 * @author Escoz
 */
public class DialogoPrestamo extends javax.swing.JDialog {

    private final VistaPrincipal vista_padre;

    private DefaultListModel<Usuario> modelo_lista;
    private Usuario usuario_seleccionado;
    private Libro libro_aPrestar;

    /**
     * Creates new form DialogoPersona
     */
    public DialogoPrestamo(VistaPrincipal parent, boolean modal, DefaultListModel<Usuario> modelo_lista) {
        super(parent, modal);
        this.vista_padre = parent;

        initComponents();
        this.lista_Usuarios.setModel(this.modelo_lista = modelo_lista);

    }


    public void muestraDialogo(Libro libro) {
        this.libro_aPrestar = libro;

        this.setTitle("Nuevo préstamo");

        campo_titulo.setEditable(false);
        campo_isbn.setText(libro.getIsbn());

        campo_isbn.setEditable(false);
        campo_titulo.setText(libro.getTitulo());

        boton_multiple.setText("Confirmar");
        boton_multiple.setBackground(AZUL);

        lista_Usuarios.setEnabled(true);

        int max = libro.getN_ejemplares();
        if (max > 0) {
            spiner_ejemplares.setModel(new SpinnerNumberModel(1, 1, max, 1));
        } else {
            spiner_ejemplares.setModel(new SpinnerNumberModel(0, 0, 0, 0));
        }
        LocalDate fecha_devolucion = LocalDate.now();
        fecha_devolucion = fecha_devolucion.plusDays(15);

        dateChooser_devolucion.setDate(Date.from(fecha_devolucion.atStartOfDay(
                ZoneId.systemDefault()).toInstant()));

        setPreferredSize(DIMENSION_GRANDE);

        setLocationRelativeTo(vista_padre);
        pack();
        setVisible(true);

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
        label_ejemplares = new javax.swing.JLabel();
        boton_multiple = new javax.swing.JButton();
        label_devolucion = new javax.swing.JLabel();
        label_titulo = new javax.swing.JLabel();
        campo_titulo = new javax.swing.JTextField();
        panel_lista = new javax.swing.JScrollPane();
        lista_Usuarios = new JList<Usuario>();
        spiner_ejemplares = new javax.swing.JSpinner();
        dateChooser_devolucion = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_isbn, gridBagConstraints);

        campo_isbn.setEditable(false);
        campo_isbn.setColumns(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_isbn, gridBagConstraints);

        label_ejemplares.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_ejemplares.setText("Ejemplares");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_ejemplares, gridBagConstraints);

        boton_multiple.setForeground(new java.awt.Color(255, 255, 255));
        boton_multiple.setText("Botón");
        boton_multiple.setPreferredSize(new java.awt.Dimension(72, 25));
        boton_multiple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_multipleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(boton_multiple, gridBagConstraints);

        label_devolucion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_devolucion.setText("Fecha devolución");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_devolucion, gridBagConstraints);

        label_titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_titulo.setText("Título");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_titulo, gridBagConstraints);

        campo_titulo.setEditable(false);
        campo_titulo.setColumns(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_titulo, gridBagConstraints);

        panel_lista.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuarios"));
        panel_lista.setPreferredSize(new java.awt.Dimension(100, 146));

        lista_Usuarios.setModel(new AbstractListModel<>() {
            Usuario[] strings = {};

            public int getSize() {
                return strings.length;
            }

            public Usuario getElementAt(int i) {
                return strings[i];
            }
        });
        lista_Usuarios.setPreferredSize(new java.awt.Dimension(100, 90));
        lista_Usuarios.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lista_UsuariosValueChanged(evt);
            }
        });
        panel_lista.setViewportView(lista_Usuarios);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(panel_lista, gridBagConstraints);

        spiner_ejemplares.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(spiner_ejemplares, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(dateChooser_devolucion, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        setLocationRelativeTo(vista_padre);
    }//GEN-LAST:event_formComponentShown

    private void boton_multipleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_multipleActionPerformed
        Date fecha_devolucion = dateChooser_devolucion.getDate();

        if (fecha_devolucion.before(new Date())) {
            JOptionPane.showMessageDialog(this, "Error en la fecha de devolución",
                    "Nuevo préstamo", JOptionPane.ERROR_MESSAGE);
            return;

        }
        if (lista_Usuarios.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario",
                    "Nuevo préstamo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ((Integer) spiner_ejemplares.getValue() == 0) {
            JOptionPane.showMessageDialog(this, "No quedan ejemplares",
                    "Nuevo préstamo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            GregorianCalendar fecha = new GregorianCalendar();
            fecha.setTime(fecha_devolucion);

            Alquiler alquiler = new Alquiler(libro_aPrestar, usuario_seleccionado, fecha,
                    (Integer) spiner_ejemplares.getValue());

            vista_padre.guardaAlquiler(alquiler);
            muestraDialogo(libro_aPrestar);

            int resultado = JOptionPane.showConfirmDialog(this, "Préstamo realizado con éxito, ¿Desea realizar más?",
                    "", JOptionPane.YES_NO_OPTION);

            if (resultado == 1) {
                dispose();
            }

        } catch (SinEjemplaresException ex) {
            JOptionPane.showMessageDialog(this, "Error faltan ejemplares",
                    "Nuevo préstamo", JOptionPane.ERROR_MESSAGE);
        } catch (GuardaDatosException ex) {
            JOptionPane.showMessageDialog(this, "No ha sido posible crear el préstamo",
                    "Nuevo préstamo", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_boton_multipleActionPerformed

    private void lista_UsuariosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lista_UsuariosValueChanged
        JList lista = (JList) evt.getSource();
        if (lista.hasFocus()) {
            if (!lista.getValueIsAdjusting()) {

                usuario_seleccionado = (Usuario) lista.getSelectedValue();

            }
        }
    }//GEN-LAST:event_lista_UsuariosValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_multiple;
    private javax.swing.JTextField campo_isbn;
    private javax.swing.JTextField campo_titulo;
    private com.toedter.calendar.JDateChooser dateChooser_devolucion;
    private javax.swing.JLabel label_devolucion;
    private javax.swing.JLabel label_ejemplares;
    private javax.swing.JLabel label_isbn;
    private javax.swing.JLabel label_titulo;
    private JList<Usuario> lista_Usuarios;
    private javax.swing.JScrollPane panel_lista;
    private javax.swing.JSpinner spiner_ejemplares;
    // End of variables declaration//GEN-END:variables
}
