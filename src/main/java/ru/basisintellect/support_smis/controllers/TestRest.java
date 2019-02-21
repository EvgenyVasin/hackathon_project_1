package ru.basisintellect.support_smis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.basisintellect.support_smis.model.entities.DistrictEntity;
import ru.basisintellect.support_smis.services.SmisService;

import java.util.List;
import java.util.Set;

@RestController
public class TestRest {
    @Autowired
    SmisService smisService;


    @RequestMapping(value = "/districts", method = RequestMethod.POST)
    List<DistrictEntity> getDistricts(Long cantryId){
        List<DistrictEntity> districtsList = smisService.getDistricsByCountryId(cantryId);
        return districtsList;
    }

    @RequestMapping(value = "/districts", method = RequestMethod.GET)
    List<DistrictEntity> getDistricts(){
        List<DistrictEntity> districtsList = smisService.getAllDistrictsSort();
        return districtsList;
    }
}
