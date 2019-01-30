package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;
import java.util.*;

/**
 * Created by vasin.e on 17.01.2019.
 */

@Entity
@Table(name="smis")
public class SmisEntity extends CustomEntity {

    @ManyToOne
    @JoinColumn(name = "parent_smis_id")
    private SmisEntity parentSmis;

    //название
    @Column(name="name", length = 512)
    private String name;

    //дата регистрации ПК ИВ в системе
    @Column(name = "date_registration")
    private Date dateRegistration;

    //номер соглашения
    @Column(name="agreement", length = 512)
    private String agreement;

    @Column(name="validity", length = 512)
    private Date validity;

    //регион
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private RegionEntity region;

    //коментарий
    @Column(name="description", length = 512)
    private String description;

    //контакты
    @OneToMany(mappedBy = "smis", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private  Set<ContactEntity> contacts;

    //файлы
    @OneToMany(mappedBy = "smis", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private  Set<SmisFileEntity> files;


        //сеттеры параметров
    /**
     * @param name the username to set
     */
    public void setName (String name){
        this.name = name;
    }

    /**
     * @param dateRegistration the username to set
     */
    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    /**
     * @param agreement the username to set
     */
    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }



    public void setParentSmis(SmisEntity parentSmis) {
        this.parentSmis = parentSmis;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setContacts( Set<ContactEntity> contacts) {
        this.contacts = contacts;
    }

    public void setFiles( Set<SmisFileEntity> files) {
        this.files = files;
    }


    public void setValidity(Date validity) {
        this.validity = validity;
    }

    //конец блока сеттеров



    //геттеры параметров
    /**
     * @return the region
     */
    public String getName() {
        return name;
    }

    /**
     * @return the dateRegistration
     */
    public Date getDateRegistration() {
        return dateRegistration;
    }

    /**
     * @return the agreement
     */
    public String getAgreement() {
        return agreement;
    }




    public SmisEntity getParentSmis() {
        return parentSmis;
    }


    public RegionEntity getRegion() {
        return region;
    }

    public String getDescription() {
        return description;
    }



    public  Collection<ContactEntity> getContacts() {
        return contacts;
    }


    public Collection<SmisFileEntity> getFiles() {
        return files;
    }


    public Date getValidity() {
        return validity;
    }

    //конец блока геттеров


    public SmisEntity() {
    }

    public SmisEntity(SmisEntity parentSmis, String name, Date dateRegistration, String agreement, Date validity, RegionEntity region, String description, Set<ContactEntity> contacts, Set<SmisFileEntity> files) {
        this.parentSmis = parentSmis;
        this.name = name;
        this.dateRegistration = dateRegistration;
        this.agreement = agreement;
        this.validity = validity;
        this.region = region;
        this.description = description;
        this.contacts = contacts;
        this.files = files;
    }

    //текстовое представление объекта для отладки
    @Override
    public String toString() {
        return "SMIS [SmisID = " + getId() + ", region = " + name
                + ", date_registration = " + dateRegistration
                + ", agreement = " + agreement
                + ", region = " + region
                + ", description = " + description;
    }
}
