package ru.mts.test.hackathon_project_1.model.entities;

import javax.persistence.*;


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
