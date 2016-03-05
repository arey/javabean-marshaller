package com.javaetmoi.javabean.util;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ParseDateMethod {

    public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
    public static final String PARSE_DATE_METHOD_NAME = "parseDate";
    public static final String TO_CALENDAR_METHOD_NAME = "toCalendar";

    public static MethodSpec requireParseDateMethod(TypeSpec.Builder clazz) {
        MethodSpec parseDate = JavapoetHelper.findMethod(clazz, PARSE_DATE_METHOD_NAME);
        if (parseDate == null) {
            FieldSpec dateFormat = FieldSpec.builder(DateFormat.class, "DATE_FORMAT")
                    .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                    .initializer("new $T(\"$L\")", SimpleDateFormat.class, DATE_PATTERN)
                    .build();
            clazz.addField(dateFormat);
            parseDate = MethodSpec.methodBuilder(PARSE_DATE_METHOD_NAME)
                    .addParameter(String.class, "date")
                    .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                    .returns(Date.class)
                    .beginControlFlow("try")
                    .addStatement("return DATE_FORMAT.parse(date)")
                    .nextControlFlow("catch ($T e)", ParseException.class)
                    .addStatement("throw new $T(e)", RuntimeException.class)
                    .endControlFlow()
                    .build();
            clazz.addMethod(parseDate);
        }
        return parseDate;
    }

    public static MethodSpec requireToCalendarMethod(TypeSpec.Builder clazz) {
        MethodSpec toCalendar = JavapoetHelper.findMethod(clazz, TO_CALENDAR_METHOD_NAME);
        if (toCalendar == null) {
            toCalendar = MethodSpec.methodBuilder(TO_CALENDAR_METHOD_NAME)
                    .addParameter(String.class, "date")
                    .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                    .returns(Calendar.class)
                    .addStatement("$T cal = $T.getInstance()", Calendar.class, Calendar.class)
                    .addStatement("cal.setTime($L(date))", PARSE_DATE_METHOD_NAME)
                    .addStatement("return cal")
                    .build();
            clazz.addMethod(toCalendar);
        }
        return toCalendar;
    }
}
