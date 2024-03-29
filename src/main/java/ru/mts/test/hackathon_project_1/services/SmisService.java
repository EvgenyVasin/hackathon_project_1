package ru.mts.test.hackathon_project_1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.mts.test.hackathon_project_1.controllers.SmisController;
import ru.mts.test.hackathon_project_1.model.entities.*;
import ru.mts.test.hackathon_project_1.repositories.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    CityRepository cityRepo;

    @Autowired
    DistrictRepository districtRepo;

    @Autowired
    CountryRepository countryRepo;

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


    @Autowired
    AreaStateRepository areaStateRepository;

    @Autowired
    SmisTypeRepository smisTypeRepo;

    public SmisEntity addSmis(MultipartFile[] files,
                              String[] fileNames,
                              String[] fileDescriptions,
                              //контакты
                              String[] phones,
                              String[] contactNames,
                              String[] positions,
                              //оборудование
                              String[] equipments,

                              //место расположения
                              Long region_id,
                              String cities,
                              String street,
                              String number,

                              //комплекс
                              String name,
                              Long parent_smis_id,
                              Long smis_type_id,

                              String validity,
                              String description,
                              Long areaState_id) throws IOException, ParseException {


//        File folder = new File("smis_files");
//
//        if (!folder.exists()) {
//            folder.mkdir();
//        }
        SmisEntity smisEntity = new SmisEntity();
        smisEntity.setDateRegistration(new Date());


        String address = "";
        if (!street.isEmpty())
            address = street + " " + number;

        return saveSmisEntity(
                files,
                fileNames,
                fileDescriptions,
                phones,
                contactNames,
                positions,
                equipments,
                region_id,
                cities,
                address,
                name,
                parent_smis_id,
                smis_type_id,
                validity,
                description,
                areaState_id,
                smisEntity);
    }

    public SmisEntity editSmis(MultipartFile[] files,
                               String[] fileNames,
                               String[] fileDescriptions,
                               Long[] deleted_files,

                               String[] phones,
                               String[] contactNames,
                               String[] positions,
                               Long[] deleted_contacts,

                               String[] equipments,
                               Long[] deleted_equipments,

                               Long region_id,
                               String cities,
                               String street,


                               Long smis_id,
                               String name,
                               Long parent_smis_id,
                               Long smis_type_id,
                               String validity,
                               String description,
                               Long areaState_id) throws IOException, ParseException {



        if (deleted_contacts != null) {
            for (Long id : deleted_contacts) {
                contactsRepo.delete(contactsRepo.findById(id).get());
            }
        }

        if (deleted_equipments != null) {
            for (Long id : deleted_equipments) {
                smisEquipmentRepo.delete(smisEquipmentRepo.findById(id).get());
            }
        }

        SmisEntity smisEntity = smisesRepo.findById(smis_id).get();

        return saveSmisEntity(
                                files,
                                fileNames,
                                fileDescriptions,
                                phones,
                                contactNames,
                                positions,
                                equipments,
                                region_id,
                                cities,
                                street,
                                name,
                                parent_smis_id,
                                smis_type_id,
                                validity,
                                description,
                                areaState_id,
                                smisEntity);

    }



    private SmisEntity saveSmisEntity(
            MultipartFile[] files,
            String[] fileNames,
            String[] fileDescriptions,

            String[] phones,
            String[] contactNames,
            String[] positions,

            String[] equipments,

            Long region_id,
            String cities,
            String address,
            String name,
            Long parent_smis_id,
            Long smis_type_id,
            String validity,
            String description,
            Long areaState_id,
            SmisEntity smisEntity) throws ParseException, IOException {

        smisEntity.setSmisType(smisTypeRepo.findById(smis_type_id).get());
        smisEntity.setName(name);
        if (parent_smis_id != null) {
            smisEntity.setParentSmis(smisesRepo.findById(parent_smis_id).get());
        }else{
            smisEntity.setParentSmis(null);
        }

        smisEntity.setCity(getCityByNameOrCreate(cities, region_id));
        smisEntity.setAddress(address);
        smisEntity.setAreaState(areaStateRepository.findById(areaState_id).get());

        if (!validity.isEmpty())
            smisEntity.setValidity(new SimpleDateFormat("yyyy-MM-dd").parse(validity));


        smisEntity.setDescription(description);

        smisesRepo.save(smisEntity);

        if(files.length>0) {
            if(fileNames.length<1)fileNames = new String[]{""};
            if(fileDescriptions.length<1)fileDescriptions = new String[]{""};
            for (int i = 0; i < files.length; i++) {
                if(!files[i].getOriginalFilename().isEmpty()) {
                    SmisFileEntity asset = new SmisFileEntity();
                    asset.setSmis(smisEntity);//
                    if(fileNames[i].isEmpty())
                        asset.setCustomName(files[i].getOriginalFilename());
                    else
                        asset.setCustomName(fileNames[i]);


                    asset.setDescription(fileDescriptions[i]);

                    asset.setName(files[i].getOriginalFilename());
                    File tempFile = Files.createTempFile(UUID.randomUUID().toString(), files[i].getOriginalFilename()).toFile();
                    files[i].transferTo(tempFile);


                }

            }
        }

        if(phones.length>0 || contactNames.length>0 ||positions.length > 0) {

            if(phones.length < 1)phones = new String[]{""};
            if(contactNames.length < 1)contactNames = new String[]{""};
            if(positions.length < 1)positions = new String[]{""};

            for (int i = 0; i < phones.length; i++) {
                if(!phones[i].isEmpty()||!contactNames[i].isEmpty()||!positions[i].isEmpty()) {
//                    String contactName = "";
////                    if (contactNames[i]!=null)
//                        contactName = contactNames[i];
//
//                    String position = "";
////                    if (positions[i]!=null)
//                        position = positions[i];
//
//                    String phone = "";
////                    if (phones[i]!=null)
//                        phone = phones[i];

                    smisEntity.getContacts().add(new ContactEntity(smisEntity, contactNames[i], positions[i], phones[i]));
                }
            }
        }


        smisesRepo.save(smisEntity);

        for (int i = 0; i < equipments.length; i++) {
            if( !equipments[i].isEmpty())
                smisEquipmentRepo.save(new SmisEquipmentEntity(smisEntity, getEquipmentByNameOrAdd(equipments[i])));
        }

        return smisEntity;
    }

    private CityEntity getCityByNameOrCreate(String city_name, Long region_id) {
        CityEntity cityEntity = cityRepo.findByName(city_name);
        if (cityEntity == null) {
            cityEntity = new CityEntity(city_name, regionsRepo.findById(region_id).get());
            cityRepo.save(cityEntity);
        }
        return cityEntity;
    }

    public void deleteSmis(Long smisId) throws IOException {
        SmisEntity smis = smisesRepo.findById(smisId).get();

        List<SmisEntity> childs = smisesRepo.findAllByParentSmis(smis);
        for (SmisEntity child : childs) {
            child.setParentSmis(null);
        }

        smisesRepo.saveAll(childs);
//        smisFileRepo.deleteAll(smis.getFiles());
        contactsRepo.deleteAll(smis.getContacts());

        smisEquipmentRepo.deleteAll(smisEquipmentRepo.findAllBySmisId(smisId));


        smisesRepo.delete(smis);


    }

//    public File getFileById(Long fileId){
//        return new File(smisFileRepo.findById(fileId).get().getLink());
//    }


    public RegionEntity getRegionByNameOrAdd(String regionName) {
        RegionEntity regionEntity = regionsRepo.findByName(regionName).get();
        if (regionEntity == null) {
            regionEntity = new RegionEntity(regionName);
            regionsRepo.save(regionEntity);
        }
        return regionEntity;
    }

    public CityEntity getCityByName(String cityName) {
        return cityRepo.findByName(cityName);
    }


    public List<RegionEntity> getAllRegionsSort() {
        return regionsRepo.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    public List<DistrictEntity> getAllDistrictsSort() {
        return districtRepo.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    public List<CountryEntity> getAllCountryesSort() {
        return countryRepo.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    public EquipmentEntity getEquipmentByNameOrAdd(String equipmentName) {
        EquipmentEntity equipmentEntity = equipmentRepo.findByName(equipmentName);
        if (equipmentEntity == null) {
            equipmentEntity = new EquipmentEntity(equipmentName);
            equipmentRepo.save(equipmentEntity);
        }
        return equipmentEntity;
    }

    public SmisEquipmentEntity addSmisEquipment(SmisEntity smis, EquipmentEntity equipment) {
        return smisEquipmentRepo.save(new SmisEquipmentEntity(smis, equipment));
    }

    public List<DistrictEntity> getDistricsByCountryId(Long cantryId) {

        return districtRepo.findByCountry(countryRepo.findById(cantryId).get());
    }

    public List<RegionEntity> getRegionsByDistrictId(Long districtId) {
        return regionsRepo.findByDistrict(districtRepo.findById(districtId).get());
    }

    public List<CityEntity> getCytiesByRegionId(Long regionId) {
        return cityRepo.findByRegion(regionsRepo.findById(regionId).get());
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

        return smisesRepo.findById(id).get();
    }






    public CountryEntity getCountryById(Long cantryId) {
        return countryRepo.findById(cantryId).get();
    }


    public List<AreaStateEntity> getAllAreaStateSort() {
        return (List<AreaStateEntity>) areaStateRepository.findAll();
    }


    public List<SmisTypeEntity> getSmisTypes() {
        return (List<SmisTypeEntity>) smisTypeRepo.findAll();
    }
}
