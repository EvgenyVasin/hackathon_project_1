package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class CityEntity extends CustomEntity {

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    RegionEntity region;

    @Column(name = "name", length = 256, unique = true)
    String name;

    public RegionEntity getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    public void setName(String name) {
        this.name = name;
    }
}
