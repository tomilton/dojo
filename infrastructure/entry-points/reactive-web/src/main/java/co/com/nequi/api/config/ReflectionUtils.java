package co.com.nequi.api.config;

import java.lang.reflect.Field;

public class ReflectionUtils {
    public  static void setFinalStaticField(Class<?> clazz, String fieldName, Object value, Object classChange)
            throws ReflectiveOperationException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(classChange, value);
    }
}
