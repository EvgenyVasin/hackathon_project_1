package ru.mts.test.hackathon_project_1.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class CityEntity extends CustomEntity {
//
//    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    Set<SmisEntity> smises = new HashSet<>();

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

    public CityEntity() {
    }

    public CityEntity(String name, RegionEntity region) {
        this.region = region;
        this.name = name;
    }
}
