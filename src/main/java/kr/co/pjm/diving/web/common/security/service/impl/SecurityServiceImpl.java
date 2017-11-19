package kr.co.pjm.diving.web.common.security.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import kr.co.pjm.diving.web.common.security.service.SecurityService;
import kr.co.pjm.diving.web.domain.dto.LoginDto;
import kr.co.pjm.diving.web.domain.entity.UserRole;
import kr.co.pjm.diving.web.repasitory.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.common.security.service.impl
 * @Class Name : SecurityServiceImpl.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 12.
 * @Version : 1.0
 * @Description : 스프링 시큐리티 서비스 구현 클래스 
 *
 */
@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {
  
  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails login(LoginDto loginDto) {
    
    /* User select */
    kr.co.pjm.diving.web.domain.entity.User user = userRepository.findByEmail(loginDto.getEmail());
    
    /* ROLE set */
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
    Iterator<UserRole> itr = user.getUserRoles().iterator();
    while (itr.hasNext()) {
      UserRole userRole = itr.next();
      
      authorities.add(new SimpleGrantedAuthority(userRole.getRole().toString()));
    }
    
    UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
        user.getEmail(), user.getPassword(), authorities);
    
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, loginDto.getPassword(), userDetails.getAuthorities());

    authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    if (usernamePasswordAuthenticationToken.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      log.debug(String.format("Auto login %s successfully!", loginDto.getEmail()));
    }
    
    return userDetails;
  }

  @Override
  public String getLoginUser() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
    if (user instanceof User) {
      return user.getUsername();
    }
    
    return null;
  }

}
