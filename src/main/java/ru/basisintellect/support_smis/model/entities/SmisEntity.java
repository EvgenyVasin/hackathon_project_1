package ru.basisintellect.support_smis.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by vasin.e on 17.01.2019.
 */

@Entity
@Table(name="Smis")
public class SmisEntity extends CustomEntity {

    //название
    @Column(name="region", length = 512)
    private String region;

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
    private boolean enabled = false;


    //сеттеры параметров
    /**
     * @param region the username to set
     */
    public void setRegion (String region){
        this.region = region;
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
    //конец блока сеттеров



    //геттеры параметров
    /**
     * @return the region
     */
    public String getRegion() {
        return region;
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
    //конец блока геттеров



    @Override
    public String toString() {
        return "SMIS [SmisID = " + getId() + ", region = " + region
                + ", date_registration = " + dateRegistration
                + ", agreement = " + agreement
                + ", validity = " + validity
                + ", contacts = " + contacts
                + ", URL = " + url.toString()
                + ", enabled = " + enabled;
    }
}
