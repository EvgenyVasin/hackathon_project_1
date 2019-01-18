package ru.basisintellect.support_smis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.basisintellect.support_smis.entities.Smis;
import ru.basisintellect.support_smis.repositories.SmisRepository;

import java.util.Date;

/**
 * Created by vasin.e on 17.01.2019.
 */

@Controller
public class SmisController {
    @Autowired
    SmisRepository smises;

    //добавление ПК ИВ СМИС
    @RequestMapping(value = "/addSmis", method = RequestMethod.POST)
    public String addSmis(String region, String agreement, String validity, String contacts, String url, Model model) {
        if (region.isEmpty() || agreement.isEmpty() || validity.isEmpty() || contacts.isEmpty())
            return null;
        Smis smis = new Smis();
        smis.setRegion(region);
        smis.setDateRegistration(new Date());
        smis.setAgreement(agreement);
        smis.setValidity(validity);
        smis.setContacts(contacts);
        smis.setUrl(url);
        smises.save(smis);
        return viewSmises(model);
    }

    //получение ПК ИВ СМИС
    @RequestMapping(value = "smis_details/{smisId}", method = RequestMethod.GET)
    public String getSmis(@PathVariable("smisId") Long smisId, Model model) {
        Smis smis = smises.findById(smisId).get();
        model.addAttribute("smis", smis);
        return "smis/smis_details";
    }

    //редактирование ПК ИВ СМИС
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "/smis_details", method = RequestMethod.POST)
    public String editSmis(Long smisId, String region, String agreement, String validity, String contacts, String url, Model model) {
        Smis smis = smises.findById(smisId).get();
        smis.setRegion(region);
        smis.setAgreement(agreement);
        smis.setValidity(validity);
        smis.setContacts(contacts);
        smis.setUrl(url);
        smises.save(smis);
        model.addAttribute("smis", smis);
        return "smises/smis_details";
    }

    //генерация страницы со смисами
    @RequestMapping(value = "/smises_table")
    public String viewSmises(Model model) {
        model.addAttribute("smisesList", smises.findAll());
        return "smises/smises_table";
    }

}
