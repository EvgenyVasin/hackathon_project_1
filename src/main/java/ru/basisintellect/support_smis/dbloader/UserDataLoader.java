package ru.basisintellect.support_smis.dbloader;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.basisintellect.support_smis.entities.User;
import ru.basisintellect.support_smis.entities.UserRole;
import ru.basisintellect.support_smis.repositories.UserRoleRepository;
import ru.basisintellect.support_smis.repositories.UsersRepository;


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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {

            UserRole adminRole = new UserRole();
            adminRole.setUserRoleName("ROLE_ADMIN");
            userRoleRepository.save(adminRole);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(bcryptEncoder.encode("admin"));
            admin.setMail("123@list.ru");
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
            user1.setUsername("user");
            user1.setPassword(bcryptEncoder.encode("user"));
            user1.setMail("123@list.ru");
            user1.setFirstName("John");
            user1.setLastName("Doe");
            user1.setUserRole(userRole);
            user1.setEnabled(true);
            user1.setDateRegistration(new Date());
            userRepository.save(user1);

        }  catch (final Exception err) {
            System.out.println(err);
    }

    }
}
