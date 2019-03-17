package ru.mts.test.hackathon_project_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.test.hackathon_project_1.model.entities.CityEntity;
import ru.mts.test.hackathon_project_1.model.entities.DistrictEntity;
import ru.mts.test.hackathon_project_1.model.entities.RegionEntity;
import ru.mts.test.hackathon_project_1.services.SmisService;

import java.util.List;

@RestController
public class SmisRestController {

    @Autowired
    SmisService smisService;


    @RequestMapping(value = "/districts", method = RequestMethod.POST)
    List<DistrictEntity> getDistricts(Long cantryId){

        return smisService.getDistricsByCountryId(cantryId);
    }

    @RequestMapping(value = "/regions", method = RequestMethod.POST)
    List<RegionEntity> getRegions(Long districtId){

        return smisService.getRegionsByDistrictId(districtId);
    }

    @RequestMapping(value = "/cities", method = RequestMethod.POST)
    List<CityEntity> getCities(Long regionId){

        return smisService.getCytiesByRegionId(regionId);
    }

    @RequestMapping(value = "/districts", method = RequestMethod.GET)
    List<DistrictEntity> getDistricts(){
        List<DistrictEntity> districtsList = smisService.getAllDistrictsSort();
        return districtsList;
    }



}
