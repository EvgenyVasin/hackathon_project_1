package ru.mts.test.hackathon_project_1.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="smis_type")
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
