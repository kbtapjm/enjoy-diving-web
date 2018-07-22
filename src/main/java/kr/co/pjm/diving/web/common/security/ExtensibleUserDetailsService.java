package kr.co.pjm.diving.web.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pjm.diving.web.common.security.social.SocialUserDetail;
import kr.co.pjm.diving.web.domain.entity.User;
import kr.co.pjm.diving.web.domain.entity.UserRole;
import kr.co.pjm.diving.web.repasitory.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.common.security
 * @Class Name : ExtensibleUserDetailsService.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 6.
 * @Version : 1.0
 * @Description : 스프링 시큐리티 인증 처리
 * 
 * <Reference>
 * http://www.baeldung.com/role-and-privilege-for-spring-security-registration
 * https://hellokoding.com/registration-and-login-example-with-spring-xml-configuration-maven-jsp-and-mysql/
 * http://libqa.com/wiki/731
 */
@Slf4j
@Component
public class ExtensibleUserDetailsService implements UserDetailsService {
  
  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public SocialUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
    if (log.isDebugEnabled()) {
      log.debug("==> username : {}", username);
    }
    
    /* find by user */
    User user = userRepository.findByEmail(username);
    
    if (null == user) {
      throw new UsernameNotFoundException("Not found username: " + username);
    }
    
    /* role set */
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
    Iterator<UserRole> itr = user.getUserRoles().iterator();
    while (itr.hasNext()) {
      UserRole userRole = itr.next();
      
      authorities.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
    }
    
    /*UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
        user.getEmail(), user.getPassword(), this.getAuthorities(authorities));*/
    
    SocialUserDetail socialUserDetail = new SocialUserDetail(user);
    
    return socialUserDetail;
  }
  
  public Collection<? extends GrantedAuthority> getAuthorities(List<GrantedAuthority> authorities) {
    return Collections.unmodifiableList(authorities);
  }

}
