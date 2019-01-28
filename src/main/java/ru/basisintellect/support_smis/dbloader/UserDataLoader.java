package ru.basisintellect.support_smis.dbloader;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.basisintellect.support_smis.model.entities.SmisEntity;
import ru.basisintellect.support_smis.model.entities.UserEntity;
import ru.basisintellect.support_smis.model.entities.UserRoleEntity;
import ru.basisintellect.support_smis.repositories.SmisRepository;
import ru.basisintellect.support_smis.repositories.UserRoleRepository;
import ru.basisintellect.support_smis.repositories.UsersRepository;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {

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
            smisNSUKS.setName("НЦУКС");
            smisNSUKS.setDateRegistration(new Date());
            smisNSUKS.setAgreement("соглашение");
            smisNSUKS.setValidity("11.12.2019");
            smisNSUKS.setContacts("8(919)7658734");
            smisNSUKS.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisNSUKS);

            SmisEntity smisSUKS1 = new SmisEntity();
            smisSUKS1.setName("ЦУКС1");
            smisSUKS1.setParentSmis(smisNSUKS);
            smisSUKS1.setDateRegistration(new Date());
            smisSUKS1.setAgreement("соглашение");
            smisSUKS1.setValidity("11.12.2019");
            smisSUKS1.setContacts("8(919)7658734");
            smisSUKS1.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisSUKS1);


            SmisEntity smisSUKS2 = new SmisEntity();
            smisSUKS2.setName("ЦУКС2");
            smisSUKS2.setParentSmis(smisNSUKS);
            smisSUKS2.setDateRegistration(new Date());
            smisSUKS2.setAgreement("соглашение");
            smisSUKS2.setValidity("11.12.2019");
            smisSUKS2.setContacts("8(919)7658734");
            smisSUKS2.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisSUKS2);


            SmisEntity smisEDDS1 = new SmisEntity();
            smisEDDS1.setName("ЕДДС1");
            smisEDDS1.setParentSmis(smisSUKS1);
            smisEDDS1.setDateRegistration(new Date());
            smisEDDS1.setAgreement("соглашение");
            smisEDDS1.setValidity("11.12.2019");
            smisEDDS1.setContacts("8(919)7658734");
            smisEDDS1.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisEDDS1);

            SmisEntity smisEDDS2 = new SmisEntity();
            smisEDDS2.setName("ЕДДС2");
            smisEDDS2.setParentSmis(smisSUKS1);
            smisEDDS2.setDateRegistration(new Date());
            smisEDDS2.setAgreement("соглашение");
            smisEDDS2.setValidity("11.12.2019");
            smisEDDS2.setContacts("8(919)7658734");
            smisEDDS2.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisEDDS2);

            SmisEntity smisEDDS3 = new SmisEntity();
            smisEDDS3.setName("ЕДДС3");
            smisEDDS3.setParentSmis(smisSUKS1);
            smisEDDS3.setDateRegistration(new Date());
            smisEDDS3.setAgreement("соглашение");
            smisEDDS3.setValidity("11.12.2019");
            smisEDDS3.setContacts("8(919)7658734");
            smisEDDS3.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisEDDS3);

            SmisEntity smisEDDS4 = new SmisEntity();
            smisEDDS4.setName("ЕДДС4");
            smisEDDS4.setParentSmis(smisSUKS2);
            smisEDDS4.setDateRegistration(new Date());
            smisEDDS4.setAgreement("соглашение");
            smisEDDS4.setValidity("11.12.2019");
            smisEDDS4.setContacts("8(919)7658734");
            smisEDDS4.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisEDDS4);

            SmisEntity smisEDDS5 = new SmisEntity();
            smisEDDS5.setName("ЕДДС5");
            smisEDDS5.setParentSmis(smisSUKS2);
            smisEDDS5.setDateRegistration(new Date());
            smisEDDS5.setAgreement("соглашение");
            smisEDDS5.setValidity("11.12.2019");
            smisEDDS5.setContacts("8(919)7658734");
            smisEDDS5.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisEDDS5);

            smisService.init();

            //

        }  catch (final Exception err) {
            System.out.println(err);
    }

    }
}
