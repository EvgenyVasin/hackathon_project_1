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

    //точка доступа к серверу для опроса
    @Column(name="url", length = 512)
    private String url;

    //(не)работоспособность
    @ManyToOne
    @JoinColumn(name = "state_id")
    private StateEntity state;

    //регион
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private RegionEntity region;

    //коментарий
    @Column(name="description", length = 512)
    private String description;

    //контакты
    @OneToMany(mappedBy = "smis", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private  Set<ContactEntity> contacts = new HashSet<>();

    @OneToMany(mappedBy = "smis", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private  Set<FileEntity> files = new HashSet<>();

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

    /**
     * @param url the username to set
     */
    public void setUrl(String url) {
        this.url = url;
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

    public void setState(StateEntity state) {
        this.state = state;
    }

    public void setContacts( Set<ContactEntity> contacts) {
        this.contacts = contacts;
    }

    public void setFiles( Set<FileEntity> files) {
        this.files = files;
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

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
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

    public StateEntity getState() {
        return state;
    }


    public  Collection<ContactEntity> getContacts() {
        return contacts;
    }


    public Collection<FileEntity> getFiles() {
        return files;
    }

    //конец блока геттеров


    //текстовое представление объекта для отладки
    @Override
    public String toString() {
        return "SMIS [SmisID = " + getId() + ", region = " + name
                + ", date_registration = " + dateRegistration
                + ", agreement = " + agreement
                + ", URL = " + url
                + ", region = " + region
                + ", description = " + description;
    }
}
