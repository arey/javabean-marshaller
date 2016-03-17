package com.javaetmoi.javabean.generator;


import com.javaetmoi.javabean.JavaBeanMarshaller;
import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

/**
 * Interface used to customize the generation of a type.
 * <p>
 * Custom {@link CodeGenerator} has to be registered with the {@link JavaBeanMarshaller#register(CodeGenerator)} register method.
 * </p>
 */
public interface CodeGenerator {

    /**
     * Type supported by this generator.
     *
     * @param valueClass type checked
     * @return true if the generator support the given type
     */
    boolean supports(Class<?> valueClass);

    /**
     * Generate a setter statement for a given parameter.
     * <p>
     * This method may also be used to add an object into a collection, a map or an array.
     * </p>
     * @param method Javapoet's method where the statement will be added
     * @param param
     */
    void generateSetter(MethodSpec.Builder method, SetterParam param);

    /**
     * Rework a value wrapped into an item.
     *
     * @param marshaller the current instance of the JavaBean marshaller
     * @param item item to refine
     */
    void refineItem(JavaBeanMarshaller marshaller, Item item);
}

