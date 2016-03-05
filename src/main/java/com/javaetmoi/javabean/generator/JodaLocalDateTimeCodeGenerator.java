package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;
import org.joda.time.LocalDateTime;

public class JodaLocalDateTimeCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return LocalDateTime.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        LocalDateTime ldt = (LocalDateTime) param.getValue();
        method.addStatement("$L.$L(new $T($L, $L, $L, $L, $L, $L, $L))", param.getVarName(), param.getSetterName(), LocalDateTime.class, ldt.getYear(), ldt.getMonthOfYear(), ldt.getDayOfMonth(), ldt.getHourOfDay(), ldt.getMinuteOfHour(), ldt.getSecondOfMinute(), ldt.getMillisOfSecond());
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        LocalDateTime ldt = (LocalDateTime) item.getVal();
        item.setPattern("new $T(" + ldt.getYear() + ", " + ldt.getMonthOfYear() + ", " + ldt.getDayOfMonth() + ", " + ldt.getHourOfDay() + ", " + ldt.getMinuteOfHour() + ", " + ldt.getSecondOfMinute() + ", " + ldt.getMillisOfSecond() + ")");
        item.setVal(item.getClazz());
    }
}
