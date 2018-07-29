package kr.co.pjm.diving.web.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import kr.co.pjm.diving.web.domain.entity.UserConnection;
import kr.co.pjm.diving.web.repasitory.support.UserConnectionRepasitorySupport;

public interface UserConnectionRepasitory extends JpaRepository<UserConnection, Long>, QueryDslPredicateExecutor<UserConnection>, UserConnectionRepasitorySupport {

}
