package com.javaetmoi.javabean.generator;


import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;

import java.lang.reflect.ParameterizedType;

public abstract class DefaultCodeGenerator<T> implements CodeGenerator {

    @Override
    public boolean supports(Class<?> valueClass) {
        return ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).isAssignableFrom(valueClass);
    }

    @Override
    public void refineItem(JavaBeanMarshaller marshaller, Item item) {
        // nothing for most type
    }

}
