/**
 * Copyright 2016 the original author or authors.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.javaetmoi.javabean;

import com.javaetmoi.javabean.domain.*;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.GregorianCalendar;

public class CommonMarshallerTest extends AbstractJavaBeanMarshallerTest {

    @Test
    public void enumProperty() {
        Address paris = new Address(null, AddressType.HOME, null, null, null);
        executeTest(paris);
    }

    @Test
    public void stringProperty() {
        Country france = new Country(null, "France");
        executeTest(france);
    }

    @Test
    public void dateProperty() {
        Person james = new Customer();
        james.setBirthday(new Date());
        executeTest(james);
    }

    @Test
    public void objectArrayProperty() {
        Customer james = new Customer();
        Project[] projects = new Project[2];
        projects[0] = new Project(1, null, true);
        projects[1] = new Project(2, null, false);
        james.setProjects(projects);
        executeTest(james);
    }

    @Test
    public void multiDimArrayPoperty() {
        Dummy dummy = new Dummy();
        String[][] array = new String[2][3];
        array[0][0] = "0-0";
        array[0][1] = "0-1";
        array[0][2] = "0-2";
        array[1][0] = "1-0";
        array[1][1] = "1-1";
        array[1][2] = "1-2";
        dummy.setMultiDimArray(array);
        executeTest(dummy);
    }

    @Test
    public void oneToOneUnidirectionalRelationship() {
        Country france = new Country(1000, "France");
        Address paris = new Address(null, null, null, null, france);
        executeTest(paris);
    }

    @Test
    public void oneToOneBidirectionalRelationship() {
        Customer john = new Customer(3L, "John");
        Passport johnPassport = new Passport(897698, john);
        john.setPassport(johnPassport);
        executeTest(john);
    }

    @Test
    public void sqlDateProperty() {
        Dummy dummy = new Dummy();
        dummy.setSqlDate(new java.sql.Date(400));
        executeTest(dummy);
    }

    @Test
    public void calendarProperty() {
        Dummy dummy = new Dummy();
        dummy.setCalendar(GregorianCalendar.getInstance());
        executeTest(dummy);
    }

    @Test
    public void complexeObjectGraph() throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Country france = new Country(1000, "France");

        Employee james = new Employee(1L, "James", "Developer");
        Employee tom = new Employee(2L, "Tom", "Project Manager");

        Customer john = new Customer(3L, "John");
        Passport johnPassport = new Passport(897698, john);
        john.setPassport(johnPassport);

        Customer julia = new Customer(4L, "Julia");
        Passport juliaPassport = new Passport(897001, julia);
        julia.setPassport(juliaPassport);

        Project android = new Project(10, "Android Project", true);
        android.getCustomers().add(julia);
        android.getCustomers().add(john);
        john.setProjects(new Project[]{android});
        Project iphone = new Project(20, "iPhone Project", false);
        iphone.getCustomers().add(julia);
        julia.setProjects(new Project[]{iphone, android});

        Address paris = new Address(100, AddressType.HOME, "Paris", james, france);
        Address lyon = new Address(200, AddressType.WORK, "Lyon", tom, france);
        Address laDefense = new Address(300, AddressType.WORK, "La Defense", james, france);

        james.getProjects().add(android);
        james.getProjects().add(iphone);
        james.getAddresses().put(paris.getType(), paris);
        james.getAddresses().put(laDefense.getType(), laDefense);
        android.getMembers().add(james);
        android.getMembers().add(tom);
        tom.getProjects().add(android);
        tom.getAddresses().put(lyon.getType(), lyon);
        iphone.getMembers().add(james);

        executeTest(james);
    }

}
