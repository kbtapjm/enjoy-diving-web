package kr.co.pjm.diving.web.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import kr.co.pjm.diving.web.domain.entity.UserBasic;
import kr.co.pjm.diving.web.repasitory.support.UserBasicRepositorySupport;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.repasitory
 * @Class Name : UserBasicRepository.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 8.
 * @Version : 1.0
 * @Description : 유저 기본 레파지토리
 *
 */
public interface UserBasicRepository extends JpaRepository<UserBasic, Long>, QueryDslPredicateExecutor<UserBasic>, UserBasicRepositorySupport {

}
