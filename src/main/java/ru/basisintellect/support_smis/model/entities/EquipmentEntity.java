package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "equipment")
public class EquipmentEntity extends CustomEntity {


    @Column(name = "name", length = 128)
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
