package ru.basisintellect.support_smis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.client.SoapFaultClientException;
import ru.basisintellect.support_smis.controllers.SmisController;
import ru.basisintellect.support_smis.model.SmisStateMsg;
import ru.basisintellect.support_smis.model.entities.*;
import ru.basisintellect.support_smis.repositories.*;
import ru.basisintellect.support_smis.soap_client.TestConnectClient;
import ru.basisintellect.support_smis.soap_client.wsdl.node.TestRequest;
import ru.basisintellect.support_smis.soap_client.wsdl.node.TestResponse;

import java.util.*;

@Service
public class SmisService {


//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    SmisRepository smisesRepo;

    @Autowired
    RegionRepository regionsRepo;

    @Autowired
    EquipmentRepository equipmentRepo;

    @Autowired
    SmisController smisController;

    @Autowired
    SmisEquipmentRepository smisEquipmentRepo;





    public SmisEntity addSmis(SmisEntity parentSmis, String name, String agreement, Date validity, RegionEntity region, String description, Set<ContactEntity> contacts, Set<SmisFileEntity> files, Set<EquipmentEntity> equipments) {
        return smisesRepo.save(new SmisEntity(parentSmis, name, new Date(), agreement, validity, region, description,  contacts,  files));
    }

    public RegionEntity getRegionByNameOrCreate(String regionName){
        RegionEntity regionEntity = regionsRepo.findByName(regionName);
        if(regionEntity == null) {
            regionEntity = new RegionEntity(regionName);
            regionsRepo.save(regionEntity);
        }
        return regionEntity;
    }

    public EquipmentEntity getEquipmentByNameOrCreate(String equipmentName){
        EquipmentEntity equipmentEntity = equipmentRepo.findByName(equipmentName);
        if(equipmentEntity == null) {
            equipmentEntity = new EquipmentEntity(equipmentName);
            equipmentRepo.save(equipmentEntity);
        }
        return equipmentEntity;
    }

    public SmisEquipmentEntity addSmisEquipment(SmisEntity smis, EquipmentEntity equipment){
        return smisEquipmentRepo.save(new SmisEquipmentEntity(smis,equipment));
    }

    public List<SmisEntity> getAllSmises() {
        return (List<SmisEntity>) smisesRepo.findAll();
    }
    public List<RegionEntity> getAllRegions() {
        return (List<RegionEntity>) regionsRepo.findAll();
    }

    public List<EquipmentEntity> getAllEquipments() {
        return (List<EquipmentEntity>) equipmentRepo.findAll();
    }

    public SmisEntity findSmisById(Long id) {
        SmisEntity result = null;
        List<SmisEntity> smises = (List<SmisEntity>) smisesRepo.findAll();
        for (SmisEntity smisEntity : smises) {
            if (id == smisEntity.getId()) {
                result = smisEntity;
                break;
            }
        }
        return result;
    }



    }
