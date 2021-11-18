package co.com.nequi.usecase.util;

public class UtilString {

    private UtilString() {
    }

    public static boolean cadenaVacia(final String cadena) {
        boolean cadenaVacia = false;
        if (cadena == null || cadena.trim().isEmpty()) {
            cadenaVacia = true;
        }
        return cadenaVacia;
    }

}
