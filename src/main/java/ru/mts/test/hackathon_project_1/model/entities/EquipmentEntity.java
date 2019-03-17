package ru.mts.test.hackathon_project_1.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "equipment")
public class EquipmentEntity extends CustomEntity {


    @Column(name = "name", length = 128, unique = true)
    String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EquipmentEntity() {
    }

    public EquipmentEntity(String name) {
        this.name = name;
    }

}
