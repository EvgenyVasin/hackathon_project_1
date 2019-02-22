package ru.basisintellect.support_smis.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "areaState")
public class AreaStateEntity extends CustomEntity {

    public AreaStateEntity() {
    }

    public AreaStateEntity(String areaState) {
        this.name = areaState;
    }


    @Column(name = "name", unique = true, nullable = false, length = 128)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public enum AreaStates {
        FEDERAL,
        OKRUG,
        REGION,
        OBLAST;
    }

    public void setAreaState(AreaStates state) {
        switch (state){

            case FEDERAL:
                areaState = "Федеральный";
                break;

            case OKRUG:
                areaState = "Окружной";
                break;

            case REGION:
                areaState = "Региональный";
                break;

            case OBLAST:
                areaState = "Областной";
                break;
        }
    }

    public String getAreaState() {
        return areaState;
    }*/
}
