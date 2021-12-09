package io.github.ppetrbednar.tmdb.tools;

import java.math.BigDecimal;

/**
 * Conversion tools.
 *
 * @author Petr Bednář
 */
public class Convertor {

    /**
     * Converts Object to String if convertable.
     *
     * @param obj Input Object
     * @return Converted value or null
     */
    public static String convertString(Object obj) {
        return obj == null ? null : (String) obj;
    }

    /**
     * Converts Object to int if convertable.
     *
     * @param obj Input Object
     * @return Converted value or 0
     */
    public static int convertInt(Object obj) {
        if (obj == null) {
            return 0;
        }

        if (obj.getClass().equals(String.class)) {
            return Integer.parseInt((String) obj);
        }

        if (obj.getClass().equals(Integer.class)) {
            return (int) obj;
        }

        if (obj.getClass().equals(Double.class)) {
            return ((Double) obj).intValue();
        }

        if (obj.getClass().equals(BigDecimal.class)) {
            return ((BigDecimal) obj).intValue();
        }

        return 0;
    }

    /**
     * Converts Object to double if convertable.
     *
     * @param obj Input Object
     * @return Converted value or 0
     */
    public static double convertDouble(Object obj) {
        if (obj == null) {
            return 0;
        }

        if (obj.getClass().equals(String.class)) {
            return Double.parseDouble((String) obj);
        }

        if (obj.getClass().equals(Double.class)) {
            return (double) obj;
        }

        if (obj.getClass().equals(Integer.class)) {
            return ((Integer) obj).doubleValue();
        }

        if (obj.getClass().equals(BigDecimal.class)) {
            return ((BigDecimal) obj).doubleValue();
        }

        return 0;
    }

    /**
     * Converts Object to boolean if convertable.
     *
     * @param obj Input Object
     * @return Converted value or false
     */
    public static boolean convertBoolean(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass().equals(String.class)) {
            return Boolean.parseBoolean((String) obj);
        }

        if (obj.getClass().equals(Boolean.class)) {
            return (Boolean) obj;
        }

        return false;
    }

}
