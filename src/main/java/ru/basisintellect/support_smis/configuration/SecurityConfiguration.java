package ru.basisintellect.support_smis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Created by safin.v on 17.10.2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*http
                .authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/signup").permitAll()
                .anyRequest().authenticated();*/

        http.csrf().disable();

        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

        // /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/smises/*").access("hasAnyRole('USER', 'ADMIN')");
        http.authorizeRequests().antMatchers("/users/*").access("hasAnyRole('USER', 'ADMIN')");

        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ADMIN')");

        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/login");

        http
                .formLogin().loginPage("/login").permitAll().usernameParameter("j_username").successForwardUrl("/scopeSession")
                .passwordParameter("j_password").loginProcessingUrl("/j_spring_security_check").failureUrl("/login?error=true")
                .and()
                .httpBasic()
                .and()
                .authorizeRequests().antMatchers("/private/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login")
                .and()
                .rememberMe().key("basis").tokenValiditySeconds(86400)
                .and()
                .csrf().disable();
    }

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(getBCryptPasswordEncoder());
    }

    @Bean(name = "bcryptEncoder")
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}