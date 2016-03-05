package com.javaetmoi.javabean.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericsHelper {

    private GenericsHelper() {
        // Helper class
    }

    public static Type findParameterizedType(PropertyDescriptor propertyDescriptor) {
        Type[] types = findParameterizedTypes(propertyDescriptor);
        if ((types != null) && (types.length > 0)) {
            return types[0];
        }
        return null;
    }

    public static Type[] findParameterizedTypes(PropertyDescriptor propertyDescriptor) {
        if (propertyDescriptor == null){
            return null;
        }
        Method readMethod = propertyDescriptor.getReadMethod();
        Type returnType = readMethod.getGenericReturnType();
        if (returnType instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) returnType;
            Type[] argTypes = paramType.getActualTypeArguments();
            if (argTypes.length > 0) {
                return argTypes;
            }
        }
        return null;
    }
}
