package ru.basisintellect.support_smis.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FileEntity extends CustomEntity{

    @ManyToOne
    @JoinColumn(name = "smis_id")
    SmisEntity smisEntity;

    @Column(name = "name", length = 128)
    String name;

    @Column(name = "link", length = 128)
    String link;


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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
