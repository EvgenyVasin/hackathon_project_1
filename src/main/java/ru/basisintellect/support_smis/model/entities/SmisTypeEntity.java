package ru.basisintellect.support_smis.model.entities;

import javax.persistence.Column;

public class SmisTypeEntity extends CustomEntity {
    @Column(name = "name", length = 128, unique = true)
    String name;

    public SmisTypeEntity() {
    }

    public SmisTypeEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
