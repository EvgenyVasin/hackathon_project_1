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
 * Настройка конфигурации безопасноти с полем <b>customUserDetailsService</b>
 * Created by safin.v on 17.10.2016.
 * @author Created by safin.v on 19.10.2016.
 * @version 0.1
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Поле пользовательского сервиса
     */
    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService customUserDetailsService;


    /**
     * Метод для конфигурации безопасности, добавление ключа шифрования паролей, страницы логина пользователя, время сессии
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .authorizeRequests().antMatchers("/console/**").permitAll().and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
        ;

        http.csrf().disable();

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/login");

        http
                .formLogin().loginPage("/login").permitAll().usernameParameter("j_username").successForwardUrl("/scopeSession")
                .passwordParameter("j_password").loginProcessingUrl("/j_spring_security_check").failureUrl("/login?error=true")
                .and()
                .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login")
                .and()
                .rememberMe().key("basis").tokenValiditySeconds(86400)
                .and()
                .csrf().disable();
    }

    /**
     * Конфигурация пользовательского сервиса
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(getBCryptPasswordEncoder());
    }

    /**
     * Раскодировщик паролей
     */
    @Bean(name = "bcryptEncoder")
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}