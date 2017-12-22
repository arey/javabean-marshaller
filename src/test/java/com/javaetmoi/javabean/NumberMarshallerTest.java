package com.javaetmoi.javabean;

import com.javaetmoi.javabean.domain.Country;
import com.javaetmoi.javabean.domain.Customer;
import com.javaetmoi.javabean.domain.Dummy;
import com.javaetmoi.javabean.domain.NumberBean;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberMarshallerTest extends AbstractJavaBeanMarshallerTest {

    @Test
    public void integerProperty() {
        Country france = new Country(100, null);
        executeTest(france);
    }

    @Test
    public void intProperty() {
        Dummy dummy = new Dummy();
        dummy.setIntNumber(10);
        executeTest(dummy);
    }

    @Test
    public void longProperty() {
        Customer john = new Customer(3L, null);
        executeTest(john);
    }

    @Test
    public void floatProperty() {
        NumberBean bean = new NumberBean();
        bean.setFloatPrimitive(2.1f);
        executeTest(bean);
    }

    @Test
    public void doubleProperty() {
        NumberBean bean = new NumberBean();
        bean.setDoublePrimitive(2.5);
        executeTest(bean);
    }

    @Test
    public void bigDecimalProperty() {
        NumberBean bean = new NumberBean();
        bean.setBigDecimal(new BigDecimal("1002.5"));
        executeTest(bean);
    }

    @Test
    public void bigIntegerProperty() {
        NumberBean bean = new NumberBean();
        bean.setBigInteger(new BigInteger("123456789012345678901234567890"));
        executeTest(bean);
    }

    @Test
    public void shortWrapperProperty() {
        NumberBean bean = new NumberBean();
        bean.setShortWrapper((short) 2);
        executeTest(bean);
    }
}
