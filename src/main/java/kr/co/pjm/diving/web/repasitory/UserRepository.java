package kr.co.pjm.diving.web.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.pjm.diving.web.domain.entity.User;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.repasitory
 * @Class Name : UserRepository.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 5.
 * @Version : 1.0
 * @Description : 유저 레파지토리
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
  
  User findByEmail(String email);

}
