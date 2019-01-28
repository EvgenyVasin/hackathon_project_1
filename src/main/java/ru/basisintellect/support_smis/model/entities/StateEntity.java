package ru.basisintellect.support_smis.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="State")
public class StateEntity extends CustomEntity{

    @Column(name="state_name")
    private String StateName;

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }
}
