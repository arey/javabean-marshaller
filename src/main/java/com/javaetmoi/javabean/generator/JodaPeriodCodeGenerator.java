package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;
import org.joda.time.Period;

public class JodaPeriodCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return Period.class.isAssignableFrom(valueClass);
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        Period period = (Period) param.getValue();
        method.addStatement("$L.$L(new $T(\"$L\"))", param.getVarName(), param.getSetterName(), Period.class, period.toString());
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        Period period = (Period) item.getVal();
        item.setPattern("new $T(\""+period.toString()+"\")");
        item.setVal(item.getClazz());
    }
}
