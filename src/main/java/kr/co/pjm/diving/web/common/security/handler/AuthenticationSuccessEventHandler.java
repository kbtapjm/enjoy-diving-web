package kr.co.pjm.diving.web.common.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import kr.co.pjm.diving.web.domain.dto.UserBasicDto;
import kr.co.pjm.diving.web.domain.entity.User;
import kr.co.pjm.diving.web.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.common.security.handler
 * @Class Name : AuthenticationSuccessEventHandler.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 12. 26.
 * @Version : 1.0
 * @Description : 로그인 성공 이벤트 핸들러
 *
 */
@Slf4j
public class AuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {
  
  @Autowired
  private UserService userService;

  @Override
  public void onApplicationEvent(AuthenticationSuccessEvent event) {
    if (log.isInfoEnabled()) {
      log.info(" ----------------------------------------------------  ");
      log.info(" getTimestamp : {}", event.getTimestamp());                              // 로그인 시간
      log.info(" getCredentials : {}", event.getAuthentication().getCredentials());      // 로그인시 패스워드
      log.info(" getDetails : {}", event.getAuthentication().getDetails());              // 로그인 세션 아이디          
      log.info(" getPrincipal : {}", event.getAuthentication().getPrincipal());          // 로그인 아이디
      log.info(" getAuthorities : {}", event.getAuthentication().getAuthorities());      // 인증정보
      log.info(" getName : {}", event.getAuthentication().getName());                    // 로그인 아이디
      log.info(" ----------------------------------------------------  ");
    }
    
    /* 로그인 일자 업데이트 */
    User user = userService.getByEmail(event.getAuthentication().getName());
    
    UserBasicDto userBasicDto = new UserBasicDto();
    userBasicDto.setId(user.getId());
    
    userService.updateLoginDate(userBasicDto);
    
    // TODO: 로그인 이력 등록 하기(몽고 DB NOSQL 적용)
  }

}
