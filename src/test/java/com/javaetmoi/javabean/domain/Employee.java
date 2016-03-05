/**
 * Copyright 2012 the original author or authors.
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
package com.javaetmoi.javabean.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Employee extends Person {

    private Collection<Project> projects = new ArrayList<Project>();

    private String job;

    public Employee() {
    }

    public Employee(Long id, String name, String job) {
        super(id, name);
        this.job = job;
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(projects, employee.projects) &&
                Objects.equals(job, employee.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), projects, job);
    }
}
