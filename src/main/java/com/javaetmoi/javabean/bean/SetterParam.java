package com.javaetmoi.javabean.bean;


import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.squareup.javapoet.TypeSpec;

import java.beans.PropertyDescriptor;

/**
 * Set of parameters used to generate a "setter" (ie. mutator) statement.
 *
 * <p>
 * This structure could also be used to add an object into a collection, an array or a map.
 * </p>
 */
public class SetterParam {

    private final String varName;
    private final PropertyDescriptor propertyDescriptor;
    private final Object value;
    private final String setterName;
    private final Class<? extends Object> valueClass;
    private final JavaBeanMarshaller marshaller;

    public SetterParam(JavaBeanMarshaller marshaller, String varName, PropertyDescriptor propertyDescriptor, Object value, String setterName) {
        this.marshaller = marshaller;
        this.varName = varName;
        this.propertyDescriptor = propertyDescriptor;
        this.value = value;
        this.setterName = setterName;
        this.valueClass = value.getClass();
    }

    /**
     *
     * @return current object instance variable the setter belongs to.
     */
    public String getVarName() {
        return varName;
    }

    /**
     *
     * @return property descriptor (may be null for the root object of the graph).
     */
    public PropertyDescriptor getPropertyDescriptor() {
        return propertyDescriptor;
    }

    /**
     * @return object to pass as a parameter to the setter.
     */
    public Object getValue() {
        return value;
    }

    /**
     * @return name of the mutator (setXXXX, add, put) called by the Java statement.
     */
    public String getSetterName() {
        return setterName;
    }

    public Class<? extends Object> getValueClass() {
        return valueClass;
    }

    /**
     * @return Javapoet's class builder where utility method could be added.
     */
    public TypeSpec.Builder getCurrentClass() {
        return marshaller.getClazz();
    }

    /**
     * @return instance of the current Java marshaller
     */
    public JavaBeanMarshaller getMarshaller() {
        return marshaller;
    }
}
