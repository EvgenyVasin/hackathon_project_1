package ru.basisintellect.support_smis.controllers;

import net.minidev.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            String sities,
            String street,
            String number,
            //комплекс
            String name,
            Long parent_smis_id,
            String region_name,
            String validity,
            String description,
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

                        name,
                        parent_smis_id,
                        region_name,
                        validity,
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
        model.addAttribute("regionList", smisService.getAllRegionsSort());
        model.addAttribute("districtsList", smisService.getAllDistrictsSort());
        model.addAttribute("countryesList",smisService.getAllCountryesSort());
        model.addAttribute("equipmentList", smisService.getAllEquipments());
        model.addAttribute("cantryList", smisService.getAllCountryesSort());
        return "smises/add_smis";
    }

    @RequestMapping(value = "/download/{fileName}", method = RequestMethod.GET)
    public HttpEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        SmisFileEntity asset = smisService.getFileAsset(fileName);
        File content = smisService.getFile(fileName);


        HttpHeaders httpHeaders = new HttpHeaders();
        String URLEncodedFileName = URLEncoder.encode(asset.getName(), "UTF-8");
        String ResultFileName = URLEncodedFileName.replace('+', ' ');
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename*=\"utf8'ru-ru'" + ResultFileName
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
            obj.put("regionName", entity.getCity().getName());
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
