package kr.co.pjm.diving.web.domain.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.UserProfile;

import kr.co.pjm.diving.common.domain.entity.UserBasic;
import kr.co.pjm.diving.common.domain.enumeration.GenderEnum;
import kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends UserBasic {

  private static final long serialVersionUID = 4567012528819692037L;

  private String email;
  private String password;
  private String name;
  private String nickname;

  @Enumerated(EnumType.ORDINAL)
  private GenderEnum gender;

  private String country;

  @Enumerated(EnumType.ORDINAL)
  private UserStatusEnum status;

  private String profile;

  private String introduce;

  private String diveGroup;

  private String diveLevel;

  private String team;

  private String signature;

  public static UserDto fromSocialUserProfile(UserProfile userProfile) {
    UserDto userDto = new UserDto();
    userDto.setEmail(!StringUtils.isEmpty(userProfile.getEmail()) ? userProfile.getEmail() : "");

    String firstName = !StringUtils.isEmpty(userProfile.getFirstName()) ? userProfile.getFirstName() : "";
    String lastName = !StringUtils.isEmpty(userProfile.getLastName()) ? userProfile.getLastName() : "";
    userDto.setName(firstName + lastName);

    return userDto;
  }

}
