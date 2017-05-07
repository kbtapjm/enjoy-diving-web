package kr.co.pjm.diving.web.configuration.web;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

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
  
}
