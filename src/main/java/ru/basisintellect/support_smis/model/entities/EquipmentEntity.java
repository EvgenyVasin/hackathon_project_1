package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "equipment")
public class EquipmentEntity extends CustomEntity {
    @ManyToOne
    @JoinColumn(name = "smis_id", nullable = false)
    SmisEntity smis;

    @Column(name = "name", length = 128)
    String name;

    public SmisEntity getSmis() {
        return smis;
    }

    public void setSmis(SmisEntity smis) {
        this.smis = smis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EquipmentEntity() {
    }

    public EquipmentEntity(SmisEntity smis, String name) {
        this.smis = smis;
        this.name = name;
    }
}
