package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.util.ParseDateMethod;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

import java.util.Date;

public class DateCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return Date.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        MethodSpec parseMethod = ParseDateMethod.requireParseDateMethod(param.getCurrentClass());
        String strDate = ParseDateMethod.DATE_FORMAT.format(param.getValue());
        method.addStatement("$L.$L($N(\"" + strDate + "\"))", param.getVarName(), param.getSetterName(), parseMethod);
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        MethodSpec method = ParseDateMethod.requireParseDateMethod(marshaller.getClazz());
        item.setVal(method.name + "(\"" + ParseDateMethod.DATE_FORMAT.format(item.getVal()) + "\")");
    }
}
