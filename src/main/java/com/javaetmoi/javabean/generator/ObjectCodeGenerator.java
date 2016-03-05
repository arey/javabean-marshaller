package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

public class ObjectCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return Object.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        String newVarname = param.getMarshaller().generateBeanCode(param.getValue());
        method.addStatement("$L.$L($L)", param.getVarName(), param.getSetterName(), newVarname);
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        item.setVal(marshaller.generateBeanCode(item.getVal()));
    }
}
