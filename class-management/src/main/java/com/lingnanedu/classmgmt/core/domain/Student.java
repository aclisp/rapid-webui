package com.lingnanedu.classmgmt.core.domain;

import java.util.UUID;

public class Student {

    private final UUID id;
    private String name;

    public Student(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
