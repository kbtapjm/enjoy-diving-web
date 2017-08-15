package kr.co.pjm.diving.web.common.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import kr.co.pjm.diving.web.common.security.service.SecurityService;
import kr.co.pjm.diving.web.domain.dto.LoginDto;
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
  private UserDetailsService userDetailsService;

  @Override
  public UserDetails login(LoginDto loginDto) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
    
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
