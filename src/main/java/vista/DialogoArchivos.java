/*
 */
package vista;

import controlador.Controlador;
import static extras.Colores_Dimensiones.AZUL;
import java.awt.Frame;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Escoz
 */
public class DialogoArchivos extends javax.swing.JDialog {
    
    private Controlador controlador;
    private JFileChooser fileChooser;

    /**
     * Creates new form DialogoArchivos
     */
    public DialogoArchivos(Frame parent, boolean modal, Controlador controlador) {
        super(parent, modal);
        initComponents();
        
        this.controlador = controlador;
        setLocationRelativeTo(parent);
        iniciaDialogo();
    }
    
    private void iniciaDialogo() {
        campo_rutaAnterior.setText(controlador.getRutaAnterior());
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        label_logo.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_azul = new javax.swing.JPanel();
        label_logo = new javax.swing.JLabel();
        panel_datos = new javax.swing.JPanel();
        boton_anterior = new javax.swing.JButton();
        boton_crear = new javax.swing.JButton();
        campo_rutaAnterior = new javax.swing.JTextField();
        boton_cargar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Selección de datos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        panel_azul.setBackground(AZUL);
        panel_azul.setPreferredSize(new java.awt.Dimension(100, 150));

        label_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        label_logo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panel_azul.add(label_logo);

        getContentPane().add(panel_azul);

        panel_datos.setMaximumSize(new java.awt.Dimension(300, 170));
        panel_datos.setMinimumSize(new java.awt.Dimension(300, 170));
        panel_datos.setPreferredSize(new java.awt.Dimension(300, 170));
        panel_datos.setLayout(new java.awt.GridBagLayout());

        boton_anterior.setText("Usar anterior");
        boton_anterior.setMinimumSize(new java.awt.Dimension(0, 27));
        boton_anterior.setPreferredSize(new java.awt.Dimension(30, 30));
        boton_anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_anteriorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 9);
        panel_datos.add(boton_anterior, gridBagConstraints);

        boton_crear.setText("Crear datos");
        boton_crear.setMinimumSize(new java.awt.Dimension(0, 27));
        boton_crear.setPreferredSize(new java.awt.Dimension(30, 30));
        boton_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_crearActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 9, 0, 9);
        panel_datos.add(boton_crear, gridBagConstraints);

        campo_rutaAnterior.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 5, 9);
        panel_datos.add(campo_rutaAnterior, gridBagConstraints);

        boton_cargar.setText("Cargar datos");
        boton_cargar.setMinimumSize(new java.awt.Dimension(0, 27));
        boton_cargar.setPreferredSize(new java.awt.Dimension(30, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 9, 0, 9);
        panel_datos.add(boton_cargar, gridBagConstraints);

        jLabel1.setText("Ruta anterior");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 5, 0);
        panel_datos.add(jLabel1, gridBagConstraints);

        getContentPane().add(panel_datos);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void boton_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_crearActionPerformed
        int opcion = fileChooser.showDialog(this, "Seleccionar");
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            controlador.creaFicherosDatos(file.getAbsolutePath());
            
            this.setVisible(false);
        }
    }//GEN-LAST:event_boton_crearActionPerformed

    private void boton_anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_anteriorActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_boton_anteriorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_anterior;
    private javax.swing.JButton boton_cargar;
    private javax.swing.JButton boton_crear;
    private javax.swing.JTextField campo_rutaAnterior;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel label_logo;
    private javax.swing.JPanel panel_azul;
    private javax.swing.JPanel panel_datos;
    // End of variables declaration//GEN-END:variables
}
