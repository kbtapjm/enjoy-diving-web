package kr.co.pjm.diving.web.service;

import kr.co.pjm.diving.web.domain.dto.UserBasicDto;
import kr.co.pjm.diving.web.domain.dto.UserDto;
import kr.co.pjm.diving.web.domain.entity.User;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.service
 * @Class Name : UserService.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 5.
 * @Version : 1.0
 * @Description : 유저 서비스 인터페이스
 *
 */
public interface UserService {
  
  User set(UserDto userDto);
  
  User getById(Long id);
  
  User getByEmail(String email);
  
  void update(UserDto userDto);
  
  void delete(Long id);
  
  void updateLoginDate(UserBasicDto userBasicDto);
  
}
