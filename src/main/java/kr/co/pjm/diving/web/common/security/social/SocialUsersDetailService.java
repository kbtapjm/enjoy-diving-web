package kr.co.pjm.diving.web.common.security.social;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

public class SocialUsersDetailService implements SocialUserDetailsService {
  
  private UserDetailsService userDetailsService;

  public SocialUsersDetailService(UserDetailsService userDetailsService) {
      this.userDetailsService = userDetailsService;
  }

  @Override
  public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
    UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
    
    return (SocialUserDetail) userDetails;
  }

}
