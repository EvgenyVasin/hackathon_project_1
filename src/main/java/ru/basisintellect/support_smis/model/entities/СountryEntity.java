package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="country")
public class СountryEntity extends CustomEntity {
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    Set<DistrictEntity> districts = new HashSet<>();

    @Column(name = "name", length = 256, unique = true)
    String name;

    public СountryEntity(){
    }

    public Set<DistrictEntity> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<DistrictEntity> districts) {
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public СountryEntity(String name) {
        this.name = name;
    }


}
