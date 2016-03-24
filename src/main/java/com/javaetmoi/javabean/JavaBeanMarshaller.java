package com.javaetmoi.javabean;

import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.javaetmoi.javabean.generator.*;
import com.javaetmoi.javabean.util.NamingUtils;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * The JavaBeanMarshaller utility class is responsible for serializing an object (graph) to it's Java code.
 * <p>
 * The objects from the graph have to follow the JavaBean convention.<br/>
 * And by Java code, we talk calling getters and setters of all the object graph.
 * </p>
 * <p>
 * The main purpose of this tools is to help developer to create realistic data sets or stubs for their unit tests.
 * Instead of using XML or JSON serialization, dataset are generated into the Java language.
 * This format is more appropriate for performance and refactoring purpose.
 * </p>
 * <p>
 * Each java class could have it's own Jaca code generator. The marshaller comes with a set of standard generators.
 * They support the main Java types.
 * </p>
 */
public class JavaBeanMarshaller {

    private Map<Object, String> variables = new HashMap<>();

    private final TypeSpec.Builder clazz;

    private final MethodSpec.Builder method;

    private Set<Object> processedBeans = new HashSet<>();

    private final static Logger LOG = LoggerFactory.getLogger(JavaBeanMarshaller.class);

    private static List<CodeGenerator> codeGenerators = new ArrayList<>();

    static {
        // Register standard Java code generators
        codeGenerators.add(new EnumCodeGenerator());
        codeGenerators.add(new StringCodeGenerator());
        codeGenerators.add(new NumberCodeGenerator());
        codeGenerators.add(new SqlDateCodeGenerator());
        codeGenerators.add(new DateCodeGenerator());
        codeGenerators.add(new BooleanCodeGenerator());
        codeGenerators.add(new CalendarCodeGenerator());
        codeGenerators.add(new CollectionCodeGenerator());
        codeGenerators.add(new ArrayCodeGenerator());
        codeGenerators.add(new MapCodeGenerator());
        codeGenerators.add(new XmlGregorianCalendarCodeGenerator());
        if (isJavaTimeSupported()) {
            codeGenerators.add(new Java8InstantCodeGenerator());
            codeGenerators.add(new Java8LocalDateCodeGenerator());
            codeGenerators.add(new Java8LocalDateTimeCodeGenerator());
            codeGenerators.add(new Java8LocalTimeCodeGenerator());
            codeGenerators.add(new Java8PeriodCodeGenerator());
            codeGenerators.add(new Java8ZonedDateTimeCodeGenerator());
        }
        if (isJodaTimeSupported()) {
            codeGenerators.add(new JodaDateTimeCodeGenerator());
            codeGenerators.add(new JodaInstantCodeGenerator());
            codeGenerators.add(new JodaLocalDateCodeGenerator());
            codeGenerators.add(new JodaLocalDateTimeCodeGenerator());
            codeGenerators.add(new JodaLocalTimeCodeGenerator());
            codeGenerators.add(new JodaPeriodCodeGenerator());
        }

        codeGenerators.add(new ObjectCodeGenerator());
    }

    private static boolean isJavaTimeSupported() {
        try {
            Class.forName("java.time.LocalDate", false, JavaBeanMarshaller.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            LOG.info("The Java 8 Date and Time API has not been detected");
            return false;
        }
        return true;
    }

    private static boolean isJodaTimeSupported() {
        try {
            Class.forName("org.joda.time.LocalDate", false, JavaBeanMarshaller.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            LOG.info("The Joda Time librairie has not been detected");
            return false;
        }
        return true;
    }

    private JavaBeanMarshaller(Object bean, String className) {
        if (className == null) {
            className = bean.getClass().getSimpleName() + "Factory";
        }
        clazz = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC);
        method = MethodSpec.methodBuilder("new" + bean.getClass().getSimpleName())
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(bean.getClass());
    }

    static JavaFile generateJavaFile(Object bean, String className) {
        JavaBeanMarshaller obj2java = new JavaBeanMarshaller(bean, className);
        return obj2java.generate(bean);
    }

    static JavaFile generateJavaFile(Object bean) {
        return generateJavaFile(bean, null);
    }

    public static void generateJavaCode(Object bean, String directory) {
        JavaFile javaFile = generateJavaFile(bean, null);
        try {
            Path path = (directory != null) ? Paths.get(directory) : Paths.get(".");
            javaFile.writeTo(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate a Java class in charge to reinstantiate the given bean.
     * <p>
     * The Java file name is created in the current working directory.
     * It's name is generated by the following naming convention: beans's class name appended with the Factory keyword (ie. FooFactory).
     * </p>
     * @param bean objects graph which have to follow the JavaBean convention (getter/setter and no-arg constructor).
     */
    public static void generateJavaCode(Object bean) {
        generateJavaCode(bean, null);
    }


    /**
     * Add a custom CodeGenerator at the end of the chain, just before the default one.
     *
     * <p>
     * The default CodeGenerator implementation is the {@link ObjectCodeGenerator}.
     * It is available at the end of the CodeGenerator chain.
     * </p>
     *
     * @param codeGenerator a new CodeGenerator
     */
    public static void register(CodeGenerator codeGenerator) {
        codeGenerators.add(codeGenerators.size() - 1, codeGenerator);
    }

    /**
     * Remove a registeed generator.
     *
     * @param codeGenerator generator to remove
     */
    public static void unregister(CodeGenerator codeGenerator) {
        codeGenerators.remove(codeGenerator);
    }

    private JavaFile generate(Object bean) {
        String varName = generateBeanCode(bean);
        method.addStatement("return " + varName);
        clazz.addMethod(method.build());
        return JavaFile.builder("", clazz.build()).build();
    }


    public String generateBeanCode(Object bean) {
        if (processedBeans.contains(bean)) {
            return getVariableName(bean);
        }
        processedBeans.add(bean);
        String varName = generateVariableName(bean);
        method.addStatement("$T " + varName + " = new $T()", bean.getClass(), bean.getClass());
        PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(bean.getClass());
        for (PropertyDescriptor propertyDescriptor : properties) {
            try {
                generatePropertySetter(bean, varName, propertyDescriptor);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return varName;
    }

    private <T> void generatePropertySetter(T obj, String varName,
                                            PropertyDescriptor propertyDescriptor)
            throws IllegalAccessException, InvocationTargetException {
        if (ignoreProperty(propertyDescriptor)) {
            return;
        }
        if (propertyDescriptor.getWriteMethod() == null) {
            LOG.warn("No setter for the {}::{} property", obj.getClass().getSimpleName(), propertyDescriptor.getDisplayName());
            return;
        }
        if (propertyDescriptor.getReadMethod() == null) {
            LOG.warn("No getter for the {}::{} property", obj.getClass().getSimpleName(), propertyDescriptor.getDisplayName());
            return;
        }
        Object val = propertyDescriptor.getReadMethod().invoke(obj);
        if (val == null) {
            return;
        }
        String setterName = propertyDescriptor.getWriteMethod().getName();
        SetterParam param = new SetterParam(this, varName, propertyDescriptor, val, setterName);
        generateSetter(param);
    }

    public void generateSetter(SetterParam param) {
        for (CodeGenerator codeGenerator : codeGenerators) {
            if (codeGenerator.supports(param.getValueClass())) {
                codeGenerator.generateSetter(method, param);
                return;
            }
        }
    }

    private boolean ignoreProperty(PropertyDescriptor propertyDescriptor) {
        return propertyDescriptor.getName().equals("class");
    }

    public String getVariableName(Object obj) {
        if (variables.containsKey(obj)) {
            return variables.get(obj);
        }
        return null;
    }

    public String getOrGenerateVariableName(Object obj) {
        return getOrGenerateVariableName(obj, null);
    }

    public String generateVariableName(Object obj) {
        return generateVariableName(obj, null);
    }

    public String getOrGenerateVariableName(Object obj, SetterParam setterParam) {
        String finaleName = getVariableName(obj);
        if (finaleName != null) {
            return finaleName;
        }
        return generateVariableName(obj, setterParam);
    }

    public String generateVariableName(Object obj, SetterParam setterParam) {
        String finaleName = null;
        String baseName;
        if ((setterParam == null) || (setterParam.getPropertyDescriptor() == null)) {
            baseName = NamingUtils.generateBaseVariableName(obj);
        } else {
            baseName = NamingUtils.generateBaseVariableName(setterParam.getPropertyDescriptor());
        }
        int num = 1;
        Collection<String> variableNames = variables.values();
        while (finaleName == null) {
            finaleName = baseName + num;
            if (variableNames.contains(finaleName)) {
                finaleName = null;
                num++;
            }
        }
        variables.put(obj, finaleName);
        return finaleName;
    }

    public Item buildItem(Object val) {
        Item item = new Item(val);
        for (CodeGenerator codeGenerator : getCodeGenerators()) {
            if (codeGenerator.supports(item.getClazz())) {
                codeGenerator.refineItem(this, item);
                return item;
            }
        }
        return item;
    }

    public TypeSpec.Builder getClazz() {
        return clazz;
    }

    public static List<CodeGenerator> getCodeGenerators() {
        return Collections.unmodifiableList(codeGenerators);
    }
}
