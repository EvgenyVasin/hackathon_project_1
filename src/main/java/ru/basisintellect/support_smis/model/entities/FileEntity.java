package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;

@Entity
@Table(name="file")
public class FileEntity extends CustomEntity{

    @ManyToOne
    @JoinColumn(name = "smis_id", nullable = false)
    SmisEntity smis;

    @Column(name = "name", length = 128)
    String name;

    @Column(name = "link", length = 128)
    String link;


    public SmisEntity getSmisEntity() {
        return smis;
    }

    public void setSmisEntity(SmisEntity smisEntity) {
        this.smis = smisEntity;
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
