package ru.basisintellect.support_smis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.basisintellect.support_smis.model.entities.DistrictEntity;
import ru.basisintellect.support_smis.model.entities.RegionEntity;
import ru.basisintellect.support_smis.services.SmisService;

import java.util.List;

@Controller
public class Test {

    @Autowired
    SmisService smisService;
    @RequestMapping(value = "/test")
    public String test(Model model){

        model.addAttribute("cantryList", smisService.getAllCountryesSort());

        return "test";
    }

    List<RegionEntity> getRegions(Long id){

        return null;
    }

}
