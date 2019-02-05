package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;

@Entity
@Table(name="file")
public class SmisFileEntity extends CustomEntity{


    @Column(name = "name", length = 256)
    String name;

    @Column(name = "file_name", length = 256)
    private String fileName;


    @Column(name = "custom_name", length = 1024)
    String customName;

    @ManyToOne
    @JoinColumn(name = "smis_id", nullable = false)
    SmisEntity smis;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String link) {
        this.customName = link;
    }

    public SmisEntity getSmis() {
        return smis;
    }

    public void setSmis(SmisEntity smis) {
        this.smis = smis;
    }

    public SmisFileEntity() {
    }


}