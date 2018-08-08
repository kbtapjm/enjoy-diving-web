package kr.co.pjm.diving.web.repasitory.support.impl;

import java.util.Date;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import kr.co.pjm.diving.web.domain.dto.UserBasicDto;
import kr.co.pjm.diving.web.domain.entity.QUserBasic;
import kr.co.pjm.diving.web.domain.entity.UserBasic;
import kr.co.pjm.diving.web.repasitory.support.UserBasicRepositorySupport;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.repasitory.support.impl
 * @Class Name : UserBasicRepositoryImpl.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 9.
 * @Version : 1.0
 * @Description : 유저 기본 레파지토리 확장 구현 클래스
 *
 */
@Repository
public class UserBasicRepositoryImpl extends QueryDslRepositorySupport implements UserBasicRepositorySupport {
  
  public UserBasicRepositoryImpl() {
    super(UserBasic.class);
  }

  @Override
  public long updateUserBasic(UserBasicDto userBasicDto) {
    QUserBasic qUserBasic = QUserBasic.userBasic;
    
    Long result = update(qUserBasic)
        .where(qUserBasic.id.eq(userBasicDto.getId()))
        .set(qUserBasic.name, userBasicDto.getName())
        .set(qUserBasic.nickname, userBasicDto.getNickname())
        .set(qUserBasic.country, userBasicDto.getCountry())
        .set(qUserBasic.gender, userBasicDto.getGender())
        .set(qUserBasic.introduce, userBasicDto.getIntroduce())
        .set(qUserBasic.updateDate, new Date())
        .execute();
    
    return result;
  }

  @Override
  public long updateLoginDate(UserBasicDto userBasicDto) {
    QUserBasic qUserBasic = QUserBasic.userBasic;
    
    Long result = update(qUserBasic)
        .where(qUserBasic.id.eq(userBasicDto.getId()))
        .set(qUserBasic.loginDate, new Date())
        .execute();
    
    return result;
  }
  
}
