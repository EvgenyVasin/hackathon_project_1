package ru.basisintellect.support_smis.entities;

import javax.persistence.*;

/**
 * Created by safin.v on 17.11.2016.
 */
@MappedSuperclass
public abstract class CustomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
