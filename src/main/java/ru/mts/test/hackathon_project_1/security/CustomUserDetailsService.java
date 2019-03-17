package ru.mts.test.hackathon_project_1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mts.test.hackathon_project_1.model.entities.UserEntity;
import ru.mts.test.hackathon_project_1.model.entities.UserRoleEntity;
import ru.mts.test.hackathon_project_1.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserEntity user = userRepository.findByMail(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

        user.setLastDate(new Date());
        userRepository.save(user);

        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(UserEntity userEntity,
                                            List<GrantedAuthority> authorities) {


        return new User(userEntity.getMail(), userEntity.getPassword(),
                userEntity.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(UserRoleEntity userRoleEntity) {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);

        authorities.add(new SimpleGrantedAuthority(userRoleEntity.getUserRoleName()));


        return authorities;
    }

}