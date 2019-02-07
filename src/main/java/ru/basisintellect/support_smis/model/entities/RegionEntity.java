package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "region")
public class RegionEntity extends CustomEntity {
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    Set<SmisEntity> smises = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    DistrictEntity district;

    @Column(name = "name", length = 256, unique = true)
    String name;

    @Column(name = "code", unique = true)
    int code;



    public RegionEntity(String name) {
        this.name = name;
    }

    public Set<SmisEntity> getSmises() {
        return smises;
    }

    public void setSmises(Set<SmisEntity> smises) {
        this.smises = smises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(DistrictEntity district) {
        this.district = district;
    }

    public RegionEntity() {
    }

    public RegionEntity( int code, String name, DistrictEntity district) {
        this.district = district;
        this.name = name;
        this.code = code;
    }

    public RegionEntity(int code, String name) {
        this.name = name;
        this.code = code;
    }
}
