package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

public class ArrayCodeGenerator implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return valueClass.isArray();
    }

    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        Object[] array = (Object[]) param.getValue();
        if (array instanceof Object[][]) {
            handleMultiArray(method, param, (Object[][]) array);
        } else {
            handleSimpleArray(method, param, array);
        }
    }

    private void handleSimpleArray(MethodSpec.Builder method, SetterParam param, Object[] array) {
        if (array.length == 0) {
            return;
        }
        String arrayName = param.getMarshaller().getOrGenerateVariableName(array, param);
        method.addStatement("$T $L = new $T[$L]", array.getClass(), arrayName, array.getClass().getComponentType(), array.length);
        for (int i = 0; i < array.length; i++) {
            Object obj = array[i];
            String objName = param.getMarshaller().getVariableName(obj);
            if (objName != null) {
                method.addStatement("$L[$L] = $L", arrayName, i, objName);
            } else {
                Item item = param.getMarshaller().buildItem(obj);
                method.addStatement("$L[$L] = " + item.getPattern(), arrayName, i, item.getVal());
            }
        }
        method.addStatement("$L.$L($L)", param.getVarName(), param.getSetterName(), arrayName);
    }

    private void handleMultiArray(MethodSpec.Builder method, SetterParam param, Object[][] multiArray) {
        if (multiArray.length == 0) {
            return;
        }
        Object[] firstArray = multiArray[0];
        if (firstArray.length == 0) {
            return;
        }
        String multiArrayName = param.getMarshaller().getOrGenerateVariableName(multiArray, param);
        method.addStatement("$T $L = new $T[$L][$L]", multiArray.getClass(), multiArrayName, firstArray.getClass().getComponentType(), multiArray.length, firstArray.length);
        for (int i = 0; i < multiArray.length; i++) {
            Object[] array = multiArray[i];
            for (int j = 0; j < array.length; j++) {
                Object obj = array[j];
                String objName = param.getMarshaller().getVariableName(obj);
                if (objName != null) {
                    method.addStatement("$L[$L][$L] = $L", multiArrayName, i, j, objName);
                } else {
                    Item item = param.getMarshaller().buildItem(obj);
                    method.addStatement("$L[$L][$L] = " + item.getPattern(), multiArrayName, i, j, item.getVal());
                }
            }
        }
        method.addStatement("$L.$L($L)", param.getVarName(), param.getSetterName(), multiArrayName);
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {

    }
}
