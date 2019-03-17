package ru.mts.test.hackathon_project_1.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "State")
public class StateEntity extends CustomEntity {

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "state_code")
    private String stateCode;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public StateEntity() {
    }

    public StateEntity(String stateName, String stateCode) {
        this.stateName = stateName;
        this.stateCode = stateCode;
    }
}
