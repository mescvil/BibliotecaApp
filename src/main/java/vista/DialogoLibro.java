/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import excepciones.GuardaDatosException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.util.Calendar;
import javax.swing.JList;
import modelo.Libro;
import static extras.Colores_Dimensiones.ROJO;
import static extras.Colores_Dimensiones.VERDE;
import static extras.Colores_Dimensiones.DIMENSION_GRANDE;

/**
 *
 * @author Escoz
 */
public class DialogoLibro extends javax.swing.JDialog {

    private VistaPrincipal vista_padre;

    private DefaultListModel modelo_lista;

    /**
     * Creates new form DialogoPersona
     */
    public DialogoLibro(VistaPrincipal parent, boolean modal, DefaultListModel modelo_lista) {
        super(parent, modal);
        this.vista_padre = parent;

        initComponents();

        this.lista_libros.setModel(this.modelo_lista = modelo_lista);

    }

    public void muestraModoAniadir() {
        this.setTitle("Nuevo libro");

        campo_titulo.setEditable(true);
        campo_autor.setEditable(true);
        campo_isbn.setEditable(true);
        yearChooser_anio.setEnabled(true);
        spiner_ejemplares.setEnabled(true);

        boton_multiple.setText("Guardar");
        boton_multiple.setBackground(VERDE);

        lista_libros.setEnabled(false);

        setPreferredSize(DIMENSION_GRANDE);
        setLocationRelativeTo(vista_padre);
        pack();
        setVisible(true);

    }

    public void muestraModoVer() {
        this.setTitle("Usuarios registrados");

        campo_titulo.setEditable(false);
        campo_autor.setEditable(false);
        campo_isbn.setEditable(false);

        boton_multiple.setText("Cerrar");
        boton_multiple.setBackground(ROJO);

        lista_libros.setEnabled(true);

        setPreferredSize(DIMENSION_GRANDE);
        setLocationRelativeTo(vista_padre);
        pack();
        setVisible(true);

    }

    public void rellenaDatosLibros(Libro libro) {
        campo_titulo.setText(libro.getTitulo());
        campo_autor.setText(libro.getAutor());
        campo_isbn.setText(libro.getIsbn());
        yearChooser_anio.setValue(Integer.parseInt(libro.getAnio_publicacion()));
        spiner_ejemplares.setValue((libro.getN_ejemplares() == 0) ? 1 : libro.getN_ejemplares());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_isbn, gridBagConstraints);

        label_autor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_autor.setText("Autor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_autor, gridBagConstraints);

        label_ejemplares.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_ejemplares.setText("Ejemplares");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_ejemplares, gridBagConstraints);

        campo_autor.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
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
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 10);
        getContentPane().add(boton_multiple, gridBagConstraints);

        label_anio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_anio.setText("Año");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_anio, gridBagConstraints);

        label_titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_titulo.setText("Titulo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 9);
        getContentPane().add(label_titulo, gridBagConstraints);

        campo_titulo.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(campo_titulo, gridBagConstraints);

        panel_lista.setBorder(javax.swing.BorderFactory.createTitledBorder("Libros"));
        panel_lista.setPreferredSize(new java.awt.Dimension(100, 146));

        lista_libros.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lista_libros.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lista_librosValueChanged(evt);
            }
        });
        panel_lista.setViewportView(lista_libros);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(panel_lista, gridBagConstraints);

        yearChooser_anio.setMaximum(Calendar.getInstance().get(Calendar.YEAR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(yearChooser_anio, gridBagConstraints);

        spiner_ejemplares.setModel(new javax.swing.SpinnerNumberModel(1, 1, 999, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 10);
        getContentPane().add(spiner_ejemplares, gridBagConstraints);

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

                modelo_lista.addElement(libro);
                rellenaDatosLibros(new Libro());

                int resultado = JOptionPane.showConfirmDialog(this, "Agregado con éxito, ¿Desea agregar más?",
                        "", JOptionPane.YES_NO_OPTION);

                if (resultado == 1) {
                    dispose();
                }
            } catch (GuardaDatosException ex) {
                JOptionPane.showMessageDialog(this, "No ha sido posible crear el libro",
                        "Nuevo libro", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            dispose();
        }
    }//GEN-LAST:event_boton_multipleActionPerformed

    private void lista_librosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lista_librosValueChanged
        JList lista = (JList) evt.getSource();
        if (lista.hasFocus()) {
            if (!lista.getValueIsAdjusting()) {

            }
        }
    }//GEN-LAST:event_lista_librosValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_multiple;
    private javax.swing.JTextField campo_autor;
    private javax.swing.JTextField campo_isbn;
    private javax.swing.JTextField campo_titulo;
    private javax.swing.JLabel label_anio;
    private javax.swing.JLabel label_autor;
    private javax.swing.JLabel label_ejemplares;
    private javax.swing.JLabel label_isbn;
    private javax.swing.JLabel label_titulo;
    private javax.swing.JList<String> lista_libros;
    private javax.swing.JScrollPane panel_lista;
    private javax.swing.JSpinner spiner_ejemplares;
    private com.toedter.calendar.JYearChooser yearChooser_anio;
    // End of variables declaration//GEN-END:variables
}
