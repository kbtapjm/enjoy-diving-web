package kr.co.pjm.diving.web.common.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import kr.co.pjm.diving.web.api.service.UserApiService;
import kr.co.pjm.diving.web.common.security.model.SocialUserDetail;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocialUsersDetailServiceImpl implements SocialUserDetailsService {
  
  @Autowired
  private UserApiService userApiService;
  
  private UserDetailsService userDetailsService;

  public SocialUsersDetailServiceImpl(UserDetailsService userDetailsService) {
      this.userDetailsService = userDetailsService;
  }

  @Override
  public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
    if (log.isDebugEnabled()) {
      log.debug(" === > SocialUsersDetailService userId : {}", userId);
    }
    
    UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
    
    SocialUserDetail socialUserDetail = (SocialUserDetail) userDetails;
    
    // update last login date
    userApiService.updateUserLoginDate(socialUserDetail.getUser().getId());
    
    // create login log
    userApiService.createUserLoginLog(socialUserDetail.getUser().getId());
    
    return socialUserDetail;
  }

}
