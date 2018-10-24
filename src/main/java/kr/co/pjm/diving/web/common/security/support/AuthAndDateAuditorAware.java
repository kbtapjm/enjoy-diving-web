package kr.co.pjm.diving.web.common.security.support;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import kr.co.pjm.diving.web.common.security.model.SocialUserDetail;

public class AuthAndDateAuditorAware implements AuditorAware<String> {

  @Override
  public String getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      return null;
    }
    
    String id = String.valueOf(((SocialUserDetail) authentication.getPrincipal()).getUser().getId());
    
    return id;
  }

}
