package ru.basisintellect.support_smis.dbloader;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.basisintellect.support_smis.entities.Smis;
import ru.basisintellect.support_smis.entities.User;
import ru.basisintellect.support_smis.entities.UserRole;
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

            UserRole adminRole = new UserRole();
            adminRole.setUserRoleName("ROLE_ADMIN");
            userRoleRepository.save(adminRole);

            User admin = new User();

            admin.setPassword(bcryptEncoder.encode("admin"));
            admin.setMail("admin@list.ru");
            admin.setFirstName("Foo");
            admin.setLastName("Bar");
            admin.setDateRegistration(new Date());
            ;
            admin.setUserRole(adminRole);
            admin.setEnabled(true);
            userRepository.save(admin);



            UserRole userRole = new UserRole();
            userRole.setUserRoleName("ROLE_USER");
            userRoleRepository.save(userRole);

            User user1 = new User();

            user1.setPassword(bcryptEncoder.encode("user"));
            user1.setMail("user@list.ru");
            user1.setFirstName("John");
            user1.setLastName("Doe");
            user1.setUserRole(userRole);
            user1.setEnabled(true);
            user1.setDateRegistration(new Date());
            userRepository.save(user1);

            //Добавление СМИСа для тестов
            Smis smis = new Smis();
            smis.setRegion("Мост тура");
            smis.setDateRegistration(new Date());
            smis.setAgreement("соглашение");
            smis.setValidity("11.12.2019");
            smis.setContacts("8(919)7658734");
            smis.setUrl("http://10.1.103.34:8080/monitoring/node/dispatch");
            smises.save(smis);
            smisService.init();
            //

        }  catch (final Exception err) {
            System.out.println(err);
    }

    }
}
