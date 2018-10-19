package kr.co.pjm.diving.web.common.security.handler;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFailureEventHandler implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  @Override
  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
    if (log.isDebugEnabled()) {
      log.debug(" ----------------------------------------------------  ");
      log.debug(" getTimestamp : {}", event.getTimestamp());                              // 로그인 실패시간
      log.debug(" getException : {}", event.getException());                              // 로그인 에러내용
      log.debug(" getCredentials : {}", event.getAuthentication().getCredentials());      // 로그인시 패스워드
      log.debug(" getDetails : {}", event.getAuthentication().getDetails());              // 로그인실패 세션 아이디          
      log.debug(" getPrincipal : {}", event.getAuthentication().getPrincipal());          // 로그인 아이디
      log.debug(" getAuthorities : {}", event.getAuthentication().getAuthorities());      // 인증정보
      log.debug(" getName : {}", event.getAuthentication().getName());                    // 로그인 아이디
      log.debug(" ----------------------------------------------------  ");
    }
  }

}
