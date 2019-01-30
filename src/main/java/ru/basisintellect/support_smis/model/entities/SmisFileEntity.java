package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;

@Entity
@Table(name="file")
public class SmisFileEntity extends CustomEntity{


    @Column(name = "name", length = 128)
    String name;

    @Column(name = "link", length = 128)
    String link;

    @ManyToOne
    @JoinColumn(name = "smis_id", nullable = false)
    SmisEntity smis;



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

    public SmisEntity getSmis() {
        return smis;
    }

    public void setSmis(SmisEntity smis) {
        this.smis = smis;
    }

    public SmisFileEntity() {
    }

    public SmisFileEntity(String name, String link, SmisEntity smis) {
        this.name = name;
        this.link = link;
        this.smis = smis;
    }
}