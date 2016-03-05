package com.javaetmoi.javabean;

import com.javaetmoi.javabean.bean.Item;
import com.javaetmoi.javabean.bean.SetterParam;
import com.javaetmoi.javabean.generator.*;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Modifier;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class JavaBeanMarshaller {

    private Map<Object, String> variables = new HashMap<Object, String>();

    private final TypeSpec.Builder clazz;

    private final MethodSpec.Builder method;

    private Set<Object> processedBeans = new HashSet<Object>();

    private final static Logger LOG = LoggerFactory.getLogger(JavaBeanMarshaller.class);

    private static List<CodeGenerator> codeGenerators = new ArrayList<>();

    static {
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

    public static JavaFile generateJavaFile(Object bean, String className) {
        JavaBeanMarshaller obj2java = new JavaBeanMarshaller(bean, className);
        return obj2java.generate(bean);
    }

    public static JavaFile generateJavaFile(Object bean) {
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

    public static void generateJavaCode(Object bean) {
        generateJavaCode(bean, null);
    }


    /**
     * Add a CodeGenerator just before the default the default one.
     *
     * @param codeGenerator a new CodeGenerator
     */
    public static void addCodeGenerator(CodeGenerator codeGenerator) {
        codeGenerators.add(codeGenerators.size() - 1, codeGenerator);
    }

    private JavaFile generate(Object bean) {
        String varName = generateBeanCode(bean);
        method.addStatement("return " + varName);
        clazz.addMethod(method.build());
        JavaFile javaFile = JavaFile.builder("", clazz.build()).build();
        return javaFile;
    }


    public String generateBeanCode(Object bean) {
        if (processedBeans.contains(bean)) {
            return getVariableName(bean);
        }
        processedBeans.add(bean);
        String varName = getOrGenerateVariableName(bean);
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

    public String getOrGenerateVariableName(Object obj, SetterParam setterParam) {
        String finaleName = getVariableName(obj);
        if (finaleName != null) {
            return finaleName;
        }
        String baseName;
        if ((setterParam == null) || (setterParam.getPropertyDescriptor() == null)) {
            baseName = generateBaseVariableName(obj);
        } else {
            baseName = generateBaseVariableName(setterParam.getPropertyDescriptor());
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

    private String generateBaseVariableName(PropertyDescriptor propertyDescriptor) {
        return propertyDescriptor.getName();
    }

    private String generateBaseVariableName(Object obj) {
        String className;
        if (obj.getClass().isArray()) {
            className = plural(obj.getClass().getComponentType().getSimpleName());
        } else if (Collection.class.isAssignableFrom(obj.getClass())) {
            className = obj.getClass().getSimpleName();
            Collection<?> coll = (Collection<?>) obj;
            if (!coll.isEmpty()) {
                className = getNameFromItem(className, coll);
            }
        } else if (Map.class.isAssignableFrom(obj.getClass())) {
            className = obj.getClass().getSimpleName();
            Map<?, ?> map = (Map<?, ?>) obj;
            if (!map.isEmpty()) {
                className = getNameFromItem(className, map.values());
            }
        } else {
            className = obj.getClass().getSimpleName();
        }
        String baseName = unCapitalize(className);
        baseName = escapeChars(baseName);
        return baseName;
    }

    private String escapeChars(String srcName) {
        String finalName = srcName;
        if (srcName.contains("[]")) {
            finalName = finalName.replaceAll("\\[\\]", "");
        }
        return finalName;
    }


    private String getNameFromItem(String className, Collection<?> coll) {
        Object item = coll.iterator().next();
        if (item != null) {
            Class itemClazz = item.getClass();
            className = plural(itemClazz.getSimpleName());
        }
        return className;
    }

    private String plural(String name) {
        if (name.endsWith("ss")) {
            return name + "es";
        } else if (!name.endsWith("s")) {
            return name + "s";
        }
        return name;
    }

    private String unCapitalize(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            char firstChar = str.charAt(0);
            return Character.isLowerCase(firstChar) ? str : (new StringBuilder(strLen)).append(Character.toLowerCase(firstChar)).append(str.substring(1)).toString();
        } else {
            return str;
        }
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
