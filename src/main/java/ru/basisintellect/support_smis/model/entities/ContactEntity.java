package ru.basisintellect.support_smis.model.entities;



import javax.persistence.*;


@Entity
@Table(name="contact")
public class ContactEntity extends CustomEntity {

    @ManyToOne
    @JoinColumn(name = "smis_id", nullable = false)
    SmisEntity smis;

    @Column(name = "name", length = 128)
    String name;
    @Column (name = "position", length = 128)
    String position;
    @Column(name = "pfone_number", length = 128)
    String fonNumber;

    public ContactEntity() {
    }

    public ContactEntity(SmisEntity smisEntity, String name, String position, String fonNumber) {
        this.smis = smisEntity;
        this.name = name;
        this.position = position;
        this.fonNumber = fonNumber;
    }



    public SmisEntity getSmis() {
        return smis;
    }

    public void setSmis(SmisEntity smisEntity) {
        this.smis = smisEntity;
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
