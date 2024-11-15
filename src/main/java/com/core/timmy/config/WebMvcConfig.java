package com.core.timmy.config;

import java.util.Locale;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration


public class WebMvcConfig implements WebMvcConfigurer, ApplicationContextAware { //Para los métodos del Dialect Layout hay que implementar ApplicationContextAware
  
   // Bean to change Locale by default:
    @Bean
    LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
      //  sessionLocaleResolver.setDefaultLocale(Locale.of("es", "ES"));
        return sessionLocaleResolver;
    }

    // Bean to apply an interceptor for changing Locale from web pages:
    @Bean
    LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
	    localeChangeInterceptor.setIgnoreInvalidLocale(true);

        return localeChangeInterceptor;
    }
   
    // Bean to allow changing languages (implementing WebMvcConfigurer):
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
   
    // Bean to allow using message_xx.properties:
    @Bean
    ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
        rbms.setBasename("i18n/message");
        //rbms.setBasenames("i18n/message", "i18n/format");
        rbms.setDefaultEncoding("UTF-8");
        return rbms;
    }

    // Los siguientes 3 métodos sirven para usar la estrategia Dialect Layout de Thymeleaf,
    // por lo que se pueden comentar si no se van a necesitar.
    //
    private ApplicationContext applicationContext; // Atributo que necesitan estos métodos
   //
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }    

    // 1/3: Next three Beans allow to include the Thymeleaf's layout dialect:
    @Bean
    ISpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setTemplateResolver(htmlTemplateResolver());
        engine.addDialect(new LayoutDialect());
        engine.addDialect(new SpringSecurityDialect());
        //
        log.info("Dialects & TemplateResolvers: "
                + " > " + engine.getDialects()
                + " >> " + engine.getTemplateResolvers()
                + " >>> " + engine.getMessageResolvers().size()
                );
        //
        return engine;
    }
    // 2/3
    @Bean
    ITemplateResolver htmlTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("classpath:templates/");// resolver.setPrefix("classpath:templates/");
        resolver.setSuffix(".html");
        // resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCacheable(false);
        return resolver;
    }
    // 3/3
    @Bean
    ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

}
