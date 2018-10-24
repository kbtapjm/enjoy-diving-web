package kr.co.pjm.diving.web.common.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import kr.co.pjm.diving.web.common.security.model.SocialUserDetail;
import kr.co.pjm.diving.web.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {
  
  @Autowired
  private UserService userService;

  @Override
  public void onApplicationEvent(AuthenticationSuccessEvent event) {
    if (log.isDebugEnabled()) {
      log.debug(" ----------------------------------------------------  ");
      log.debug(" getTimestamp : {}", event.getTimestamp());                            // 로그인 시간
      log.debug(" getCredentials : {}", event.getAuthentication().getCredentials());    // 로그인시 패스워드
      log.debug(" getDetails : {}", event.getAuthentication().getDetails());            // 로그인 세션 아이디          
      log.debug(" getPrincipal : {}", event.getAuthentication().getPrincipal());        // 로그인 아이디
      log.debug(" getAuthorities : {}", event.getAuthentication().getAuthorities());    // 인증정보
      log.debug(" getName : {}", event.getAuthentication().getName());                  // 로그인 아이디
      log.debug(" ----------------------------------------------------  ");
    }
    
    try {
      SocialUserDetail socialUserDetail = (SocialUserDetail) event.getAuthentication().getPrincipal();
      
      userService.updateLoginDate(socialUserDetail.getUser().getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // TODO: 로그인 이력 등록 하기(몽고 DB NOSQL 적용)
  }

}
