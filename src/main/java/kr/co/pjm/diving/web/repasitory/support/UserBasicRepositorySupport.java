package kr.co.pjm.diving.web.repasitory.support;

import kr.co.pjm.diving.web.domain.dto.UserBasicDto;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.repasitory.support
 * @Class Name : UserBasicRepositorySupport.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 9.
 * @Version : 1.0
 * @Description : 유저 기본 레파지토리 확장 인터페이스
 *
 */
public interface UserBasicRepositorySupport {
  
  long updateUserBasic(UserBasicDto userBasicDto);
  
  long updateLoginDate(UserBasicDto userBasicDto);

}
