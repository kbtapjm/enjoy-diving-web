package kr.co.pjm.diving.web.service;

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
 * @Description : 
 *
 */
public interface UserService {
  
  User getById(Long id) throws Exception;
  
}
