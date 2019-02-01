package ru.basisintellect.support_smis.services;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ws.soap.client.SoapFaultClientException;
import ru.basisintellect.support_smis.controllers.SmisController;
import ru.basisintellect.support_smis.model.SmisStateMsg;
import ru.basisintellect.support_smis.model.entities.*;
import ru.basisintellect.support_smis.repositories.*;
import ru.basisintellect.support_smis.soap_client.TestConnectClient;
import ru.basisintellect.support_smis.soap_client.wsdl.node.TestRequest;
import ru.basisintellect.support_smis.soap_client.wsdl.node.TestResponse;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    @Autowired
    SmisFileRepository smisFileRepo;

    @Autowired
    ContactsRepository contactsRepo;




    public SmisEntity addSmis(MultipartFile[] files,
                              String[] fileNames,
                              //контакты
                              String[] phones,
                              String[] contactNames,
                              String[] positions,
                              //оборудование
                              String[] equipments,

                              //комплекс
                              String name,
                              String agreement,
                              Long parent_smis_id,
                              String region_name,
                              Date validity,
                              String description) throws IOException {


        File folder = new File("smis_files");

        if (!folder.exists()) {
            folder.mkdir();
        }
        SmisEntity smisEntity = new SmisEntity();
        smisEntity.setName(name);
        smisEntity.setAgreement(agreement);
        if(parent_smis_id != null)
            smisEntity.setParentSmis(smisesRepo.findById(parent_smis_id).get());

        smisEntity.setRegion(getRegionByNameOrAdd(region_name));
        smisEntity.setValidity(validity);
        smisEntity.setDescription(description);
        smisEntity.setDateRegistration(new Date());

        for (int i = 0; i < fileNames.length; i++) {

            byte[] fileBytes = files[i].getBytes();
            new File("smis_files/" + name + '_' + region_name).mkdir();
            String rootPath ="smis_files/"  + name + '_' + region_name + '/';
                    System.out.println("File content type: " + files[i].getContentType());
            File newFile = new File(rootPath + files[i].getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
            stream.write(fileBytes);
            stream.close();
            String link = newFile.getAbsolutePath();
            smisEntity.getFiles().add(new SmisFileEntity(fileNames[i], link, smisEntity));

        }

        for (int i = 0; i < phones.length; i++) {
            smisEntity.getContacts().add(new ContactEntity(smisEntity, contactNames[i], positions[i], phones[i]));
        }


        smisesRepo.save(smisEntity);

        for (int i = 0; i < equipments.length; i++) {
            smisEquipmentRepo.save(new SmisEquipmentEntity(smisEntity, getEquipmentByNameOrAdd(equipments[i])));
        }

        return smisEntity;
    }

    public void deleteSmis(Long smisId) throws IOException {
        SmisEntity smis = smisesRepo.findById(smisId).get();

        List<SmisEntity> childs = smisesRepo.findAllByParentSmis(smis);
        for (SmisEntity child:childs) {
            child.setParentSmis(null);
        }

        smisesRepo.saveAll(childs);

        smisFileRepo.deleteAll(smis.getFiles());
        contactsRepo.deleteAll(smis.getContacts());

        smisEquipmentRepo.deleteAll(smisEquipmentRepo.findAllBySmisId(smisId));

        File folderSmis = new File("smis_files/" + smis.getName() + '_' + smis.getRegion().getName());
        if (folderSmis.exists()) {
            FileUtils.deleteDirectory(folderSmis);
        }

        smisesRepo.delete(smis);


    }

    public File getFileById(Long fileId){
        return new File(smisFileRepo.findById(fileId).get().getLink());
    }


    public RegionEntity getRegionByNameOrAdd(String regionName){
        RegionEntity regionEntity = regionsRepo.findByName(regionName);
        if(regionEntity == null) {
            regionEntity = new RegionEntity(regionName);
            regionsRepo.save(regionEntity);
        }
        return regionEntity;
    }

    public EquipmentEntity getEquipmentByNameOrAdd(String equipmentName){
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
