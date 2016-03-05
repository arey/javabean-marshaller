package com.javaetmoi.javabean;

import com.javaetmoi.javabean.domain.JodaTime;
import org.joda.time.*;
import org.junit.Test;


public class JodaTimeMarshallerTest extends AbstractJavaBeanMarshallerTest {

    @Test
    public void localDate() {
        JodaTime jodaTime = new JodaTime();
        jodaTime.setLocalDate(new LocalDate(200));
        executeTest(jodaTime);
    }

    @Test
    public void mapOfLocalDate() {
        JodaTime jodaTime = new JodaTime();
        jodaTime.getLocalDateMap().put("ten", new LocalDate(10));
        jodaTime.getLocalDateMap().put("eleven", new LocalDate(11));
        executeTest(jodaTime);
    }

    @Test
    public void instant() {
        JodaTime jodaTime = new JodaTime();
        jodaTime.setInstant(Instant.now());
        executeTest(jodaTime);
    }

    @Test
    public void mapOfInstant() {
        JodaTime jodaTime = new JodaTime();
        jodaTime.getInstantMap().put("now", Instant.now());
        jodaTime.getInstantMap().put("epoch", Instant.parse("2016-02-23"));
        executeTest(jodaTime);
    }

    @Test
    public void localDateTime() {
        JodaTime jodaTime = new JodaTime();
        jodaTime.setLocalDateTime(new LocalDateTime(2016, 2, 23, 19, 2, 0, 100));
        executeTest(jodaTime);
    }

    @Test
    public void mapOfLocalDateTime() {
        JodaTime jodaTime = new JodaTime();
        jodaTime.getLocalDateTimeMap().put("one", new LocalDateTime(2016, 2, 9, 16, 15));
        jodaTime.getLocalDateTimeMap().put("two", new LocalDateTime(2016, 2, 21, 18, 10, 5, 2));
        executeTest(jodaTime);
    }

    @Test
    public void localTime() {
        JodaTime jodaTime = new JodaTime();
        jodaTime.setLocalTime(new LocalTime(19, 2, 0, 100));
        executeTest(jodaTime);
    }

    @Test
    public void mapOfLocalTime() {
        JodaTime jodaTime = new JodaTime();
        jodaTime.getLocalTimeMap().put("one", new LocalTime(16, 15));
        jodaTime.getLocalTimeMap().put("two", new LocalTime(18, 10, 5, 2));
        executeTest(jodaTime);
    }

    @Test
    public void dateTime() {
        JodaTime jodaTime = new JodaTime();
        jodaTime.setDateTime(new DateTime(2016, 2, 23, 19, 2, 0, 100, DateTimeZone.UTC));
        executeTest(jodaTime);
    }

    @Test
    public void mapOfDateTime() {
        JodaTime jodaTime = new JodaTime();
        jodaTime.getDateTimeMap().put("one", new DateTime(2016, 2, 9, 16, 15, 0, 0, DateTimeZone.UTC));
        jodaTime.getDateTimeMap().put("two", new DateTime(2016, 2, 21, 18, 10, 5, 2, DateTimeZone.UTC));
        executeTest(jodaTime);
    }

    @Test
    public void period() {
        JodaTime jodaTime = new JodaTime();
        jodaTime.setPeriod(Period.months(4));
        executeTest(jodaTime);
    }

    @Test
    public void mapOPeriod() {
        JodaTime jodaTime = new JodaTime();
        jodaTime.getPeriodMap().put("P2M", Period.months(2));
        jodaTime.getPeriodMap().put("P3W", Period.weeks(3));
        executeTest(jodaTime);
    }
}
