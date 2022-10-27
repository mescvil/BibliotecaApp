/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package extras;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author theky
 */
public class Utilidades {

    private static final SimpleDateFormat formato_fecha = new SimpleDateFormat("dd MMM yyyy");

    public static String gregorianCalendarToString(GregorianCalendar fecha) {
        formato_fecha.setCalendar(fecha);
        String fecha_string = formato_fecha.format(fecha.getTime());
        return fecha_string;
    }

    public static GregorianCalendar aniadeDiasFecha(GregorianCalendar fecha, int n_dias) {
        fecha.add(GregorianCalendar.DAY_OF_MONTH, n_dias);
        return fecha;
    }
}
