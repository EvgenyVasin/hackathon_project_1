package ru.basisintellect.support_smis.dbloader;



import com.sun.org.apache.xml.internal.resolver.helpers.Debug;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.basisintellect.support_smis.model.entities.*;
import ru.basisintellect.support_smis.repositories.*;
//import ru.basisintellect.support_smis.services.SmisService;


import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {



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

            //

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

            userEntity1.setPassword(bcryptEncoder.encode("user"));
            userEntity1.setMail("user@list.ru");
            userEntity1.setFirstName("John");
            userEntity1.setLastName("Doe");
            userEntity1.setUserRole(userRole);
            userEntity1.setEnabled(true);
            userEntity1.setDateRegistration(new Date());
            userRepository.save(userEntity1);

            //Добавление СМИСа для тестов

            //удаление папки с файлами НА ВРЕМЯ ОТЛАДКИ
            FileUtils.deleteDirectory(new File("smis_files"));

            RegionEntity regionEntity = new RegionEntity("Центральный");
            regionRepository.save(regionEntity);

            SmisEntity smisNSUKS = new SmisEntity();

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
            /*smisNSUKS.setDescription("aaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");*/
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
            System.out.println("smisEDDS5");

//            smisService.init();

            //

        }  catch (final Exception err) {
            System.out.println(err);
    }

    }
}
