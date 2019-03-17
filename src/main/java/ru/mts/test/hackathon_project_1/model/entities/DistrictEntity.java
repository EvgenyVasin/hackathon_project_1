package ru.mts.test.hackathon_project_1.model.entities;

import javax.persistence.*;

@Entity
@Table(name="district")
public class DistrictEntity extends CustomEntity{
//    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    Set<RegionEntity> regions = new HashSet<>();

    @Column(name = "name", length = 256, unique = true)
    String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    CountryEntity country;


    public DistrictEntity(){
    }

//    public Set<RegionEntity> getRegions() {
//        return regions;
//    }
//
//    public void setRegions(Set<RegionEntity> regions) {
//        this.regions = regions;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }



    public DistrictEntity(String name, CountryEntity country) {
        this.name = name;
        this.country = country;
    }

}
