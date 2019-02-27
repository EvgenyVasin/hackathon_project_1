package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vasin.e on 17.01.2019.
 */

@Entity
@Table(name = "smis")
public class SmisEntity extends CustomEntity {

    @ManyToOne
    @JoinColumn(name = "parent_smis_id")
    private SmisEntity parentSmis;

    //название
    @Column(name = "name", length = 128)
    private String name;

    //дата регистрации ПК ИВ в системе
    @Column(name = "date_registration")
    private Date dateRegistration;


    @Column(name = "validity")
    private Date validity;

    //регион
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;



    @Column(name = "address")
    private String address;

    //коментарий
    @Column(name = "description", length = 65536)
    private String description;

    //контакты
    @OneToMany(mappedBy = "smis", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ContactEntity> contacts = new HashSet<>();

    //файлы
    @OneToMany(mappedBy = "smis", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<SmisFileEntity> files = new HashSet<>();

    //файлы
    @OneToMany(mappedBy = "smis", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<SmisEquipmentEntity> equipments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "area_state_id")
    private AreaStateEntity areaState;

    //сеттеры параметров

    public SmisEntity() {
    }

    public SmisEntity(SmisEntity parentSmis, String name, Date dateRegistration, Date validity, String description, Set<ContactEntity> contacts, Set<SmisFileEntity> files, CityEntity city) {
        this.parentSmis = parentSmis;
        this.name = name;
        this.dateRegistration = dateRegistration;
        this.validity = validity;
        this.city = city;
        this.description = description;
        this.contacts = contacts;
        this.files = files;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the region
     */
    public String getName() {
        return name;
    }

    /**
     * @param name сет имя смис
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setAreaState(AreaStateEntity areaState) {
        this.areaState = areaState;
    }

    /**
     * @return the dateRegistration
     */
    public Date getDateRegistration() {
        return dateRegistration;
    }

    /**
     * @param dateRegistration the username to set
     */
    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public SmisEntity getParentSmis() {
        return parentSmis;
    }

    public void setParentSmis(SmisEntity parentSmis) {
        this.parentSmis = parentSmis;
    }

    //конец блока сеттеров

    //геттеры параметров

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ContactEntity> getContacts() {
        return contacts;
    }

    public void setContacts(Set<ContactEntity> contacts) {
        this.contacts = contacts;
    }

    public Set<SmisFileEntity> getFiles() {
        return files;
    }

    public void setFiles(Set<SmisFileEntity> files) {
        this.files = files;
    }

    public Set<SmisEquipmentEntity> getEquipments() {
        return equipments;
    }

    public void setEquipments(Set<SmisEquipmentEntity> equipments) {
        this.equipments = equipments;
    }

    public AreaStateEntity getAreaState() {
        return areaState;
    }

    //конец блока геттеров

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    //текстовое представление объекта для отладки
    @Override
    public String toString() {
        return "SMIS [SmisID = " + getId() + ", name = " + name
                + ", date_registration = " + dateRegistration
                + ", city = " + city
                + ", description = " + description;
    }
}
