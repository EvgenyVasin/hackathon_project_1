package ru.basisintellect.support_smis.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "area")
public class StateAreaEntity extends CustomEntity {

    @Column(name="name")
    String name;

}
