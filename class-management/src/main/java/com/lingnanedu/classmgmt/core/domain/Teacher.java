package com.lingnanedu.classmgmt.core.domain;

import java.util.UUID;

public class Teacher {

    private final UUID id;
    private String name;

    public Teacher(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
