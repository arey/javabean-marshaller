package com.javaetmoi.javabean.bean;


import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.squareup.javapoet.TypeSpec;

import java.beans.PropertyDescriptor;

public class SetterParam {

    private final String varName;
    private final PropertyDescriptor propertyDescriptor;
    private final Object value;
    private final String setterName;
    private final Class<? extends Object> valueClass;

    private JavaBeanMarshaller marshaller;

    public SetterParam(JavaBeanMarshaller marshaller, String varName, PropertyDescriptor propertyDescriptor, Object value, String setterName) {
        this.marshaller = marshaller;
        this.varName = varName;
        this.propertyDescriptor = propertyDescriptor;
        this.value = value;
        this.setterName = setterName;
        this.valueClass = value.getClass();
    }

    public String getVarName() {
        return varName;
    }

    public PropertyDescriptor getPropertyDescriptor() {
        return propertyDescriptor;
    }

    public Object getValue() {
        return value;
    }

    public String getSetterName() {
        return setterName;
    }

    public Class<? extends Object> getValueClass() {
        return valueClass;
    }

    public TypeSpec.Builder getCurrentClass() {
        return marshaller.getClazz();
    }

    public JavaBeanMarshaller getMarshaller() {
        return marshaller;
    }
}
