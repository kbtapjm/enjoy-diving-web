package kr.co.pjm.diving.web.common.security.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import kr.co.pjm.diving.web.common.security.social.SocialUserDetail;

@Component
public class CertificationService {
  
  public static String getId() {
    kr.co.pjm.diving.common.domain.entity.User user = CertificationService.getUser();
    
    return String.valueOf(user.getId());
  }
  
  public static String getEmail() {
    kr.co.pjm.diving.common.domain.entity.User user = CertificationService.getUser();
    
    return user.getEmail();
  }
  
  public static kr.co.pjm.diving.common.domain.entity.User getUser() {
    SocialUserDetail socialUserDetail = (SocialUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
    return socialUserDetail.getUser();
  }

}
