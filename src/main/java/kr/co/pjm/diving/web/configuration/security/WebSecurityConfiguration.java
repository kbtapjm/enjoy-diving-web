package kr.co.pjm.diving.web.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import kr.co.pjm.diving.web.common.security.ExtensibleUserDetailsService;
import kr.co.pjm.diving.web.common.security.social.SocialUsersDetailService;
import kr.co.pjm.diving.web.configuration.security.social.redis.EnableEmbeddedRedis;
import kr.co.pjm.diving.web.configuration.security.social.redis.RedisServerPort;

@Configuration
@EnableEmbeddedRedis
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private ExtensibleUserDetailsService extensibleUserDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers(
            "/auth/**",
            "/login", 
            "/login/authenticate", 
            "/signup", 
            "/error/accessDenied",
            "/story/**",
            "/")
        .permitAll()
        .antMatchers("/h2console/**").hasAnyAuthority("ADMIN")
        .anyRequest().authenticated()
      .and()
        .headers()
        .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"))
      .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login/authenticate")
        .usernameParameter("email")
        .passwordParameter("password")
        .defaultSuccessUrl("/")
        .failureUrl("/login?error=failure")
      .and()
        .logout()
        .logoutUrl("/signOut")
        .deleteCookies("SESSION")
        .logoutSuccessUrl("/")
//      .and()
//        .httpBasic()
      .and()
        .csrf().disable()
        .headers().frameOptions().disable()
      .and()
        .apply(new SpringSocialConfigurer());
    
/*    http
      .sessionManagement()
      .maximumSessions(1)
      .maxSessionsPreventsLogin(false) 
      .expiredUrl("/login?error=expired")
      .sessionRegistry(sessionRegistry());*/
    
    http
    .exceptionHandling()
      .accessDeniedPage("/error/accessDenied");
    
//    http
//      .addFilterBefore(new SessionTimeoutFilter(), BasicAuthenticationFilter.class)
//      .addFilterAfter(new CORSFilter(), SessionTimeoutFilter.class);
  }
  
  @Bean
  public SocialUserDetailsService socialUsersDetailService() {
      return new SocialUsersDetailService(extensibleUserDetailsService);
  }
  
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }
  
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
      DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
      daoAuthenticationProvider.setUserDetailsService(extensibleUserDetailsService);
      daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
      daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
      return daoAuthenticationProvider;
  }
  
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/static/**");
  }
 
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Override
  protected ExtensibleUserDetailsService userDetailsService() {
      return extensibleUserDetailsService;
  }
  
  @Bean
  public JedisConnectionFactory connectionFactory(@RedisServerPort int port) {
      JedisConnectionFactory connection = new JedisConnectionFactory();
      connection.setPort(port);
      return connection;
  }

}
