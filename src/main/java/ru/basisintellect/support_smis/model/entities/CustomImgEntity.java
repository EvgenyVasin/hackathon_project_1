package ru.basisintellect.support_smis.model.entities;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Created by safin.v on 22.11.2016.
 */
@MappedSuperclass
public abstract class CustomImgEntity extends CustomEntity{
    @ManyToOne
    @JoinColumn(name = "img_id")
    ImageEntity image;

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }
}
