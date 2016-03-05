package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

import java.time.LocalDateTime;
import java.time.Month;

public class Java8LocalDateTimeCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return LocalDateTime.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        LocalDateTime localDateTime = (LocalDateTime) param.getValue();
        method.addStatement("$L.$L($T.of($L, $T.$L, $L, $L, $L, $L, $L))", param.getVarName(), param.getSetterName(), LocalDateTime.class, localDateTime.getYear(), Month.class, localDateTime.getMonth(), localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond(), localDateTime.getNano());
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        LocalDateTime ldt = (LocalDateTime) item.getVal();
        item.setPattern("$T.of(" + ldt.getYear() + ", " + ldt.getMonthValue() + ", " + ldt.getDayOfMonth() + ", " + ldt.getHour() + ", " + ldt.getMinute() + ", " + ldt.getSecond() + ", " + ldt.getNano() + ")");
        item.setVal(item.getClazz());
    }
}
