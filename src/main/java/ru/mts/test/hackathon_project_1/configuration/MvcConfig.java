package ru.mts.test.hackathon_project_1.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * Конфигурация и настройка html компонентов из папки проекта <b>templates</b> с константой <b>CLASSPATH_RESOURCE_LOCATIONS</b>
 * @version 0.1
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * Константа путь расположения ресурсов
     */
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"};

    /**
     * Добавление ресурсов из <b>CLASSPATH_RESOURCE_LOCATIONS</b>
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }
    }

//    @Bean(name="simpleMappingExceptionResolver")
//    public SimpleMappingExceptionResolver
//    createSimpleMappingExceptionResolver() {
//        SimpleMappingExceptionResolver r =
//            new SimpleMappingExceptionResolver();
//
//        Properties mappings = new Properties();
//        mappings.setProperty("/error", "error/error");
//        mappings.setProperty("InvalidCreditCardException", "creditCardError");
//
//        r.setExceptionMappings(mappings);  // None by default
//        r.setDefaultErrorView("error/error");    // No default
//        r.setExceptionAttribute("ex");     // Default is "exception"
//        r.setWarnLogCategory("example.MvcLogger");     // No default
//        return r;
//    }

    /**
     * Регистрация в контроллере страниц из <b>templates</b>
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("login");
        //registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/profile").setViewName("users/profile");
        registry.addViewController("/info").setViewName("info");
        registry.addViewController("/smises_table").setViewName("smises/smises_table");
        registry.addViewController("/smises_list").setViewName("smises/smises_list");
        registry.addViewController("/add_smis").setViewName("smises/add_smis");
        registry.addViewController("/smis_details").setViewName("smises/smis_details");
    }

    /**
     * <mvc:interceptors> Добавление <b>Interceptor</b>
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLocaleChangeInterceptor()).addPathPatterns("/*");
    }

    /**
     * <mvc:interceptors> Получение <b>Interceptor</b>
     */
    @Bean(name = "localeChangeInterceptor")
    public LocaleChangeInterceptor getLocaleChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("languageVar");
        return localeChangeInterceptor;
    }

    /**
     * <bean id="localeResolver" class="org.springframework.web.servlet.i18n.CookieLocaleResolver">
     */
    @Bean(name = "localeResolver")
    public CookieLocaleResolver getLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("ru"));
        cookieLocaleResolver.setCookieMaxAge(100000);
        return cookieLocaleResolver;
    }

    /**
     * Подключение кодировки UTF-8
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }

    /**
     * <bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
     * Настройка Spring класса ReloadableResourceBundleMessageSource
     */
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:/locales/messages");
        resource.setCacheSeconds(1);
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }
    // В качестве альтернативы автоматической загрузке
        /*@Bean
        public ResourceBundleMessageSource messageSource() {
                ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
                messageSource.setBasename("classpath:locales/messages");
                return messageSource;
        }*/
}