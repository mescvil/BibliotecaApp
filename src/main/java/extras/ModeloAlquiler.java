/*
 */
package extras;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelo.Alquiler;
import static extras.Utilidades.gregorianCalendarToString;
import static extras.Utilidades.aniadeDiasFecha;

/**
 *
 * @author Escoz
 */
public class ModeloAlquiler extends AbstractTableModel {

    private Object[][] datos_alquileres = {};
    private final String[] nombre_columnas = {"DNI", "Nombre", "ISBN", "Título", "Devolución", "Creación"};
    private final boolean[] isEditable = {false, false, false, false, false, false};

    //  --------------------- METODOS HEREDADOS  ---------------------
    @Override
    public int getRowCount() {
        return datos_alquileres.length;

    }

    @Override
    public int getColumnCount() {
        return nombre_columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datos_alquileres[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return nombre_columnas[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return isEditable[columnIndex];
    }

    @Override
    public void setValueAt(Object o, int row, int col) {
        datos_alquileres[row][col] = o;
        fireTableCellUpdated(row, col);
    }

    //  --------------------- METODOS ---------------------
    public void clean() {
        datos_alquileres = new Object[][]{};
        fireTableDataChanged();
    }

    public void addAlquileres(ArrayList<Alquiler> alquileres) {

        // UNA COLUMNA MAS PARA TRAMPEAR EL ALQUILER
        datos_alquileres = new Object[alquileres.size()][getColumnCount() + 1];

        for (int i = 0; i < alquileres.size(); i++) {
            addRow(alquileres.get(i), i);
        }

        fireTableDataChanged();
    }

    private void addRow(Alquiler alquiler, int fila) {
        // DNI 
        datos_alquileres[fila][0] = alquiler.getPersona().getDni();
        // NOMBRE COMPLETO
        datos_alquileres[fila][1] = alquiler.getPersona().getNombreCompleto();
        // ISBN
        datos_alquileres[fila][2] = alquiler.getLibro().getIsbn();
        // TITULO
        datos_alquileres[fila][3] = alquiler.getLibro().getTitulo();
        // FECHA DEVOLUCION
        datos_alquileres[fila][4] = gregorianCalendarToString(alquiler.getFecha_limite());
        // FECHA CREACION
        datos_alquileres[fila][5] = gregorianCalendarToString(aniadeDiasFecha(alquiler.getFecha_limite(), -15));
        // ALQUILER TRAMPEADO
        datos_alquileres[fila][6] = alquiler;

        fireTableRowsInserted(fila, fila);
    }

    public Alquiler getAlquiler(int fila) {
        return (Alquiler) datos_alquileres[fila][6];
    }
}
