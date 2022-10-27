/*
 */
package extras;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Escoz
 */
public class ModeloAlquiler extends AbstractTableModel {

    private Object[] datos;
    private String[] nombre_columnas;

    public ModeloAlquiler() {
        datos = new Object[]{};
        nombre_columnas = new String[]{"DNI", "Nombre", "ISBN", "Título", "Devolución", "Creación"};
    }

    @Override
    public int getRowCount() {
        return datos.length;
    }

    @Override
    public int getColumnCount() {
        return nombre_columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datos[rowIndex][columnIndex];
    }

    public void setRowCount(int i) {

    }

    public void addRow(Object[] object) {

    }

}
