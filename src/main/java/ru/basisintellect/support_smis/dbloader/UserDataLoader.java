package ru.basisintellect.support_smis.dbloader;


import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.basisintellect.support_smis.model.entities.*;
import ru.basisintellect.support_smis.repositories.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//import ru.basisintellect.support_smis.services.SmisService;


/**
 * Created by safin.v on 24.10.2016.
 */
@Component
public class UserDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    SmisRepository smises;

    @Autowired
    private RegionRepository regionRepository;
//    @Autowired
//    SmisService smisService;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    ContactsRepository contactsRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    CountryRepository countryRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {


            /*while(true){


            }*/


            //добавление состояний работоспособности СМИС
            StateEntity stateUnSuccess = new StateEntity();
            stateUnSuccess.setStateName("НЕ РАБОТАЕТ");
            stateUnSuccess.setStateCode("UN_SUCCESS");
            stateRepository.save(stateUnSuccess);
//            stateUnSuccess.setId((long) 2);
            System.out.println("stateUnSuccess");

            StateEntity stateSuccess = new StateEntity();
            stateSuccess.setStateName("РАБОТАЕТ");
            stateSuccess.setStateCode("SUCCESS");
            stateRepository.save(stateSuccess);
//            stateSuccess.setId((long) 1);
            System.out.println("stateSuccess");

            StateEntity stateOffline = new StateEntity();
            stateOffline.setStateName("НЕТ КОМПЛЕКСА");
            stateOffline.setStateCode("NO_COMPLEX");
            stateRepository.save(stateOffline);
//            stateOffline.setId((long) 3);

            System.out.println("stateOffline");

            СountryEntity country;
            DistrictEntity district;
            Set<RegionEntity> regions;

            country = new СountryEntity("Российская Федерация");
            countryRepository.save(country);

            district = new DistrictEntity("Центральный", country);
            regions = district.getRegions();
            regions.add(new RegionEntity(31, "Белгородская область", district));
            regions.add(new RegionEntity(32, "Брянская область", district));
            regions.add(new RegionEntity(33, "Владимирская область", district));
            regions.add(new RegionEntity(36, "Воронежская область", district));
            regions.add(new RegionEntity(37, "Ивановская область", district));
            regions.add(new RegionEntity(40, "Калужская область", district));
            regions.add(new RegionEntity(44, "Костромская область", district));
            regions.add(new RegionEntity(46, "Курская область", district));
            regions.add(new RegionEntity(48, "Липецкая область", district));
            regions.add(new RegionEntity(50, "Московская область", district));
            regions.add(new RegionEntity(57, "Орловская область", district));
            regions.add(new RegionEntity(62, "Рязанская область", district));
            regions.add(new RegionEntity(67, "Смоленская область", district));
            regions.add(new RegionEntity(68, "Тамбовская область", district));
            regions.add(new RegionEntity(69, "Тверская область", district));
            regions.add(new RegionEntity(71, "Тульская область", district));
            regions.add(new RegionEntity(76, "Ярославская область", district));
            regions.add(new RegionEntity(77, "г.Москва", district));
            districtRepository.save(district);

            district = new DistrictEntity("Южный", country);
            regions = district.getRegions();
            regions.add(new RegionEntity(1, "Республика Адыгея (Адыгея)", district));
            regions.add(new RegionEntity(8, "Республика Калмыкия", district));
            regions.add(new RegionEntity(23, "Краснодарский край", district));
            regions.add(new RegionEntity(30, "Астраханская область", district));
            regions.add(new RegionEntity(34, "Волгоградская область", district));
            regions.add(new RegionEntity(61, "Ростовская область", district));
            districtRepository.save(district);

            district = new DistrictEntity("Северо-Западный", country);
            regions = district.getRegions();
            regions.add(new RegionEntity(10, "Республика Карелия", district));
            regions.add(new RegionEntity(11, "Республика Коми", district));
            regions.add(new RegionEntity(29, "Архангельская область", district));
            regions.add(new RegionEntity(35, "Вологодская область", district));
            regions.add(new RegionEntity(39, "Калининградская область", district));
            regions.add(new RegionEntity(47, "Ленинградская область", district));
            regions.add(new RegionEntity(51, "Мурманская область", district));
            regions.add(new RegionEntity(53, "Новгородская область", district));
            regions.add(new RegionEntity(60, "Псковская область", district));
            regions.add(new RegionEntity(78, "Санкт - Петербург", district));
            regions.add(new RegionEntity(83, "Ненецкий автономный округ", district));
            districtRepository.save(district);

            district = new DistrictEntity("Дальневосточный", country);
            regions = district.getRegions();
            regions.add(new RegionEntity(14, "Республика Саха (Якутия)", district));
            regions.add(new RegionEntity(41, "Камчатский край", district));
            regions.add(new RegionEntity(25, "Приморский край", district));
            regions.add(new RegionEntity(27, "Хабаровский край", district));
            regions.add(new RegionEntity(28, "Амурская область", district));
            regions.add(new RegionEntity(49, "Магаданская область", district));
            regions.add(new RegionEntity(65, "Сахалинская область", district));
            regions.add(new RegionEntity(79, "Еврейская автономная область", district));
            regions.add(new RegionEntity(87, "Чукотский автономный округ", district));
            districtRepository.save(district);

            district = new DistrictEntity("Сибирский", country);
            regions = district.getRegions();
            regions.add(new RegionEntity(4, "Республика Алтай", district));
            regions.add(new RegionEntity(3, "Республика Бурятия", district));
            regions.add(new RegionEntity(17, "Республика Тыва", district));
            regions.add(new RegionEntity(19, "Республика Хакасия", district));
            regions.add(new RegionEntity(22, "Алтайский край", district));
            regions.add(new RegionEntity(75, "Забайкальский край", district));
            regions.add(new RegionEntity(24, "Красноярский край", district));
            regions.add(new RegionEntity(38, "Иркутская область", district));
            regions.add(new RegionEntity(42, "Кемеровская область", district));
            regions.add(new RegionEntity(54, "Новосибирская область", district));
            regions.add(new RegionEntity(55, "Омская область", district));
            regions.add(new RegionEntity(70, "Томская область", district));
            districtRepository.save(district);

            district = new DistrictEntity("Уральский", country);
            regions = district.getRegions();
            regions.add(new RegionEntity(45, "Курганская область", district));
            regions.add(new RegionEntity(66, "Свердловская область", district));
            regions.add(new RegionEntity(72, "Тюменская область", district));
            regions.add(new RegionEntity(74, "Челябинская область", district));
            regions.add(new RegionEntity(86, "Ханты - Мансийский автономный округ -Югра", district));
            regions.add(new RegionEntity(89, "Ямало - Ненецкий автономный округ", district));
            districtRepository.save(district);

            district = new DistrictEntity("Приволжский", country);
            regions = district.getRegions();
            regions.add(new RegionEntity(2, "Республика Башкортостан", district));
            regions.add(new RegionEntity(12, "Республика Марий Эл", district));
            regions.add(new RegionEntity(13, "Республика Мордовия", district));
            regions.add(new RegionEntity(16, "Республика Татарстан (Татарстан)", district));
            regions.add(new RegionEntity(18, "Удмуртская Республика", district));
            regions.add(new RegionEntity(21, "Чувашская Республика - Чувашия", district));
            regions.add(new RegionEntity(43, "Кировская область", district));
            regions.add(new RegionEntity(52, "Нижегородская область", district));
            regions.add(new RegionEntity(56, "Оренбургская область", district));
            regions.add(new RegionEntity(58, "Пензенская область", district));
            regions.add(new RegionEntity(59, "Пермский край", district));
            regions.add(new RegionEntity(63, "Самарская область", district));
            regions.add(new RegionEntity(64, "Саратовская область", district));
            regions.add(new RegionEntity(73, "Ульяновская область", district));
            districtRepository.save(district);

            district = new DistrictEntity("Северо-Кавказский", country);
            regions = district.getRegions();
            regions.add(new RegionEntity(5, "Республика Дагестан", district));
            regions.add(new RegionEntity(6, "Республика Ингушетия", district));
            regions.add(new RegionEntity(7, "Кабардино-Балкарская Республика", district));
            regions.add(new RegionEntity(9, "Карачаево-Черкесская Республика", district));
            regions.add(new RegionEntity(15, "Республика Северная Осетия - Алания", district));
            regions.add(new RegionEntity(20, "Чеченская Республика", district));
            regions.add(new RegionEntity(26, "Ставропольский край", district));
            districtRepository.save(district);

            district = new DistrictEntity("Крымский", country);
            regions = district.getRegions();
            regions.add(new RegionEntity(91, "Республика Крым(Симферополь)", district));
            regions.add(new RegionEntity(92, "Севастополь", district));
            districtRepository.save(district);




            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setUserRoleName("ROLE_ADMIN");
            userRoleRepository.save(adminRole);

            UserEntity admin = new UserEntity();

            admin.setPassword(bcryptEncoder.encode("admin"));
            admin.setMail("admin@list.ru");
            admin.setFirstName("Foo");
            admin.setLastName("Bar");
            admin.setDateRegistration(new Date());
            admin.setUserRole(adminRole);
            admin.setEnabled(true);
            userRepository.save(admin);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setUserRoleName("ROLE_USER");
            userRoleRepository.save(userRole);

            UserEntity userEntity1 = new UserEntity();

            userEntity1.setPassword(bcryptEncoder.encode("rozanov"));
            userEntity1.setMail("rozanov.a@basis-ic.ru");
            userEntity1.setFirstName("Алексей");
            userEntity1.setLastName("Розанов");
            userEntity1.setUserRole(userRole);
            userEntity1.setEnabled(true);
            userEntity1.setDateRegistration(new Date());
            userRepository.save(userEntity1);

            //Добавление СМИСа для тестов

            //удаление папки с файлами НА ВРЕМЯ ОТЛАДКИ
            //FileUtils.deleteDirectory(new File("smis_files"));




            /*SmisEntity smisNSUKS = new SmisEntity();

            Set<ContactEntity> contactsNSUKS = new HashSet<ContactEntity>(){{
                add(new ContactEntity(smisNSUKS, "Иван Васильевич", "ЦАРЬ", "палаты Кремля"));
                add(new ContactEntity(smisNSUKS, "Игорь Игорь", "в честь олимпийских игр", "8(191)023-42-89"));
                add(new ContactEntity(smisNSUKS, "Петр I", "император", "Зимний Дворец"));
            }};
            smisNSUKS.setContacts(contactsNSUKS);

            smisNSUKS.setName("НЦУКС");
            smisNSUKS.setDateRegistration(new Date());
            smisNSUKS.setAgreement("соглашение");
            smisNSUKS.setRegion(regionEntity);
            smisNSUKS.setValidity(new Date());

            smises.save(smisNSUKS);
            System.out.println("smisNSUKS");

            SmisEntity smisSUKS1 = new SmisEntity();

            Set<ContactEntity> contactsSUKS1 = new HashSet<ContactEntity>(){{
                add(new ContactEntity(smisSUKS1, "Владимир Путин", "президент", "8(919)-000-00-00"));
                add(new ContactEntity(smisSUKS1, "Дмитрий Медведев", "премьер министр", "8(191)023-42-89"));
                add(new ContactEntity(smisSUKS1, "Алексей Навальный", "видео блоггер", "8(919)-888-88-88"));
            }};
            smisSUKS1.setContacts(contactsSUKS1);

            smisSUKS1.setName("ЦУКС1");
            smisSUKS1.setParentSmis(smisNSUKS);
            smisSUKS1.setDateRegistration(new Date());
            smisSUKS1.setAgreement("соглашение");
            smisSUKS1.setRegion(regionEntity);
            smisSUKS1.setValidity(new Date());
            smises.save(smisSUKS1);
            System.out.println("smisSUKS1");

            SmisEntity smisSUKS2 = new SmisEntity();
            smisSUKS2.setName("ЦУКС2");
            smisSUKS2.setParentSmis(smisNSUKS);
            smisSUKS2.setDateRegistration(new Date());
            smisSUKS2.setAgreement("соглашение");
            smisSUKS2.setRegion(regionEntity);
            smisSUKS2.setValidity(new Date());
            smises.save(smisSUKS2);
            System.out.println("smisSUKS2");

            SmisEntity smisEDDS1 = new SmisEntity();
            smisEDDS1.setName("ЕДДС1");
            smisEDDS1.setParentSmis(smisSUKS1);
            smisEDDS1.setDateRegistration(new Date());
            smisEDDS1.setAgreement("соглашение");
            smisEDDS1.setRegion(regionEntity);
            smisEDDS1.setValidity(new Date());
            smises.save(smisEDDS1);
            System.out.println("smisEDDS1");

            SmisEntity smisEDDS2 = new SmisEntity();

            Set<ContactEntity> contactsEDDS2 = new HashSet<ContactEntity>(){{
                add(new ContactEntity(smisEDDS2, "Иван Васильевич", "ЦАРЬ", "палаты Кремля"));
                add(new ContactEntity(smisEDDS2, "Игорь Игорь", "в честь олимпийских игр", "8(191)023-42-89"));
                add(new ContactEntity(smisEDDS2, "Петр I", "император", "Зимний Дворец"));
                add(new ContactEntity(smisEDDS2, "Петр I", "император", "Зимний Дворец"));
                add(new ContactEntity(smisEDDS2, "Петр I", "император", "Зимний Дворец"));
                add(new ContactEntity(smisEDDS2, "Петр I", "император", "Зимний Дворец"));
                add(new ContactEntity(smisEDDS2, "Петр I", "император", "Зимний Дворец"));
                add(new ContactEntity(smisEDDS2, "Петр I", "император", "Зимний Дворец"));
            }};
            smisEDDS2.setContacts(contactsEDDS2);

            smisEDDS2.setName("ЕДДС2");
            smisEDDS2.setParentSmis(smisSUKS1);
            smisEDDS2.setDateRegistration(new Date());
            smisEDDS2.setAgreement("соглашение");
            smisEDDS2.setRegion(regionEntity);
            smisEDDS2.setValidity(new Date());
            smises.save(smisEDDS2);
            System.out.println("smisEDDS2");

            SmisEntity smisEDDS3 = new SmisEntity();
            smisEDDS3.setName("ЕДДС3");
            smisEDDS3.setParentSmis(smisSUKS1);
            smisEDDS3.setDateRegistration(new Date());
            smisEDDS3.setAgreement("соглашение");
            smisEDDS3.setRegion(regionEntity);
            smisEDDS3.setValidity(new Date());
            smises.save(smisEDDS3);
            System.out.println("smisEDDS3");

            SmisEntity smisEDDS4 = new SmisEntity();
            smisEDDS4.setName("ЕДДС4");
            smisEDDS4.setParentSmis(smisSUKS2);
            smisEDDS4.setDateRegistration(new Date());
            smisEDDS4.setAgreement("соглашение");
            smisEDDS4.setRegion(regionEntity);
            smisEDDS4.setValidity(new Date());
            smises.save(smisEDDS4);
            System.out.println("smisEDDS4");


            //
            SmisEntity smisEDDS5 = new SmisEntity();
            smisEDDS5.setName("ЕДДС5");
            smisEDDS5.setParentSmis(smisSUKS2);
            smisEDDS5.setDateRegistration(new Date());
            smisEDDS5.setAgreement("соглашение");

            smisEDDS5.setRegion(regionEntity);
            Set<ContactEntity> contacts = new HashSet<ContactEntity>(){{
                add(new ContactEntity(smisEDDS5, "Петр Петрович", "главный главный", "8(191)0000000"));
                add(new ContactEntity(smisEDDS5, "Петр Василич", "не главный", "8(191)02342"));
                add(new ContactEntity(smisEDDS5, "Петр Игорь", "забавный", "8(191)9898989"));
            }};
            smisEDDS5.setContacts(contacts);
            smisEDDS5.setValidity(new Date());
            smises.save(smisEDDS5);
            System.out.println("smisEDDS5");*/

//            smisService.init();

            //

        } catch (final Exception err) {
            System.out.println(err);
        }

    }


}
