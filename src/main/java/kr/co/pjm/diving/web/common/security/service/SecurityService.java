package kr.co.pjm.diving.web.common.security.service;

import org.springframework.security.core.userdetails.UserDetails;

import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.web.common.security.social.SocialUserDetail;
import kr.co.pjm.diving.web.domain.dto.LoginDto;

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

  UserDetails login(LoginDto loginDto);
  
  String getLoginUser();
  
  SocialUserDetail getSocialUserDetailUser();
  
  User getUser();
}
