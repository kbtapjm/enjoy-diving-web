package kr.co.pjm.diving.web.configuration.security.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

@Configuration
@EnableSocial
public class SocialConfiguration extends SocialConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Override
  public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
    GoogleConnectionFactory googleConnectionFactory = new GoogleConnectionFactory(
        environment.getProperty("spring.social.google.app-id"),
        environment.getProperty("spring.social.google.app-secret"));
    
    connectionFactoryConfigurer.addConnectionFactory(googleConnectionFactory);
  }

  @Override
  public UserIdSource getUserIdSource() {
    return new AuthenticationNameUserIdSource();
  }

  @Override
  public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
    JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator,
        Encryptors.noOpText());
    return repository;
  }

  @Bean
  public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator,
      ConnectionRepository connectionRepository) {
    return new ConnectController(connectionFactoryLocator, connectionRepository);
  }

  @Bean
  public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator,
      UsersConnectionRepository usersConnectionRepository) {
    return new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository);
  }

}
