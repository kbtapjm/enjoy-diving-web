package kr.co.pjm.diving.web.common.security.social;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocialUsersDetailService implements SocialUserDetailsService {
  
  private UserDetailsService userDetailsService;

  public SocialUsersDetailService(UserDetailsService userDetailsService) {
      this.userDetailsService = userDetailsService;
  }

  @Override
  public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
    UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
    
    log.debug("==> 555555555555 : {}", userDetails);
    
    return (SocialUserDetail) userDetails;
  }

}
