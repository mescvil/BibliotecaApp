package extras;

import modelo.Alquiler;

import javax.swing.table.AbstractTableModel;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import static extras.Utilidades.gregorianCalendarToString;

/**
 * @author Escoz
 */
public class ModeloAlquiler extends AbstractTableModel {

    private Object[][] datos_alquileres = {};
    private final String[] nombre_columnas = {"DNI", "Nombre", "ISBN", "Título", "Entrega", "Creación", "Restantes"};
    private final boolean[] isEditable = {false, false, false, false, false, false, false};

    //  --------------------- MÉTODOS HEREDADOS  ---------------------
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

    public void addAlquileres(ArrayList<Alquiler> alquileres) {

        // UNA COLUMNA MAS PARA TRAMPEAR EL ALQUILER
        datos_alquileres = new Object[alquileres.size()][getColumnCount() + 1];

        for (int i = 0; i < alquileres.size(); i++) {
            addRow(alquileres.get(i), i);
        }

        fireTableDataChanged();
    }

    private void addRow(Alquiler alquiler, int fila) {
        GregorianCalendar fecha_devolucion = alquiler.getFecha_limite();
        GregorianCalendar fecha_creacion = alquiler.getFecha_creacion();
        GregorianCalendar fecha_hoy = new GregorianCalendar();
        long dias_entreFechas = ChronoUnit.DAYS.between(fecha_hoy.toInstant(), fecha_devolucion.toInstant());

        // DNI 
        datos_alquileres[fila][0] = alquiler.getUsuario().getDni();
        // NOMBRE COMPLETO
        datos_alquileres[fila][1] = alquiler.getUsuario().getNombreCompleto();
        // ISBN
        datos_alquileres[fila][2] = alquiler.getLibro().getIsbn();
        // TITULO
        datos_alquileres[fila][3] = alquiler.getLibro().getTitulo();
        // FECHA DEVOLUCIÓN
        datos_alquileres[fila][4] = gregorianCalendarToString(fecha_devolucion);
        // FECHA CREACIÓN
        datos_alquileres[fila][5] = gregorianCalendarToString(fecha_creacion);
        // DIFERENCIA ENTRE HOY Y LA DEVOLUCIÓN
        datos_alquileres[fila][6] = dias_entreFechas + " día(s)";
        // ALQUILER TRAMPEADO
        datos_alquileres[fila][nombre_columnas.length] = alquiler;

        fireTableRowsInserted(fila, fila);
    }

    public Alquiler getAlquiler(int fila) {
        return (Alquiler) datos_alquileres[fila][nombre_columnas.length];
    }
}
