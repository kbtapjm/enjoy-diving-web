package kr.co.pjm.diving.web.common.security.handler;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.common.security.handler
 * @Class Name : SessionDestroyedEventHandler.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 12. 26.
 * @Version : 1.0
 * @Description : 세션 소멸 이벤트 핸들러
 *
 */
@Slf4j
public class SessionDestroyedEventHandler implements ApplicationListener<SessionDestroyedEvent> {

  @Override
  public void onApplicationEvent(SessionDestroyedEvent event) {
    if (log.isInfoEnabled()) {
      log.info(" ----------------------------------------------------  ");
      log.info(" SessionDestroyedEvent getId : {}", event.getId());
      log.info(" SessionDestroyedEvent getTimestamp : {}", event.getTimestamp());
      log.info(" ----------------------------------------------------  ");
    }
  }

}
