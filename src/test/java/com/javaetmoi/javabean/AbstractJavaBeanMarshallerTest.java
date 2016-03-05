package com.javaetmoi.javabean;

import com.squareup.javapoet.JavaFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.unitils.reflectionassert.ReflectionAssert;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.fail;

public abstract class AbstractJavaBeanMarshallerTest {


    private final static Logger LOG = LoggerFactory.getLogger(CommonMarshallerTest.class);

    private static AtomicInteger count = new AtomicInteger();


    protected void executeTest(Object obj) {
        JavaFile javaFile = JavaBeanMarshaller.generateJavaFile(obj, generateClassName(obj));
        assertNotNull(javaFile);
        LOG.info("Generated file:\n{}", javaFile);
        Class clazz = null;
        try {
            clazz = compile(javaFile);
        } catch (IOException e) {
            fail("Compilation of generated code failure: " + e.getMessage());
        }
        Object result = null;
        try {
            result = execute(clazz, obj);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            fail("Execution of generated code failure: " + e.getMessage());
        }
        assertNotNull(result);
        ReflectionAssert.assertReflectionEquals(obj, result);
    }


    private Class<?> compile(JavaFile javaFile) throws IOException {
        String dir = "target/test-classes";
        javaFile.writeTo(Paths.get(dir));
        String filename = javaFile.toJavaFileObject().getName();
        String fileToCompile = dir + java.io.File.separator + filename;
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compilationResult = compiler.run(null, null, null, fileToCompile);
        if (compilationResult == 0) {
            LOG.info("Compilation is successful for class {}", fileToCompile);
        } else {
            throw new RuntimeException("Compilation Failed");
        }
        Class clazz = null;
        try {
            clazz = Class.forName(filename.replaceAll(".java", ""));
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        }
        return clazz;
    }

    private Object execute(Class clazz, Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "new" + obj.getClass().getSimpleName();
        Method method = clazz.getDeclaredMethod(methodName);
        Object result = method.invoke(null);
        return result;
    }

    private String generateClassName(Object bean) {
        return bean.getClass().getSimpleName() + "Factory" + count.getAndIncrement();
    }

}
