package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

public class BooleanCodeGenerator extends DefaultCodeGenerator<Boolean> {

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        method.addStatement("$L.$L($L)", param.getVarName(), param.getSetterName(), param.getValue());
    }
}
