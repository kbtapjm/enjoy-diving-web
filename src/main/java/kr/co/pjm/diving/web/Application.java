package kr.co.pjm.diving.web;

import java.nio.charset.Charset;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.Compression;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web
 * @Class Name : Application.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 3.
 * @Version : 1.0
 * @Description : Spring Boot Application
 *
 */
@SpringBootApplication(scanBasePackages = {"kr.co.pjm.diving.web", "kr.co.pjm.diving.common"})
@EnableJpaRepositories(basePackages = {"kr.co.pjm.diving.common.repository"})
@EntityScan( basePackages = {"kr.co.pjm.diving.common.domain"} )
public class Application extends SpringBootServletInitializer implements CommandLineRunner {
  
  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  /**
   * Create a deployable war file
   */
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    builder.sources(Application.class);
    return builder;
  }
  
  /**
   * Enable HTTP response compression(HTTP 응답 압축 사용)
   * https://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-servlet-containers.html
   * 
   * @return
   * @throws Exception
   */
  @Bean
  public EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {
    return (ConfigurableEmbeddedServletContainer container) -> {
      if (container instanceof TomcatEmbeddedServletContainerFactory) {
        Compression compression = new Compression();
        compression.setEnabled(true);
        compression.setMinResponseSize(2048);   // 최소 2048
        container.setCompression(compression);
        
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/notFound"));
        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/serverError"));
      }
    };
  }
  
  @Bean
  public HttpMessageConverter<String> responseBodyConverter() {
    return new StringHttpMessageConverter(Charset.forName("UTF-8"));
  }
  
  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setForceEncoding(true);
    characterEncodingFilter.setEncoding("UTF-8");

    registrationBean.setFilter(characterEncodingFilter);

    return registrationBean;
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  @Override
  public void run(String... args) throws Exception {
    // CommandLineRunner
  }

}
