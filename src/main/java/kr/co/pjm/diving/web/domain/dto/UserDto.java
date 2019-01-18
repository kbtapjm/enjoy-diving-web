package kr.co.pjm.diving.web.domain.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import kr.co.pjm.diving.common.domain.enumeration.GenderEnum;
import kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
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
  
  private String provider;
  
  private String token;
  
  
  @Builder
  public UserDto(String email, String name, String provider) {
    this.email = email;
    this.name = name;
    this.provider = provider;
  }
  
  @Getter
  @Setter
  public static class Password {
    private String oldPassword;
    private String newPassword;
  }

}
