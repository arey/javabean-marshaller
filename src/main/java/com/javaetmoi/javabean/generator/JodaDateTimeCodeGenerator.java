package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;
import org.joda.time.DateTime;

public class JodaDateTimeCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return DateTime.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        DateTime dateTime = (DateTime) param.getValue();
        method.addStatement("$L.$L($T.parse(\"$L\"))", param.getVarName(), param.getSetterName(), DateTime.class, dateTime.toString());
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        DateTime dateTime = (DateTime) item.getVal();
        item.setPattern("$T.parse(\""+dateTime.toString()+"\")");
        item.setVal(item.getClazz());
    }
}
