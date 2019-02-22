package ru.basisintellect.support_smis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.basisintellect.support_smis.model.entities.CityEntity;
import ru.basisintellect.support_smis.model.entities.DistrictEntity;
import ru.basisintellect.support_smis.model.entities.RegionEntity;
import ru.basisintellect.support_smis.services.SmisService;

import java.util.List;
import java.util.Set;

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
