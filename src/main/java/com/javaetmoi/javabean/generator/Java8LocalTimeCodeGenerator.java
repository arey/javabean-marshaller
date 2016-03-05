package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

import java.time.LocalTime;

public class Java8LocalTimeCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return LocalTime.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        LocalTime localTime = (LocalTime) param.getValue();
        method.addStatement("$L.$L($T.of($L, $L, $L, $L))", param.getVarName(), param.getSetterName(), LocalTime.class, localTime.getHour(), localTime.getMinute(), localTime.getSecond(), localTime.getNano());
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        LocalTime lt = (LocalTime) item.getVal();
        item.setPattern("$T.of(" + lt.getHour() + ", " + lt.getMinute() + ", " + lt.getSecond() + ", " + lt.getNano() + ")");
        item.setVal(item.getClazz());
    }
}
