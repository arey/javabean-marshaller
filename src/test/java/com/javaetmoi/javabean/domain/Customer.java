package com.javaetmoi.javabean.domain;

import java.util.Arrays;
import java.util.Objects;

public class Customer extends Person {

    private Passport passport;

    private Project[] projects;

    public Customer() {
    }

    public Customer(Long id, String name) {
        super(id, name);
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Project[] getProjects() {
        return projects;
    }

    public void setProjects(Project[] projects) {
        this.projects = projects;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(passport, customer.passport) &&
                Arrays.equals(projects, customer.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passport, projects);
    }
}