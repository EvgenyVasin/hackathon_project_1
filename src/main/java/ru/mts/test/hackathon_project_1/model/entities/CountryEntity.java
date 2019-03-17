package ru.mts.test.hackathon_project_1.model.entities;

import javax.persistence.*;

@Entity
@Table(name="country")
public class CountryEntity extends CustomEntity {
//    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    Set<DistrictEntity> districts = new HashSet<>();

    @Column(name = "name", length = 128, unique = true)
    private String name;

    public CountryEntity(){
    }

//    public Set<DistrictEntity> getDistricts() {
//        return districts;
//    }
//
//    public void setDistricts(Set<DistrictEntity> districts) {
//        this.districts = districts;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryEntity(String name) {
        this.name = name;
    }


}