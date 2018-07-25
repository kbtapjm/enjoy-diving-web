package kr.co.pjm.diving.web.common.security.handler;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.common.security.handler
 * @Class Name : AuthenticationFailureEventHandler.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 12. 26.
 * @Version : 1.0
 * @Description : 시큐리티 인증실패 이벤트 핸들러
 *
 */
@Slf4j
public class AuthenticationFailureEventHandler implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

  @Override
  public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
    if (log.isInfoEnabled()) {
      log.info(" ----------------------------------------------------  ");
      log.info(" getTimestamp : {}", event.getTimestamp());                              // 로그인 실패시간
      log.info(" getException : {}", event.getException());                              // 로그인 에러내용
      log.info(" getCredentials : {}", event.getAuthentication().getCredentials());      // 로그인시 패스워드
      log.info(" getDetails : {}", event.getAuthentication().getDetails());              // 로그인실패 세션 아이디          
      log.info(" getPrincipal : {}", event.getAuthentication().getPrincipal());          // 로그인 아이디
      log.info(" getAuthorities : {}", event.getAuthentication().getAuthorities());      // 인증정보
      log.info(" getName : {}", event.getAuthentication().getName());                    // 로그인 아이디
      log.info(" ----------------------------------------------------  ");
    }
  }

}
