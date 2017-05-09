package kr.co.pjm.diving.web.service;

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
  
  void set(UserDto userDto) throws Exception;
  
  User getById(Long id) throws Exception;
  
  User getByEmail(String email) throws Exception;
  
  void update(UserDto userDto) throws Exception;
  
  void delete(Long id) throws Exception;
  
}
