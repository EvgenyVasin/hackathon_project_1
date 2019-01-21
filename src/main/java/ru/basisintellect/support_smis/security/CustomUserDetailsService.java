package ru.basisintellect.support_smis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.basisintellect.support_smis.model.entities.UserEntity;
import ru.basisintellect.support_smis.model.entities.UserRoleEntity;
import ru.basisintellect.support_smis.repositories.UsersRepository;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by safin.v on 24.10.2016.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByMail(username);
        List<GrantedAuthority> authorities = buildUserAuthority(userEntity.getUserRoleEntity());

        userEntity.setLastDate(new Date());
        userRepository.save(userEntity);

        return buildUserForAuthentication(userEntity, authorities);
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