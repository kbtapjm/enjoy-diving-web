package kr.co.pjm.diving.web.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

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
public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {
  
  User findByEmail(String email);

}
