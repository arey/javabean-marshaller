package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;
import org.joda.time.LocalDate;


public class JodaLocalDateCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return LocalDate.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        LocalDate localDate = (LocalDate) param.getValue();
        method.addStatement("$L.$L(new $T($L, $L, $L))", param.getVarName(), param.getSetterName(), LocalDate.class, localDate.getYear(), localDate.getMonthOfYear(), localDate.getDayOfMonth());
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        LocalDate localDate = (LocalDate) item.getVal();
        item.setPattern("new $T("+localDate.getYear()+", "+localDate.getMonthOfYear()+", "+localDate.getDayOfMonth()+")");
        item.setVal(item.getClazz());
    }
}
