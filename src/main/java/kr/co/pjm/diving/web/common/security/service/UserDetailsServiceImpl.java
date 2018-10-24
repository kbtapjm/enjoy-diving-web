package kr.co.pjm.diving.web.common.security.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.common.domain.entity.UserRole;
import kr.co.pjm.diving.common.repository.UserRepository;
import kr.co.pjm.diving.web.common.security.model.SocialUserDetail;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
  
  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public SocialUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
    if (log.isDebugEnabled()) {
      log.debug("==> username : {}", username);
    }
    
    User user = userRepository.findByEmail(username);
    if (null == user) {
      throw new UsernameNotFoundException("Not found username: " + username);
    }
    
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
    Iterator<UserRole> itr = user.getUserRoles().iterator();
    while (itr.hasNext()) {
      UserRole userRole = itr.next();
      
      authorities.add(new SimpleGrantedAuthority(userRole.getRole().getRole().name()));
    }
    
    SocialUserDetail socialUserDetail = new SocialUserDetail(user);
    socialUserDetail.setAuthorities(authorities);
    
    return socialUserDetail;
  }

}
