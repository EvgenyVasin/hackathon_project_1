package ru.basisintellect.support_smis.services;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.basisintellect.support_smis.controllers.SmisController;
import ru.basisintellect.support_smis.model.entities.*;
import ru.basisintellect.support_smis.repositories.*;

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
    FileAssetService assetService;

    @Autowired
    AreaStateRepository areaStateRepository;





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
                              String city_name,
                              String street,
                              String number,

                              //комплекс
                              String name,
                              Long parent_smis_id,

                              String validity,
                              String description,
                              String areaState) throws IOException, ParseException {


//        File folder = new File("smis_files");
//
//        if (!folder.exists()) {
//            folder.mkdir();
//        }
        SmisEntity smisEntity = new SmisEntity();
        smisEntity.setName(name);
        if(parent_smis_id != null)
            smisEntity.setParentSmis(smisesRepo.findById(parent_smis_id).get());

        smisEntity.setCity(getCityByNameOrCreate(city_name, region_id));

            if(!validity.isEmpty())
                smisEntity.setValidity(new SimpleDateFormat("yyyy-MM-dd").parse(validity));


        smisEntity.setDescription(description);
        smisEntity.setDateRegistration(new Date());

        smisesRepo.save(smisEntity);

        for (int i = 0; i < fileNames.length; i++) {

            SmisFileEntity asset = new SmisFileEntity();
            asset.setSmis(smisEntity);
            asset.setCustomName(fileNames[i]);
            asset.setDescription(fileDescriptions[i]);
            asset.setName(files[i].getOriginalFilename());
            File tempFile = Files.createTempFile(UUID.randomUUID().toString(), files[i].getOriginalFilename()).toFile();
            files[i].transferTo(tempFile);

            smisEntity.getFiles().add(assetService.createFileAsset(asset, tempFile));

//            byte[] fileBytes = files[i].getBytes();
//            new File("smis_files/" + smisEntity.getId()).mkdir();
//            String rootPath ="smis_files/"  + smisEntity.getId() + '/';
//                    System.out.println("File content type: " + files[i].getContentType());
//            File newFile = new File(rootPath + files[i].getOriginalFilename());
//            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
//            stream.write(fileBytes);
//            stream.close();
//            String link = newFile.getAbsolutePath();
//            smisEntity.getFiles().add(new SmisFileEntity(fileNames[i], link, smisEntity));

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

    private CityEntity getCityByNameOrCreate(String city_name, Long region_id) {
        CityEntity cityEntity = cityRepo.findByName(city_name);
        if(cityEntity == null) {
            cityEntity = new CityEntity(city_name, regionsRepo.findById(region_id).get());
            cityRepo.save(cityEntity);
        }
        return cityEntity;
    }

    public void deleteSmis(Long smisId) throws IOException {
        SmisEntity smis = smisesRepo.findById(smisId).get();

        List<SmisEntity> childs = smisesRepo.findAllByParentSmis(smis);
        for (SmisEntity child:childs) {
            child.setParentSmis(null);
        }

        smisesRepo.saveAll(childs);
        assetService.deleteFileAssets(smis.getFiles());
//        smisFileRepo.deleteAll(smis.getFiles());
        contactsRepo.deleteAll(smis.getContacts());

        smisEquipmentRepo.deleteAll(smisEquipmentRepo.findAllBySmisId(smisId));

        File folderSmis = new File("smis_files/" + smis.getName() + '_' + smis.getCity().getName());
        if (folderSmis.exists()) {
            FileUtils.deleteDirectory(folderSmis);
        }

        smisesRepo.delete(smis);


    }

//    public File getFileById(Long fileId){
//        return new File(smisFileRepo.findById(fileId).get().getLink());
//    }


    public RegionEntity getRegionByNameOrAdd(String regionName){
        RegionEntity regionEntity = regionsRepo.findByName(regionName).get();
        if(regionEntity == null) {
            regionEntity = new RegionEntity(regionName);
            regionsRepo.save(regionEntity);
        }
        return regionEntity;
    }

    public CityEntity getCityByName(String cityName){
        return cityRepo.findByName(cityName);
    }



    public List<RegionEntity> getAllRegionsSort(){
        return regionsRepo.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    public List<DistrictEntity> getAllDistrictsSort(){
        return districtRepo.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    public List<CountryEntity> getAllCountryesSort(){
        return countryRepo.findAll(new Sort(Sort.Direction.ASC, "name"));
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

    public List<DistrictEntity>getDistricsByCountryId(Long cantryId){

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


    public SmisFileEntity getFileAsset(Long id) {
        return assetService.getFileAsset(id);
    }

    public SmisFileEntity getFileAsset(String fileName) {
        return assetService.getFileAsset(fileName);
    }


    public File getFile(String fileName) {

        return assetService.getFile(fileName);
    }


    public CountryEntity getCountryById(Long cantryId) {
       return countryRepo.findById(cantryId).get();
    }


    public List<AreaStateEntity> getAllAreaStateSort() {
        return (List<AreaStateEntity>) areaStateRepository.findAll();
    }
}
