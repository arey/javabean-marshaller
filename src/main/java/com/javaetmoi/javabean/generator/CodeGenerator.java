package com.javaetmoi.javabean.generator;


import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

public interface CodeGenerator {

    boolean supports(Class<?> valueClass);

    void generateSetter(MethodSpec.Builder method, SetterParam param);

    void refineItem(JavaBeanMarshaller marshaller, Item item);
}

