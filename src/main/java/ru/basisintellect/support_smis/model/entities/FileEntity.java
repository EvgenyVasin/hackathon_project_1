package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;

@Entity
@Table(name="file")
public class FileEntity extends CustomEntity{


    @Column(name = "name", length = 128)
    String name;

    @Column(name = "link", length = 128)
    String link;



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

    public FileEntity() {
    }

    public FileEntity(String name, String link) {
        this.name = name;
        this.link = link;
    }
}