package kr.co.pjm.diving.web.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import kr.co.pjm.diving.common.domain.enumeration.RoleTypeEnum;
import kr.co.pjm.diving.web.domain.entity.Role;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.repasitory
 * @Class Name : RoleRepository.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 9.
 * @Version : 1.0
 * @Description : 롤 레파지토리 
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, QueryDslPredicateExecutor<Role> {
  
  Role findByRole(RoleTypeEnum  roleTypeEnum);

}
