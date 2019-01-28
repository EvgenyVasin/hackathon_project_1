package ru.basisintellect.support_smis.model.entities;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ContactEntity extends CustomEntity {

    @ManyToOne
    @JoinColumn(name = "smis_id")
    SmisEntity smisEntity;
    @Column(name = "name", length = 128)
    String name;
    @Column (name = "position")
    String position;
    @Column(name = "fone_number", length = 128)
    String fonNumber;

    public ContactEntity(SmisEntity smisEntity, String name, String position, String fonNumber) {
        this.smisEntity = smisEntity;
        this.name = name;
        this.position = position;
        this.fonNumber = fonNumber;
    }



    public SmisEntity getSmisEntity() {
        return smisEntity;
    }

    public void setSmisEntity(SmisEntity smisEntity) {
        this.smisEntity = smisEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFonNumber() {
        return fonNumber;
    }

    public void setFonNumber(String fonNumber) {
        this.fonNumber = fonNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
