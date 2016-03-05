package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.javaetmoi.javabean.util.ParseDateMethod;
import com.squareup.javapoet.MethodSpec;

import java.util.Calendar;

public class CalendarCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return Calendar.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        ParseDateMethod.requireParseDateMethod(param.getCurrentClass());
        MethodSpec toCalendarMethod = ParseDateMethod.requireToCalendarMethod(param.getCurrentClass());
        Calendar calendar = (Calendar) param.getValue();
        String strDate = ParseDateMethod.DATE_FORMAT.format(calendar.getTime());
        method.addStatement("$L.$L($L(\"" + strDate + "\"))", param.getVarName(), param.getSetterName(), toCalendarMethod.name);
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        ParseDateMethod.requireParseDateMethod(marshaller.getClazz());
        MethodSpec toCalendarMethod = ParseDateMethod.requireToCalendarMethod(marshaller.getClazz());
        Calendar calendar = (Calendar) item.getVal();
        item.setVal(toCalendarMethod.name + "(\"" + ParseDateMethod.DATE_FORMAT.format(calendar.getTime()) + "\")");
    }
}
