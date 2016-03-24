package com.javaetmoi.javabean.util;


import java.beans.PropertyDescriptor;
import java.util.*;

public class NamingUtils {

    private static final Set<String> COLLECTION_SUFFIXES = new HashSet<>(Arrays.asList( "list", "map", "set", "queue"));

    public static String generateBaseVariableName(PropertyDescriptor propertyDescriptor) {
        String name = propertyDescriptor.getName();
        if (isCollection(propertyDescriptor)) {
            boolean plural = false;
            for (String suffix : COLLECTION_SUFFIXES) {
                if (name.toLowerCase(Locale.ENGLISH).endsWith(suffix)) {
                    plural = true;
                    break;
                }
            }
            if (!plural) {
                name = pluralize(name);
            }
        }
        return name;
    }

    public static String generateBaseVariableName(Object obj) {
        String className;
        if (obj.getClass().isArray()) {
            className = pluralize(obj.getClass().getComponentType().getSimpleName());
        } else if (Collection.class.isAssignableFrom(obj.getClass())) {
            className = obj.getClass().getSimpleName();
            Collection<?> coll = (Collection<?>) obj;
            if (!coll.isEmpty()) {
                className = NamingUtils.getNameFromItem(className, coll);
            }
        } else if (Map.class.isAssignableFrom(obj.getClass())) {
            className = obj.getClass().getSimpleName();
            Map<?, ?> map = (Map<?, ?>) obj;
            if (!map.isEmpty()) {
                className = NamingUtils.getNameFromItem(className, map.values());
            }
        } else {
            className = obj.getClass().getSimpleName();
        }
        String baseName = NamingUtils.unCapitalize(className);
        baseName = NamingUtils.escapeChars(baseName);
        return baseName;
    }

    private static String getNameFromItem(String className, Collection<?> coll) {
        Object item = coll.iterator().next();
        if (item != null) {
            Class itemClazz = item.getClass();
            className = pluralize(itemClazz.getSimpleName());
        }
        return className;
    }

    private static String pluralize(String name) {
        if (name.endsWith("ss")) {
            return name + "es";
        } else if (!name.endsWith("s")) {
            return name + "s";
        }
        return name;
    }

    private static String unCapitalize(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            char firstChar = str.charAt(0);
            return Character.isLowerCase(firstChar) ? str : (new StringBuilder(strLen)).append(Character.toLowerCase(firstChar)).append(str.substring(1)).toString();
        } else {
            return str;
        }
    }

    private static boolean isCollection(PropertyDescriptor propertyDescriptor) {
        Class<?> propertyType = propertyDescriptor.getPropertyType();
        return Map.class.isAssignableFrom(propertyType)
                || Collection.class.isAssignableFrom(propertyType)
                || propertyType.isArray();
    }

    private static String escapeChars(String srcName) {
        String finalName = srcName;
        if (srcName.contains("[]")) {
            finalName = finalName.replaceAll("\\[\\]", "");
        }
        return finalName;
    }
}
