package ru.basisintellect.support_smis.entities;

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
    Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
