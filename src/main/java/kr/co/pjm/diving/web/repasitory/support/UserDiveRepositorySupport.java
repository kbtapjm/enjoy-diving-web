package kr.co.pjm.diving.web.repasitory.support;

import kr.co.pjm.diving.web.domain.dto.UserDiveDto;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.repasitory.support
 * @Class Name : UserDiveRepositorySupport.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2018. 5. 2.
 * @Version : 1.0
 * @Description : 유저 다이브 레파지토리 확장 인터페이스 
 *
 */
public interface UserDiveRepositorySupport {

  long updateUserDive(UserDiveDto userDiveDto);
}
