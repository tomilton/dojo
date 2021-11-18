package co.com.nequi.usecase.createcustomer.util;

public class UtilObject {

    private static final Object NULL = null;

    private UtilObject() {
    }

    public static boolean isNull(final Object object) {
        return object == NULL;
    }

    public static boolean isNotNull(final Object object) {
        return !isNull(object);
    }

}
