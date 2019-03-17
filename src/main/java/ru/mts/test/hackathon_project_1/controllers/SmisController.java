package ru.mts.test.hackathon_project_1.controllers;

import net.minidev.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import ru.mts.test.hackathon_project_1.model.entities.SmisFileEntity;
import ru.mts.test.hackathon_project_1.model.entities.SmisEntity;
import ru.mts.test.hackathon_project_1.services.SmisService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для работы с данными по СМИСам с полем <b>smisService</b>
 * Created by vasin.e on 17.01.2019.
 */

@Controller
public class SmisController {

//    @Autowired
//    SmisRepository smisRepository;

    /**
     * Поле <b>smisService</b>
     * @param smisService
     */
    @Autowired
    SmisService smisService;
    @RequestMapping(value = "/editSmis", method = RequestMethod.POST)
    public String editSmis(
            //файлы
//          @RequestParam("files")
            MultipartFile[] files,
            String[] fileNames,
            String[] fileDescriptions,
            Long[] deleted_files,
            //контакты
            String[] phones,
            String[] contactNames,
            String[] positions,
            Long[] deleted_contacts,
            //оборудование
            String[] equipments,
            Long[] deleted_equipments,
            //место расположения
            Long regions,
            String cities,
            String street,

            //комплекс
            Long smis_id,
            String name,
            Long parent_smis_id,
            Long smis_type_id,
            String validity,
            String description,
            Long areaState_id,
            Model model) {

        try {
            smisService.editSmis(
                    files,
                    fileNames,
                    fileDescriptions,
                    deleted_files,

                    phones,
                    contactNames,
                    positions,
                    deleted_contacts,

                    equipments,
                    deleted_equipments,

                    //место расположения
                    regions,
                    cities,
                    street,

                    smis_id,
                    name,
                    parent_smis_id,
                    smis_type_id,
                    validity,
                    description,
                    areaState_id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return "redirect:/smises_list";

    }

    /**
     * Метод добавления нового <a>Smis</a> только для авторизованных пользователей с ролями <b>Admin</b> или <b>User</b>
     * @return возвращает перенаправление на таблицу со всеми объектами <a>Smis</a>
     */

    //добавление ПК ИВ СМИС
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "/addSmis", method = RequestMethod.POST)
    public String addSmis(
            //файлы
//          @RequestParam("files")
            MultipartFile[] files,
            String[] fileNames,
            String[] fileDescriptions,
            //контакты
            String[] phones,
            String[] contactNames,
            String[] positions,
            //оборудование
            String[] equipments,
            //место расположения
            Long regions,
            String cities,
            String street,
            String number,
            //комплекс
            String name,
            Long parent_smis_id,
            Long smis_type_id,
            String validity,
            String description,
            Long areaState_id,
            Model model) {


        //пробуем добавить новый СМИС и делаем перенаправление на страницу с таблицей
        try {



                smisService.addSmis(
                        files,
                        fileNames,
                        fileDescriptions,

                        phones,
                        contactNames,
                        positions,

                        equipments,

                        //место расположения
                        regions,
                        cities,
                        street,
                        number,

                        name,
                        parent_smis_id,
                        smis_type_id,
                        validity,
                        description,
                        areaState_id);
            } catch(IOException e){
                e.printStackTrace();
            } catch(ParseException e){
                e.printStackTrace();
            }

        return "redirect:/smises_list";
    }


    //получение ПК ИВ СМИС
    //@PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "smis_details/{smisId}", method = RequestMethod.GET)
    public String getSmis(@PathVariable("smisId") Long smisId, Model model) {
        SmisEntity smisEntity = smisService.findSmisById(smisId);
        model.addAttribute("smis", smisEntity);
        model.addAttribute("cantryList", smisService.getAllCountryesSort());
        model.addAttribute("districtsList", smisService.getDistricsByCountryId(smisEntity.getCity().getRegion().getDistrict().getCountry().getId()));
        model.addAttribute("regionList", smisService.getRegionsByDistrictId(smisEntity.getCity().getRegion().getDistrict().getId()));
        model.addAttribute("sitiesList", smisService.getCytiesByRegionId(smisEntity.getCity().getRegion().getId()));
        model.addAttribute("areaStateList", smisService.getAllAreaStateSort());
        model.addAttribute("smisesList", smisService.getAllSmises());
        model.addAttribute("typesList", smisService.getSmisTypes());

        return "smises/smis_details";
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "delete_smis/{smisId}", method = RequestMethod.GET)
    public String delSmis(@PathVariable("smisId") Long smisId, Model model, HttpServletRequest request) {
        try {
            smisService.deleteSmis(smisId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    //редактирование ПК ИВ СМИС
    /*@PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "/smis_details", method = RequestMethod.POST)
    public String editSmis(Long smisId, String region, String agreement, String validity, String contacts, String url, SmisEntity parent, Model model) {
        model.addAttribute("smis", smisService.editSmis(smisId, region, agreement, validity, contacts, url, parent));
        return "smises/smis_details";
    }*/

    //генерация страницы со смисами
    //@PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "/smises_table")
    public String viewSmises(Model model) {
        List<SmisEntity> smisesList = smisService.getAllSmises();
        model.addAttribute("smisesList", smisesList);
        /*model.addAttribute("regionList", smisService.getAllRegionsSort());
        model.addAttribute("districtsList", smisService.getAllDistrictsSort());
        model.addAttribute("countryesList",smisService.getAllCountryesSort());
        model.addAttribute("areaStateList", smisService.getAllAreaStateSort());*/
        return "smises/smises_table";
    }

    //генерация листа со смисами для страницы добавления
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "/add_smis")
    public String viewSmisesAdd(Model model) {
        model.addAttribute("smisesList", smisService.getAllSmises());
        //model.addAttribute("regionList", smisService.getAllRegionsSort());
        //model.addAttribute("districtsList", smisService.getAllDistrictsSort());
        model.addAttribute("equipmentList", smisService.getAllEquipments());
        model.addAttribute("cantryList", smisService.getAllCountryesSort());
        model.addAttribute("areaStateList", smisService.getAllAreaStateSort());
        model.addAttribute("typesList", smisService.getSmisTypes());
        return "smises/add_smis";
    }


    //генерация страницы со смисами2
    //@PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "/smises_list")
    public String viewListSmises(Model model) {
        List<JSONObject> data = new ArrayList<>();
        List<SmisEntity> list = smisService.getAllSmises();

        for (SmisEntity entity : list) {
            SmisEntity parentSMIS = entity.getParentSmis();
            JSONObject obj = new JSONObject();
            obj.put("itemId", entity.getId());
            obj.put("itemName", entity.getName());
            obj.put("regionName", entity.getNameByAreaState());
            obj.put("typeName", entity.getSmisType().getName());

            if (parentSMIS != null)
                obj.put("itemParentId", entity.getParentSmis().getId());
            else
                obj.put("itemParentId", null);
            data.add(obj);
        }
        model.addAttribute("data", data);
        return "smises/smises_list";
    }
}
