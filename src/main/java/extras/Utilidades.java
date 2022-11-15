package extras;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author theky
 */
public class Utilidades {

    private static final SimpleDateFormat formato_fecha = new SimpleDateFormat("dd MMM yyyy");

    public static String gregorianCalendarToString(GregorianCalendar fecha) {
        formato_fecha.setCalendar(fecha);
        return formato_fecha.format(fecha.getTime());
    }

    public static GregorianCalendar aniadeDiasFecha(GregorianCalendar fecha, int n_dias) {
        GregorianCalendar nueva_fecha = (GregorianCalendar) fecha.clone();
        nueva_fecha.add(GregorianCalendar.DAY_OF_MONTH, n_dias);
        return nueva_fecha;
    }

    public static GregorianCalendar stringToGregorianCalendar(String fecha) throws ParseException {
        Date date = formato_fecha.parse(fecha);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
