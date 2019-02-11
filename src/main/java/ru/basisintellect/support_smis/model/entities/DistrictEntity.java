package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="district")
public class DistrictEntity extends CustomEntity{
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    Set<RegionEntity> regions = new HashSet<>();

    @Column(name = "name", length = 256, unique = true)
    String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    СountryEntity country;


    public DistrictEntity(){
    }

    public Set<RegionEntity> getRegions() {
        return regions;
    }

    public void setRegions(Set<RegionEntity> regions) {
        this.regions = regions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public СountryEntity getCountry() {
        return country;
    }

    public void setCountry(СountryEntity country) {
        this.country = country;
    }



    public DistrictEntity(String name, СountryEntity country) {
        this.name = name;
        this.country = country;
    }

}
