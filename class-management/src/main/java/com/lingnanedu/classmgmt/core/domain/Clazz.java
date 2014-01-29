package com.lingnanedu.classmgmt.core.domain;

import java.util.Set;
import java.util.UUID;

public class Clazz {

    private final UUID id;
    private Set<Teacher> teachers;
    private Set<Student> students;
    private String name;

    public Clazz(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
