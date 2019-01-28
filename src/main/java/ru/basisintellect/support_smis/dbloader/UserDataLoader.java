package ru.basisintellect.support_smis.dbloader;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.basisintellect.support_smis.model.entities.*;
import ru.basisintellect.support_smis.repositories.*;
import ru.basisintellect.support_smis.services.SmisService;


import java.util.Date;


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
    SmisService smisService;

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
            stateUnSuccess.setId((long) 2);

            StateEntity stateSuccess = new StateEntity();
            stateSuccess.setStateName("РАБОТАЕТ");
            stateSuccess.setId((long) 1);

            StateEntity stateOffline = new StateEntity();
            stateOffline.setStateName("НЕТ КОМПЛЕКСА");
            stateOffline.setId((long) 0);

            stateRepository.save(stateUnSuccess);
            stateRepository.save(stateSuccess);
            stateRepository.save(stateOffline);
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
            SmisEntity smisNSUKS = new SmisEntity();

            ContactEntity contactEntity1 = new ContactEntity();
            contactEntity1.setFonNumber("8(909)000000000");
            contactEntity1.setName("Петр Петров");
            contactEntity1.setPosition("главный босс");
            contactEntity1.setSmisEntity(smisNSUKS);

            smisNSUKS.setName("НЦУКС");
            smisNSUKS.setDateRegistration(new Date());
            smisNSUKS.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());

            smisNSUKS.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisNSUKS);

            SmisEntity smisSUKS1 = new SmisEntity();

            ContactEntity contactEntity2 = new ContactEntity();
            contactEntity2.setFonNumber("8(909)1111111");
            contactEntity2.setName("Иван Иванов");
            contactEntity2.setPosition("зам главного босса");
            contactEntity2.setSmisEntity(smisSUKS1);

            smisSUKS1.setName("ЦУКС1");
            smisSUKS1.setParentSmis(smisNSUKS);
            smisSUKS1.setDateRegistration(new Date());
            smisSUKS1.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisSUKS1.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisSUKS1);


            SmisEntity smisSUKS2 = new SmisEntity();

            ContactEntity contactEntity3 = new ContactEntity();
            contactEntity3.setFonNumber("8(909)2222222");
            contactEntity3.setName("Вася Пупкин");
            contactEntity3.setPosition("начальник отдела");
            contactEntity3.setSmisEntity(smisSUKS2);

            smisSUKS2.setName("ЦУКС2");
            smisSUKS2.setParentSmis(smisNSUKS);
            smisSUKS2.setDateRegistration(new Date());
            smisSUKS2.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisSUKS2.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisSUKS2);


            SmisEntity smisEDDS1 = new SmisEntity();

            ContactEntity contactEntity4 = new ContactEntity();
            contactEntity4.setFonNumber("8(909)3333333");
            contactEntity4.setName("Акакий Акакиевич");
            contactEntity4.setPosition("главный инженер");
            contactEntity4.setSmisEntity(smisEDDS1);

            smisEDDS1.setName("ЕДДС1");
            smisEDDS1.setParentSmis(smisSUKS1);
            smisEDDS1.setDateRegistration(new Date());
            smisEDDS1.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisEDDS1.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisEDDS1);

            SmisEntity smisEDDS2 = new SmisEntity();

            ContactEntity contactEntity5 = new ContactEntity();
            contactEntity5.setFonNumber("8(909)4444444");
            contactEntity5.setName("Василий Васильев");
            contactEntity5.setPosition("главный технолог");
            contactEntity5.setSmisEntity(smisEDDS2);

            smisEDDS2.setName("ЕДДС2");
            smisEDDS2.setParentSmis(smisSUKS1);
            smisEDDS2.setDateRegistration(new Date());
            smisEDDS2.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisEDDS2.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisEDDS2);

            SmisEntity smisEDDS3 = new SmisEntity();


            smisEDDS3.setName("ЕДДС3");
            smisEDDS3.setParentSmis(smisSUKS1);
            smisEDDS3.setDateRegistration(new Date());
            smisEDDS3.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisEDDS3.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisEDDS3);

            SmisEntity smisEDDS4 = new SmisEntity();



            smisEDDS4.setName("ЕДДС4");
            smisEDDS4.setParentSmis(smisSUKS2);
            smisEDDS4.setDateRegistration(new Date());
            smisEDDS4.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisEDDS4.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisEDDS4);

            SmisEntity smisEDDS5 = new SmisEntity();

            ContactEntity contactEntity8 = new ContactEntity();
            contactEntity8.setFonNumber("8(909)7777777");
            contactEntity8.setName("Павел Петрович");
            contactEntity8.setPosition("главный главный главный");

            ContactEntity contactEntity7 = new ContactEntity();
            contactEntity7.setFonNumber("8(909)6666666");
            contactEntity7.setName("Павел Павлович");
            contactEntity7.setPosition("главный главный");

            ContactEntity contactEntity6 = new ContactEntity();
            contactEntity6.setFonNumber("8(909)5555555");
            contactEntity6.setName("Игорь Игоревич");
            contactEntity6.setPosition("главный технолог");

            contactEntity8.setSmisEntity(smisEDDS5);
            contactEntity7.setSmisEntity(smisEDDS5);
            contactEntity6.setSmisEntity(smisEDDS5);

            smisEDDS5.setName("ЕДДС5");
            smisEDDS5.setParentSmis(smisSUKS2);
            smisEDDS5.setDateRegistration(new Date());
            smisEDDS5.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisEDDS5.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisEDDS5);

            smisService.init();

            //

        }  catch (final Exception err) {
            System.out.println(err);
    }

    }
}
