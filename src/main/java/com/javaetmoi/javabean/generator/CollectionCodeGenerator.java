package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.util.GenericsHelper;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

public class CollectionCodeGenerator extends DefaultCodeGenerator<Collection> {

    private final static Logger LOG = LoggerFactory.getLogger(CollectionCodeGenerator.class);

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        Collection<?> collection = (Collection<?>) param.getValue();
        if (collection.isEmpty()) {
            return;
        }
        if (param.getPropertyDescriptor() == null) {
            LOG.warn("propertyDescriptor is null for {}", param.getSetterName());
        }
        String collName = param.getMarshaller().getOrGenerateVariableName(collection, param);
        Type parameterizedType = GenericsHelper.findParameterizedType(param.getPropertyDescriptor());
        String constructorCall;
        if (hasInitialCapacityConstructor(param.getValueClass())) {
            constructorCall = "(" + collection.size() + ")";
        } else {
            constructorCall = "()";
        }
        Class<?> varType = (param.getPropertyDescriptor()!=null) ? param.getPropertyDescriptor().getPropertyType() : param.getValueClass();
        if (parameterizedType != null) {
            if (hasParameterType(param.getValueClass())) {
                method.addStatement("$T<$T> $L = new $T<>$L", varType, parameterizedType, collName, param.getValueClass(), constructorCall);
            } else {
                method.addStatement("$T<$T> $L = new $T$L", varType, parameterizedType, collName, param.getValueClass(), constructorCall);
            }

        } else {
            method.addStatement("$T $L = new $T$L", varType, collName, param.getValueClass(), constructorCall);
        }
        for (Object obj : collection) {
            SetterParam newParam = new SetterParam(param.getMarshaller(), collName, null, obj, "add");
            param.getMarshaller().generateSetter(newParam);
        }
        method.addStatement("$L.$L($L)", param.getVarName(), param.getSetterName(), collName);
    }

    private boolean hasParameterType(Class<?> clazz) {
        for (Type type : clazz.getGenericInterfaces()) {
            if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
                return true;
            }
        }
        return false;

    }

    private boolean hasInitialCapacityConstructor(Class<?> clazz) {
        try {
            clazz.getConstructor(String.class);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
