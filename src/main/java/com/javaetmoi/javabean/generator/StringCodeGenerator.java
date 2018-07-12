package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

public class StringCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return String.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        String str = (String) param.getValue();
        // Escape double quotes (used in JSON)
        str = str.replaceAll("\"", "\\\\\"");
        method.addStatement("$L.$L(\"$L\")", param.getVarName(), param.getSetterName(), str);
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        item.appendAfterVal("\"");
        item.appendBeforeVal("\"");
    }
}
