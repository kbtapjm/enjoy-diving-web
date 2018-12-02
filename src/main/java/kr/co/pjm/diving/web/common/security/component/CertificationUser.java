package kr.co.pjm.diving.web.common.security.component;

import org.springframework.security.core.context.SecurityContextHolder;

import kr.co.pjm.diving.web.common.security.model.SocialUserDetail;

public class CertificationUser {

  public static String getId() {
    kr.co.pjm.diving.common.domain.entity.User user = CertificationUser.getUser();
    
    return String.valueOf(user.getId());
  }
  
  public static Long getLongId() {
    kr.co.pjm.diving.common.domain.entity.User user = CertificationUser.getUser();
    
    return user.getId();
  }
  
  public static String getEmail() {
    kr.co.pjm.diving.common.domain.entity.User user = CertificationUser.getUser();
    
    return user.getEmail();
  }
  
  public static kr.co.pjm.diving.common.domain.entity.User getUser() {
    SocialUserDetail socialUserDetail = (SocialUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
    return socialUserDetail.getUser();
  }
  
}
