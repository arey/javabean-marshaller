package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;
import org.joda.time.LocalTime;

public class JodaLocalTimeCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return LocalTime.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        LocalTime ld = (LocalTime) param.getValue();
        method.addStatement("$L.$L(new $T($L, $L, $L, $L))", param.getVarName(), param.getSetterName(), LocalTime.class, ld.getHourOfDay(), ld.getMinuteOfHour(), ld.getSecondOfMinute(), ld.getMillisOfSecond());
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        LocalTime ld = (LocalTime) item.getVal();
        item.setPattern("new $T(" + ld.getHourOfDay() + ", " + ld.getMinuteOfHour() + ", " + ld.getSecondOfMinute() + ", " + ld.getMillisOfSecond() + ")");
        item.setVal(item.getClazz());
    }
}
