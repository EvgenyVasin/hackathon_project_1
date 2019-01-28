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
            final SmisEntity smisNSUKS = new SmisEntity();
            smisNSUKS.setName("НЦУКС");
            smisNSUKS.setDateRegistration(new Date());
            smisNSUKS.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisNSUKS.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");

            final SmisEntity smisSUKS1 = new SmisEntity();
            smisSUKS1.setName("ЦУКС1");
            smisSUKS1.setParentSmis(smisNSUKS);
            smisSUKS1.setDateRegistration(new Date());
            smisSUKS1.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisSUKS1.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");


            final SmisEntity smisSUKS2 = new SmisEntity();
            smisSUKS2.setName("ЦУКС2");
            smisSUKS2.setParentSmis(smisNSUKS);
            smisSUKS2.setDateRegistration(new Date());
            smisSUKS2.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisSUKS2.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");

            final SmisEntity smisEDDS1 = new SmisEntity();
            smisEDDS1.setName("ЕДДС1");
            smisEDDS1.setParentSmis(smisSUKS1);
            smisEDDS1.setDateRegistration(new Date());
            smisEDDS1.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisEDDS1.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");

            final SmisEntity smisEDDS2 = new SmisEntity();
            smisEDDS2.setName("ЕДДС2");
            smisEDDS2.setParentSmis(smisSUKS1);
            smisEDDS2.setDateRegistration(new Date());
            smisEDDS2.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisEDDS2.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");

            final SmisEntity smisEDDS3 = new SmisEntity();
            smisEDDS3.setName("ЕДДС3");
            smisEDDS3.setParentSmis(smisSUKS1);
            smisEDDS3.setDateRegistration(new Date());
            smisEDDS3.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisEDDS3.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");

            final SmisEntity smisEDDS4 = new SmisEntity();
            smisEDDS4.setName("ЕДДС4");
            smisEDDS4.setParentSmis(smisSUKS2);
            smisEDDS4.setDateRegistration(new Date());
            smisEDDS4.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisEDDS4.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");

            //
            final SmisEntity smisEDDS5 = new SmisEntity();

                    Set contacts = new HashSet<ContactEntity>(){{
                        add(new ContactEntity(smisEDDS5, "Петр Петрович", "главный главный", "8(191)0000000"));
                        add(new ContactEntity(smisEDDS5, "Петр Василич", "не главный", "8(191)02342"));
                        add(new ContactEntity(smisEDDS5, "Петр Игорь", "забавный", "8(191)9898989"));
                    }};

                   smisEDDS5.setContacts(contacts);

            smisEDDS5.setName("ЕДДС5");
            smisEDDS5.setParentSmis(smisSUKS2);
            smisEDDS5.setDateRegistration(new Date());
            smisEDDS5.setAgreement("соглашение");
            smisNSUKS.setState(stateRepository.findById((long) 1).get());
            smisEDDS5.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");

            final Set<SmisEntity> setSmises = new HashSet<SmisEntity>();
            setSmises.add(smisNSUKS);
            setSmises.add(smisSUKS1);
            setSmises.add(smisSUKS2);
            setSmises.add(smisEDDS1);
            setSmises.add(smisEDDS2);
            setSmises.add(smisEDDS3);
            setSmises.add(smisEDDS4);
            setSmises.add(smisEDDS5);

            for (SmisEntity smis: setSmises
                 ) {
                smises.save(smis);
            }

            smisService.init();

            //

        }  catch (final Exception err) {
            System.out.println(err);
    }

    }
}
