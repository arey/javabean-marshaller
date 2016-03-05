package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

public class NumberCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return Number.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        if (Long.class.isAssignableFrom(param.getValueClass())) {
            method.addStatement("$L.$L($LL)", param.getVarName(), param.getSetterName(), param.getValue());
        } else if (Float.class.isAssignableFrom(param.getValueClass())) {
            method.addStatement("$L.$L($Lf)", param.getVarName(), param.getSetterName(), param.getValue());
        } else {
            method.addStatement("$L.$L($L)", param.getVarName(), param.getSetterName(), param.getValue());
        }
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        if (Long.class.isAssignableFrom(item.getClazz())) {
            item.appendAfterVal("L");
        } else
        if (Float.class.isAssignableFrom(item.getClazz())) {
            item.appendAfterVal("f");
        }

    }
}
