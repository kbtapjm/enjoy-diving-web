package kr.co.pjm.diving.web.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import kr.co.pjm.diving.web.common.security.ExtensibleUserDetailsService;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.configuration.security
 * @Class Name : WebSecurityConfig.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 3.
 * @Version : 1.0
 * @Description : 웹 보안 설정
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private ExtensibleUserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/signIn", "/login", "/users/new").permitAll()
        .antMatchers(HttpMethod.POST, "/users", "/login").permitAll()
        .antMatchers("/h2console/**").hasAnyAuthority("ADMIN")
        .anyRequest().authenticated()
      .and()
        .headers()
        .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"))
//      .and()
//        .formLogin()
//        .loginPage("/login")
//        .loginProcessingUrl("/signIn")
//        .usernameParameter("email")
//        .passwordParameter("password")
//        .defaultSuccessUrl("/")
//        .failureUrl("/login?error=failure")
      .and()
        .logout()
        .logoutUrl("/signOut")
        .logoutSuccessUrl("/login")
      .and()
        .httpBasic()
      .and()
        .csrf().disable();
    
    http
      .sessionManagement()
      .maximumSessions(1)
      .maxSessionsPreventsLogin(false) 
      .expiredUrl("/login?error=expired")
      .sessionRegistry(sessionRegistry());
    
    http
    .exceptionHandling()
      .accessDeniedPage("/accessDenied");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
  }
  
  @Override
  protected UserDetailsService userDetailsService() {
    return userDetailsService;
  }
  
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/static/**");
  }
 
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  @Bean
  public SessionRegistry sessionRegistry() {
    SessionRegistry sessionRegistry = new SessionRegistryImpl();
    return sessionRegistry;
  }

}
