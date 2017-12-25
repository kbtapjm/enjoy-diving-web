package kr.co.pjm.diving.web.common.security.handler;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * &#64;Package Name : kr.co.pjm.diving.web.common.security.handler
 * &#64;Class Name : ApplicationEventHandler.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 12. 21.
 * @Version : 1.0
 * @Description : 시큐리티 이벤트 핸들러
 *
 */
@Slf4j
public class ApplicationEventHandler implements ApplicationListener<ApplicationEvent> {

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    if (log.isDebugEnabled()) {
      log.debug(" ======================================================================================================= ");
      log.debug("SecurityApplicationEvent : {}", event);
      log.debug(" ===> getTimestamp : {}", event.getTimestamp());
      log.debug(" ===> getSource : {}", event.getSource());
      log.debug(" ======================================================================================================= ");
    }
  }

}
