package com.javaetmoi.javabean.domain;

import org.joda.time.*;

import java.util.Map;
import java.util.TreeMap;

public class JodaTime {

    private LocalDate localDate;

    private Map<String, LocalDate> localDateMap = new TreeMap<>();

    private Instant instant;

    private Map<String, Instant> instantMap = new TreeMap<>();

    public LocalDate getLocalDate() {
        return localDate;
    }

    private LocalDateTime localDateTime;

    private Map<String, LocalDateTime> localDateTimeMap = new TreeMap<>();

    private LocalTime localTime;

    private Map<String, LocalTime> localTimeMap = new TreeMap<>();

    private DateTime dateTime;

    private Map<String, DateTime> dateTimeMap = new TreeMap<>();

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Map<String, LocalDate> getLocalDateMap() {
        return localDateMap;
    }

    public void setLocalDateMap(Map<String,LocalDate> localDateMap) {
        this.localDateMap = localDateMap;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public Map<String, Instant> getInstantMap() {
        return instantMap;
    }

    public void setInstantMap(Map<String, Instant> instantMap) {
        this.instantMap = instantMap;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Map<String, LocalDateTime> getLocalDateTimeMap() {
        return localDateTimeMap;
    }

    private Period period;

    private Map<String, Period> periodMap = new TreeMap<>();

    public void setLocalDateTimeMap(Map<String, LocalDateTime> localDateTimeMap) {
        this.localDateTimeMap = localDateTimeMap;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public Map<String, LocalTime> getLocalTimeMap() {
        return localTimeMap;
    }

    public void setLocalTimeMap(Map<String, LocalTime> localTimeMap) {
        this.localTimeMap = localTimeMap;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Map<String, DateTime> getDateTimeMap() {
        return dateTimeMap;
    }

    public void setDateTimeMap(Map<String, DateTime> dateTimeMap) {
        this.dateTimeMap = dateTimeMap;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Map<String, Period> getPeriodMap() {
        return periodMap;
    }

    public void setPeriodMap(Map<String, Period> periodMap) {
        this.periodMap = periodMap;
    }
}
