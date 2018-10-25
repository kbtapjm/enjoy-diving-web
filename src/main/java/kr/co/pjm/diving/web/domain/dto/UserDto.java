package kr.co.pjm.diving.web.domain.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import kr.co.pjm.diving.common.domain.entity.UserBasic;
import kr.co.pjm.diving.common.domain.enumeration.GenderEnum;
import kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum;
import lombok.Builder;
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
  
  @Builder
  public UserDto(String email, String name) {
    this.email = email;
    this.name = name;
  }

}
