package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

import java.time.Instant;

public class Java8InstantCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return Instant.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        Instant instant = (Instant) param.getValue();
        method.addStatement("$L.$L($T.parse(\"$L\"))", param.getVarName(), param.getSetterName(), Instant.class, instant.toString());
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        Instant instant = (Instant) item.getVal();
        item.setPattern("$T.parse(\""+instant.toString()+"\")");
        item.setVal(item.getClazz());
    }
}
