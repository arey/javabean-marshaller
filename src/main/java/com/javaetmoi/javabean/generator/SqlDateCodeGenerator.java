package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.bean.SetterParam;
import com.javaetmoi.javabean.util.ParseDateMethod;
import com.squareup.javapoet.MethodSpec;

import java.sql.Date;

public class SqlDateCodeGenerator extends DefaultCodeGenerator<Date> {

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        MethodSpec parseMethod = ParseDateMethod.requireParseDateMethod(param.getCurrentClass());
        String strDate = ParseDateMethod.DATE_FORMAT.format(param.getValue());
        method.addStatement("$L.$L(new java.sql.Date($L(\"" + strDate + "\").getTime()))", param.getVarName(), param.getSetterName(), parseMethod.name);
    }
}
