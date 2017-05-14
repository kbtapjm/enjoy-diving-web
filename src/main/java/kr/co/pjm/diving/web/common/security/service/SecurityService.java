package kr.co.pjm.diving.web.common.security.service;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.common.security.service
 * @Class Name : SecurityService.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 12.
 * @Version : 1.0
 * @Description : 스프링 시큐리티 서비스 인터페이스 
 *
 */
public interface SecurityService {

  void login(String email, String password);
  
  String getLoginUser();
}
