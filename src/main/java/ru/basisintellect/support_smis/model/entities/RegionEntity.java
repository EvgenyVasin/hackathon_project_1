package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "region")
public class RegionEntity extends CustomEntity {
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    Set<SmisEntity> smises = new HashSet<>();

    @Column(name = "name", length = 128)
    String name;

    @Column(name = "code")
    Integer code;



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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public RegionEntity() {
    }

    public RegionEntity(Integer code, String name) {
        this.name = name;
        this.code = code;
    }
}
