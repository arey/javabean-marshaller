package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

import java.time.LocalDate;
import java.time.Month;

public class Java8LocalDateCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return LocalDate.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        LocalDate localDate = (LocalDate) param.getValue();
        method.addStatement("$L.$L($T.of($L, $T.$L, $L))", param.getVarName(), param.getSetterName(), LocalDate.class, localDate.getYear(), Month.class, localDate.getMonth(), localDate.getDayOfMonth());
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        LocalDate localDate = (LocalDate) item.getVal();
        item.setPattern("$T.of("+localDate.getYear()+", "+localDate.getMonthValue()+", "+localDate.getDayOfMonth()+")");
        item.setVal(item.getClazz());
    }
}
