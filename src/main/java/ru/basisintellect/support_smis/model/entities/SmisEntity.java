package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vasin.e on 17.01.2019.
 */

@Entity
@Table(name="Smis")
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

    //срок дейсвтия соглашения
    @Column(name="validity", length = 256)
    private String validity;

    //контакты
    @Column(name="contacts", length = 512)
    private String contacts;

    //точка доступа к серверу для опроса
    @Column(name="url", length = 512)
    private String url;

    //(не)работоспособность
    @Column(name="enabled", length = 1)
    private boolean enabled = true;

    //регион
    @Column(name="region", length = 512)
    private String region;

    //коментарий
    @Column(name="description", length = 512)
    private String description;

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
     * @param validity the username to set
     */
    public void setValidity(String validity) {
        this.validity = validity;
    }

    /**
     * @param contacts the username to set
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * @param url the username to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @param enabled the username to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public void setParentSmis(SmisEntity parentSmis) {
        this.parentSmis = parentSmis;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setDescription(String description) {
        this.description = description;
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
     * @return the validity
     */
    public String getValidity() {
        return validity;
    }

    /**
     * @return the contacts
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }


    public SmisEntity getParentSmis() {
        return parentSmis;
    }

    public String getRegion() {
        return region;
    }

    public String getDescription() {
        return description;
    }



    //конец блока геттеров


    //текстовое представление объекта для отладки
    @Override
    public String toString() {
        return "SMIS [SmisID = " + getId() + ", region = " + name
                + ", date_registration = " + dateRegistration
                + ", agreement = " + agreement
                + ", validity = " + validity
                + ", contacts = " + contacts
                + ", URL = " + url
                + ", enabled = " + enabled
                + ", region = " + region
                + ", description = " + description;
    }
}
