package com.javaetmoi.javabean.util;

import com.squareup.javapoet.MethodSpec;
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
}
