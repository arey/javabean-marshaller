package com.javaetmoi.javabean;

import com.javaetmoi.javabean.domain.*;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class CollectionMarshallerTest extends AbstractJavaBeanMarshallerTest {

    @Test
    public void emptyArrayListProperty() {
        Employee james = new Employee(1L, "James", "Developer");
        executeTest(james);
    }

    @Test
    public void objectArrayListProperty() {
        Employee james = new Employee(1L, "James", "Developer");
        Project android = new Project(10, "Android Project", true);
        Project iphone = new Project(20, "iPhone Project", false);
        james.getProjects().add(android);
        james.getProjects().add(iphone);
        executeTest(james);
    }

    @Test
    public void integerTreeSetProperty() {
        Dummy dummy = new Dummy();
        Set<Integer> hashSet = new TreeSet<>();
        hashSet.add(1);
        hashSet.add(2);
        dummy.setIntegerSet(hashSet);
        executeTest(dummy);
    }

    @Test
    public void longQueueProperty() {
        Dummy dummy = new Dummy();
        Collection<Long> queue = new LinkedBlockingDeque<>();
        queue.add(1L);
        queue.add(2L);
        dummy.setLongQueue(queue);
        executeTest(dummy);
    }

    @Test
    public void mapEnumObjectProperty() {
        Employee james = new Employee(1L, "James", null);
        Address paris = new Address(100, AddressType.HOME, "Paris", null, null);
        Address lyon = new Address(200, AddressType.WORK, "Lyon", null, null);
        james.getAddresses().put(paris.getType(), paris);
        james.getAddresses().put(lyon.getType(), lyon);
        executeTest(james);
    }

    @Test
    public void mapStringStringMapProperty() {
        Dummy dummy = new Dummy();
        dummy.getStringStringMap().put("James", "Hunt");
        dummy.getStringStringMap().put("John", "Grant");
        executeTest(dummy);
    }

    @Test
    public void integerLongMapProperty() {
        Dummy dummy = new Dummy();
        dummy.getIntegerLongMap().put(1, 200L);
        dummy.getIntegerLongMap().put(2, 400L);
        executeTest(dummy);
    }

    @Test
    public void dateBooleanMapProperty() {
        Dummy dummy = new Dummy();
        dummy.getDateBooleanMap().put(new Date(200), true);
        dummy.getDateBooleanMap().put(new Date(), false);
        executeTest(dummy);
    }

    @Test
    public void listOfStringListProperty() {
        Dummy dummy = new Dummy();
        dummy.getListOfStringList().add(new ArrayList<>(Arrays.asList("one", "two")));
        dummy.getListOfStringList().add(new ArrayList<>(Arrays.asList("three", "for")));
        executeTest(dummy);
    }

    @Test
    public void calendarMapProperty() {
        Dummy dummy = new Dummy();
        dummy.getCalendarMap().put("now", Calendar.getInstance());
        dummy.getCalendarMap().put("2010-01-01", new GregorianCalendar(2010, 0, 1));
        executeTest(dummy);
    }

}
