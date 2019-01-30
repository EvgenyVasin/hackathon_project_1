package ru.basisintellect.support_smis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.client.SoapFaultClientException;
import ru.basisintellect.support_smis.controllers.SmisController;
import ru.basisintellect.support_smis.model.SmisStateMsg;
import ru.basisintellect.support_smis.model.entities.*;
import ru.basisintellect.support_smis.repositories.EquipmentRepository;
import ru.basisintellect.support_smis.repositories.RegionRepository;
import ru.basisintellect.support_smis.repositories.SmisRepository;
import ru.basisintellect.support_smis.repositories.StateRepository;
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



    public SmisEntity addSmis(SmisEntity parentSmis, String name, String agreement, Date validity, RegionEntity region, String description, Set<ContactEntity> contacts, Set<SmisFileEntity> files, Set<EquipmentEntity> equipments) {
        SmisEntity smisEntity = new SmisEntity();
        smisEntity.setName(name);
        smisEntity.setDateRegistration(new Date());
        smisEntity.setAgreement(agreement);
        smisEntity.setRegion(region);
        smisEntity.setDescription(description);
        smisEntity.setParentSmis(parentSmis);
        smisEntity.setValidity(validity);
        smisEntity.setContacts(contacts);
        smisEntity.setEquipments(equipments);
        smisEntity.setFiles(files);
//        smisEntity.setValidity(validity);
//        smisEntity.setContacts(contacts);
//        smisEntity.setUrl(url);

        smisesRepo.save(smisEntity);
        return smisEntity;
    }

    public RegionEntity getRegionByNameOrCreate(String regionName){
        RegionEntity regionEntity = regionsRepo.findByName(regionName);
        if(regionEntity == null)
            regionEntity = new RegionEntity(regionName);
        return regionEntity;
    };

    public List<SmisEntity> getAllSmises() {
        return (List<SmisEntity>) smisesRepo.findAll();
    }
    public List<RegionEntity> getAllRegions() {
        return (List<RegionEntity>) regionsRepo.findAll();
    }
    public List<EquipmentEntity> getAllEquipments() {
        return (List<EquipmentEntity>) equipmentRepo.findAll();
    }



    }
