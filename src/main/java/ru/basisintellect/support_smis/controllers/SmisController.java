package ru.basisintellect.support_smis.controllers;

import net.minidev.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import ru.basisintellect.support_smis.model.entities.SmisEntity;
import ru.basisintellect.support_smis.model.entities.SmisFileEntity;
import ru.basisintellect.support_smis.services.SmisService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasin.e on 17.01.2019.
 */

@Controller
public class SmisController {

//    @Autowired
//    SmisRepository smisRepository;

    @Autowired
    SmisService smisService;

    //добавление ПК ИВ СМИС
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "/addSmis", method = RequestMethod.POST)
    public String addSmis(
            //файлы
//                                        @RequestParam("files")
            MultipartFile[] files,
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
            String validity,
            String description,
            Model model) {


        try {
            smisService.addSmis(
                    files,
                    fileNames,

                    phones,
                    contactNames,
                    positions,

                    equipments,

                    name,
                    agreement,
                    parent_smis_id,
                    region_name,
                    new SimpleDateFormat("yyyy-MM-dd").parse(validity),
                    description);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewListSmises(model);
        return "redirect:/smises_list";
    }

    //получение ПК ИВ СМИС
    //@PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "smis_details/{smisId}", method = RequestMethod.GET)
    public String getSmis(@PathVariable("smisId") Long smisId, Model model) {
        model.addAttribute("smis", smisService.findSmisById(smisId));
        return "smises/smis_details";
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "delete_smis/{smisId}", method = RequestMethod.GET)
    public String delSmis(@PathVariable("smisId") Long smisId, Model model) {
        try {
            smisService.deleteSmis(smisId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/smises_list";
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
        return "smises/smises_table";
    }

    //генерация листа со смисами для страницы добавления
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value = "/add_smis")
    public String viewSmisesAdd(Model model) {
        model.addAttribute("smisesList", smisService.getAllSmises());
        model.addAttribute("regionList", smisService.getAllRegions());
        model.addAttribute("equipmentList", smisService.getAllEquipments());
        return "smises/add_smis";
    }

    @RequestMapping(value = "/download/{hash}", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadFile(@PathVariable String hash) throws IOException {
        SmisFileEntity asset = smisService.getFileAsset(hash);
        File content = smisService.getFile(asset.getFileName());


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + asset.getName().replace(" ", "_")
        );

        httpHeaders.setContentLength(content.length());
        return new HttpEntity<>(FileUtils.readFileToByteArray(content), httpHeaders);

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
            obj.put("regionName", entity.getRegion().getName());
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
