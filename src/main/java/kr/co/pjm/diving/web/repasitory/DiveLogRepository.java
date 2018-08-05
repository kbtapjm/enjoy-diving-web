package kr.co.pjm.diving.web.repasitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import kr.co.pjm.diving.web.domain.entity.DiveLog;

@Repository
public interface DiveLogRepository extends JpaRepository<DiveLog, Long>, QueryDslPredicateExecutor<DiveLog> {

}
