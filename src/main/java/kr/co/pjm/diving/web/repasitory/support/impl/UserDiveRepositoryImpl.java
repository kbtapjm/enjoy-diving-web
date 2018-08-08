package kr.co.pjm.diving.web.repasitory.support.impl;

import java.util.Date;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import kr.co.pjm.diving.web.domain.dto.UserDiveDto;
import kr.co.pjm.diving.web.domain.entity.QUserDive;
import kr.co.pjm.diving.web.domain.entity.UserDive;
import kr.co.pjm.diving.web.repasitory.support.UserDiveRepositorySupport;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.repasitory.support.impl
 * @Class Name : UserDiveRepositoryImpl.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2018. 5. 2.
 * @Version : 1.0
 * @Description : 유저 다이브 레파지토리 확장 구현 클래스
 *
 */
@Repository
public class UserDiveRepositoryImpl extends QueryDslRepositorySupport implements UserDiveRepositorySupport {
  
  public UserDiveRepositoryImpl() {
    super(UserDive.class);
  }

  @Override
  public long updateUserDive(UserDiveDto userDiveDto) {
    QUserDive qUserDive = QUserDive.userDive;
    
    Long result = update(qUserDive)
        .where(qUserDive.id.eq(userDiveDto.getId()))
        .set(qUserDive.diveGroup, userDiveDto.getDiveGroup())
        .set(qUserDive.diveLevel, userDiveDto.getDiveLevel())
        .set(qUserDive.team, userDiveDto.getTeam())
        .set(qUserDive.signature, userDiveDto.getSignature())
        .set(qUserDive.updateDate, new Date())
        .execute();
    
    return result;
  }

}
