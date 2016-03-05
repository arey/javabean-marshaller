package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

import java.time.ZonedDateTime;

public class Java8ZonedDateTimeCodeGenerator extends DefaultCodeGenerator<ZonedDateTime> {



    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        ZonedDateTime time = (ZonedDateTime) param.getValue();
        method.addStatement("$L.$L($T.parse(\"$L\"))", param.getVarName(), param.getSetterName(), ZonedDateTime.class, time.toString());
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        ZonedDateTime time = (ZonedDateTime) item.getVal();
        item.setPattern("$T.parse(\""+time.toString()+"\")");
        item.setVal(item.getClazz());
    }
}
