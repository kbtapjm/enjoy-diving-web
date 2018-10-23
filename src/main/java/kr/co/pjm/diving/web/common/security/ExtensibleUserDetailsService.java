package kr.co.pjm.diving.web.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.common.repository.UserRepository;
import kr.co.pjm.diving.web.api.service.UserApiService;
import kr.co.pjm.diving.web.common.security.social.SocialUserDetail;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExtensibleUserDetailsService implements UserDetailsService {
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private UserApiService userApiService;

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
    
    //userApiService.updateUserLoginDate(user.getId());
    
    SocialUserDetail socialUserDetail = new SocialUserDetail(user);
    
    return socialUserDetail;
  }


}
