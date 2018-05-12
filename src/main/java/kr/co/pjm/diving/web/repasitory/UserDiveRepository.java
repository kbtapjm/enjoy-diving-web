package kr.co.pjm.diving.web.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import kr.co.pjm.diving.web.domain.entity.UserDive;
import kr.co.pjm.diving.web.repasitory.support.UserDiveRepositorySupport;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.repasitory
 * @Class Name : UserDiveRepository.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2018. 5. 2.
 * @Version : 1.0
 * @Description : 유저 다이브 레파지토리 확장 인터페이스
 *
 */
public interface UserDiveRepository extends JpaRepository<UserDive, Long>, QueryDslPredicateExecutor<UserDive>, UserDiveRepositorySupport {
}
