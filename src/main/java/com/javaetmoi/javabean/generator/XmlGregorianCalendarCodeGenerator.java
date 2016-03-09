package com.javaetmoi.javabean.generator;

import com.javaetmoi.javabean.bean.SetterParam;
import com.squareup.javapoet.MethodSpec;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class XmlGregorianCalendarCodeGenerator extends DefaultCodeGenerator<XMLGregorianCalendar> {


    @Override
    public void generateSetter(MethodSpec.Builder method, SetterParam param) {
        XMLGregorianCalendar cal = getValue(param);
        int year = cal.getYear();
        int month = cal.getMonth();
        int day = cal.getDay();
        int hour = cal.getHour();
        int minute = cal.getMinute();
        int second = cal.getSecond();
        int millis = cal.getMillisecond();
        int timezone = cal.getTimezone();
        method.addStatement("$L.$L($T.newInstance().newXMLGregorianCalendar($L, $L, $L, $L, $L, $L, $L, $L))", param.getVarName(), param.getSetterName(), DatatypeFactory.class, year, month, day, hour, minute, second, millis, timezone);
        method.addException(DatatypeConfigurationException.class);
    }

}
