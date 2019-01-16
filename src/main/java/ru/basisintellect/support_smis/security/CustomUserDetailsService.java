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
import ru.basisintellect.support_smis.entities.UserRole;
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

        ru.basisintellect.support_smis.entities.User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

        user.setLastDate(new Date());
        userRepository.save(user);

        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(ru.basisintellect.support_smis.entities.User user,
                                            List<GrantedAuthority> authorities) {


        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(UserRole userRole) {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);

            authorities.add(new SimpleGrantedAuthority(userRole.getUserRoleName()));


        return authorities;
    }

}