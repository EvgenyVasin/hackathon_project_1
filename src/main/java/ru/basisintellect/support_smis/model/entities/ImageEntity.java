package ru.basisintellect.support_smis.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by safin.v on 22.11.2016.
 */
@Entity
@Table(name = "img_links")
public class ImageEntity extends CustomEntity{

    @Column(name = "img_link", length = 256)
    private String imgLink;

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public ImageEntity() {
    }

    public ImageEntity(String imgLink) {
        this.imgLink = imgLink;
    }


}
