package com.javaetmoi.javabean.domain;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JavaTime {

    private LocalDate localDate;

    private List<LocalDate> localDateList = new ArrayList<>();

    private Map<String, LocalDate> localDateMap = new TreeMap<>();

    private Instant instant;

    private Map<String, Instant> instantMap = new TreeMap<>();

    private LocalDateTime localDateTime;

    private Map<String, LocalDateTime> localDateTimeMap = new TreeMap<>();

    private LocalTime localTime;

    private Map<String, LocalTime> localTimeMap = new TreeMap<>();

    private ZonedDateTime zonedDateTime;

    private Map<String, ZonedDateTime> zonedDateTimeMap = new TreeMap<>();

    private Period period;

    private Map<String, Period> periodMap = new TreeMap<>();

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<LocalDate> getLocalDateList() {
        return localDateList;
    }

    public void setLocalDateList(List<LocalDate> localDateList) {
        this.localDateList = localDateList;
    }

    public Map<String, LocalDate> getLocalDateMap() {
        return localDateMap;
    }

    public void setLocalDateMap(Map<String, LocalDate> localDateMap) {
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

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public Map<String, ZonedDateTime> getZonedDateTimeMap() {
        return zonedDateTimeMap;
    }

    public void setZonedDateTimeMap(Map<String, ZonedDateTime> zonedDateTimeMap) {
        this.zonedDateTimeMap = zonedDateTimeMap;
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
