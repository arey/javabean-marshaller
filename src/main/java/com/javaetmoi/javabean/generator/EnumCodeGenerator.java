package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

public class EnumCodeGenerator implements CodeGenerator {
    @Override
    public boolean supports(Class<?> valueClass) {
        return valueClass.isEnum();
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        method.addStatement("$L.$L($T.$L)", param.getVarName(), param.getSetterName(), param.getValueClass(), param.getValue());
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        item.setPattern("$T." + item.getVal());
        item.setVal(item.getClazz());
    }
}
