package ru.mts.test.hackathon_project_1.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "smis_equipment")
public class SmisEquipmentEntity extends CustomEntity{
    @ManyToOne
    @JoinColumn(name = "smis_id", nullable = false)
    SmisEntity smis;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    EquipmentEntity equipment;

    public SmisEntity getSmis() {
        return smis;
    }

    public void setSmis(SmisEntity smis) {
        this.smis = smis;
    }

    public EquipmentEntity getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentEntity equipment) {
        this.equipment = equipment;
    }

    public SmisEquipmentEntity() {
    }

    public SmisEquipmentEntity(SmisEntity smis, EquipmentEntity equipment) {
        this.smis = smis;
        this.equipment = equipment;
    }
}
