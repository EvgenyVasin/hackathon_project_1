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
            SmisEntity smisEntity = new SmisEntity();
            smisEntity.setRegion("Мост Тура");
            smisEntity.setDateRegistration(new Date());
            smisEntity.setAgreement("соглашение");
            smisEntity.setValidity("11.12.2019");
            smisEntity.setContacts("8(919)7658734");
            smisEntity.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smisEntity);
            smisService.init();

            //

        }  catch (final Exception err) {
            System.out.println(err);
    }

    }
}
