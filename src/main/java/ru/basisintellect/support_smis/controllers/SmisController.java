package ru.basisintellect.support_smis.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.basisintellect.support_smis.model.entities.SmisEntity;
import ru.basisintellect.support_smis.services.SmisService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasin.e on 17.01.2019.
 */

@Controller
public class SmisController {
    @Autowired
    SmisService smisService;

    //добавление ПК ИВ СМИС
    @RequestMapping(value = "/addSmis", method = RequestMethod.POST)
    public String addSmis(String name, String agreement, String validity, String contacts, String url, Long parent_id, Model model) {
        if (name.isEmpty() || agreement.isEmpty() || validity.isEmpty() || contacts.isEmpty())
            return null;
        smisService.addSmis(name, agreement, validity, contacts, url, parent_id);
        return viewSmises(model);
    }

    //получение ПК ИВ СМИС
    @RequestMapping(value = "smis_details/{smisId}", method = RequestMethod.GET)
    public String getSmis(@PathVariable("smisId") Long smisId, Model model) {
        model.addAttribute("smis", smisService.findSmisById(smisId));
        return "smis/smis_details";
    }

    //редактирование ПК ИВ СМИС
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "/smis_details", method = RequestMethod.POST)
    public String editSmis(Long smisId, String region, String agreement, String validity, String contacts, String url, SmisEntity parent, Model model) {
        model.addAttribute("smis", smisService.editSmis(smisId, region, agreement, validity, contacts, url, parent));
        return "smises/smis_details";
    }

    //генерация страницы со смисами
    @RequestMapping(value = "/smises_table")
    public String viewSmises(Model model) {
        model.addAttribute("smisesList", smisService.getAllSmises());
        return "smises/smises_table";
    }

    //генерация листа со смисами для страницы добавления
    @RequestMapping(value = "/add_smis")
    public String viewSmisesAdd(Model model) {
        model.addAttribute("smisesList", smisService.getAllSmises());
        return "smises/add_smis";
    }

    //генерация страницы со смисами2
    @RequestMapping(value = "/smises_list")
    public String viewListSmises(Model model) {
        List<JSONObject> data = new ArrayList<>();
        List<SmisEntity> list = smisService.getAllSmises();

        for (SmisEntity entity : list) {
            SmisEntity parentSMIS = entity.getParentSmis();
            JSONObject obj = new JSONObject();
            obj.appendField("itemId", entity.getId());
            obj.appendField("itemName", entity.getName());
            if(parentSMIS != null)
                obj.appendField("itemParentId", entity.getParentSmis().getId());
            else
                obj.appendField("itemParentId", null);
            data.add(obj);
        }
        model.addAttribute("data", data);
        return "smises/smises_list";
    }
}
