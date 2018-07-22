package kr.co.pjm.diving.web.domain.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.UserProfile;

import kr.co.pjm.diving.common.domain.enumeration.GenderEnum;
import kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum;
import kr.co.pjm.diving.web.domain.entity.UserBasic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.domain.dto
 * @Class Name : UserDto.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 8.
 * @Version : 1.0
 * @Description : 유저 DTO
 *
 */
@Getter @Setter
@NoArgsConstructor
public class UserDto extends UserBasic {
  
  /* 이메일 */
  private String email;
  
  /* 비밀번호 */
  private String password;
  
  /* 이름 */
  private String name;
  
  /* 닉네임 */
  private String nickname;
  
  /* 성별 */
  @Enumerated(EnumType.ORDINAL)
  private GenderEnum gender;
  
  /* 국가 */
  private String country;
  
  /* 유저 상태 */
  @Enumerated(EnumType.ORDINAL)
  private UserStatusEnum status;
  
  /* 프로필 URL */
  private String profile;
  
  /* 소개 */
  private String introduce;
  
  /* 다이브 단체 */
  private String diveGroup;
  
  /* 다이브 레벨 */
  private String diveLevel;
  
  /* 다이브 소속 */
  private String team;
  
  /* 서명 */
  private String signature;
  
  /**
   * 
   * @param userProfile
   * @return
   */
  public static UserDto fromSocialUserProfile(UserProfile userProfile) {
    UserDto userDto = new UserDto();
    userDto.setEmail(!StringUtils.isEmpty(userProfile.getEmail()) ? userProfile.getEmail() : "");
    
    String firstName = !StringUtils.isEmpty(userProfile.getFirstName()) ? userProfile.getFirstName() : ""; 
    String lastName = !StringUtils.isEmpty(userProfile.getLastName()) ? userProfile.getLastName() : ""; 
    userDto.setName(firstName + lastName);
    
    return userDto;
}

}
