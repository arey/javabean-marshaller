package com.javaetmoi.javabean.util;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

public class JavapoetHelper {

    private JavapoetHelper() {
        // Helper class
    }

    public static MethodSpec findMethod(TypeSpec.Builder typeBuilder, String methodName) {
        // https://github.com/square/javapoet/issues/416
        List<MethodSpec> methodSpecs = typeBuilder.build().methodSpecs;
        for (MethodSpec methodSpec : methodSpecs) {
            if (methodSpec.name.equals(methodName)) {
                return methodSpec;
            }
        }
        return null;
    }

    public static void addExceptionIfNotDeclared(MethodSpec.Builder methodBuilder, Class<?> exception) {
        // See https://github.com/square/javapoet/issues/438
        ClassName ex = ClassName.get(exception);
        if (!methodBuilder.build().exceptions.contains(ex)) methodBuilder.addException(ex);
    }
}


