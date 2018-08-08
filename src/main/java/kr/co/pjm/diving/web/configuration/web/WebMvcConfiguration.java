package kr.co.pjm.diving.web.configuration.web;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import kr.co.pjm.diving.web.common.security.AuthAndDateAuditorAware;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.configuration.web
 * @Class Name : WebMvcConfiguration.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 4.
 * @Version : 1.0
 * @Description : WEB MVC 설정
 *
 */
@Configuration
@EnableJpaAuditing
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

  @Bean(name = "localeResolver")
  public LocaleResolver localeResolver() {
    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    sessionLocaleResolver.setDefaultLocale(Locale.KOREA);
    return sessionLocaleResolver;
  }
  
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("language");
    return localeChangeInterceptor;
  }
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }
  
  @Bean
  public AuthAndDateAuditorAware auditorAware() {
    return new AuthAndDateAuditorAware();
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
      .addMapping("/**")
      .allowedOrigins("*")
      .allowedMethods("POST", "GET",  "PUT", "DELETE")
      .allowedHeaders("Access-Control-Allow-Headers", "Content-Type", "x-requested-with")
      .allowCredentials(false)
      .maxAge(1);
  }
  
}
