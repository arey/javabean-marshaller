package com.javaetmoi.javabean;

import com.javaetmoi.javabean.domain.JavaTime;
import org.junit.Test;

import java.time.*;

public class JavaTimeMarshallerTest extends AbstractJavaBeanMarshallerTest {

    @Test
    public void localDate() {
        JavaTime javaTime = new JavaTime();
        javaTime.setLocalDate(LocalDate.of(2016, 2, 9));
        executeTest(javaTime);
    }

    @Test
    public void listOfLocalDate() {
        JavaTime javaTime = new JavaTime();
        javaTime.getLocalDateList().add(LocalDate.of(2016, 2, 9));
        javaTime.getLocalDateList().add(LocalDate.of(2016, 2, 21));
        executeTest(javaTime);
    }

    @Test
    public void mapOfLocalDate() {
        JavaTime javaTime = new JavaTime();
        javaTime.getLocalDateMap().put("one", LocalDate.of(2016, 2, 9));
        javaTime.getLocalDateMap().put("two", LocalDate.of(2016, 2, 21));
        executeTest(javaTime);
    }

    @Test
    public void instant() {
        JavaTime javaTime = new JavaTime();
        javaTime.setInstant(Instant.now());
        executeTest(javaTime);
    }

    @Test
    public void mapOfInstant() {
        JavaTime javaTime = new JavaTime();
        javaTime.getInstantMap().put("now", Instant.now());
        javaTime.getInstantMap().put("epoch", Instant.EPOCH);
        executeTest(javaTime);
    }

    @Test
    public void localDateTime() {
        JavaTime javaTime = new JavaTime();
        javaTime.setLocalDateTime(LocalDateTime.of(2016, Month.FEBRUARY, 23, 19, 2, 0, 100));
        executeTest(javaTime);
    }

    @Test
    public void mapOfLocalDateTime() {
        JavaTime javaTime = new JavaTime();
        javaTime.getLocalDateTimeMap().put("one", LocalDateTime.of(2016, 2, 9, 16, 15));
        javaTime.getLocalDateTimeMap().put("two", LocalDateTime.of(2016, 2, 21, 18, 10, 5, 2));
        executeTest(javaTime);
    }

    @Test
    public void localTime() {
        JavaTime javaTime = new JavaTime();
        javaTime.setLocalTime(LocalTime.of( 19, 2, 0, 100));
        executeTest(javaTime);
    }

    @Test
    public void mapOfLocalTime() {
        JavaTime javaTime = new JavaTime();
        javaTime.getLocalTimeMap().put("one", LocalTime.of(16, 15, 0));
        javaTime.getLocalTimeMap().put("two", LocalTime.of(18, 10, 5, 2));
        executeTest(javaTime);
    }

    @Test
    public void zonedDateTime() {
        JavaTime javaTime = new JavaTime();
        javaTime.setZonedDateTime(ZonedDateTime.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]"));
        executeTest(javaTime);
    }

    @Test
    public void mapOfZonedDateTime() {
        JavaTime javaTime = new JavaTime();
        javaTime.getZonedDateTimeMap().put("now", ZonedDateTime.now());
        javaTime.getZonedDateTimeMap().put("two", ZonedDateTime.parse("2014-07-23T17:55:51.612-03:00[America/Argentina/Buenos_Aires]"));
        executeTest(javaTime);
    }

    @Test
    public void period() {
        JavaTime javaTime = new JavaTime();
        javaTime.setPeriod(Period.ofMonths(4));
        executeTest(javaTime);
    }

    @Test
    public void mapOPeriod() {
        JavaTime javaTime = new JavaTime();
        javaTime.getPeriodMap().put("P2M", Period.ofMonths(2));
        javaTime.getPeriodMap().put("P29Y6M2D", Period.between(LocalDate.of(2000, Month.JUNE, 30), LocalDate.of(2030, Month.JANUARY, 1) ));
        executeTest(javaTime);
    }
}
