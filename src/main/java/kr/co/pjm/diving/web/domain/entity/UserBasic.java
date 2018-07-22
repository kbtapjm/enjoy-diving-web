package kr.co.pjm.diving.web.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.co.pjm.diving.common.domain.enumeration.GenderEnum;
import kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum;
import kr.co.pjm.diving.web.common.domain.entity.CommonEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.domain.entity
 * @Class Name : UserBasic.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 7.
 * @Version : 1.0
 * @Description : 유저 기본 엔티티
 *
 */
@Getter @Setter
@NoArgsConstructor 
@Entity(name = "user_basic")
public class UserBasic extends CommonEntity {

  private static final long serialVersionUID = -1639688996082330984L;

  @Id
  @GeneratedValue
  private Long id;
  
  /* 이름 */
  @Column(name = "name", nullable = false, length = 100, columnDefinition = "VARCHAR(100)")
  private String name;
  
  /* 닉네임 */
  @Column(name = "nickname", nullable = true, unique = true, length = 100)
  private String nickname;
  
  /* 성별 */
  @Column(name = "gender", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private GenderEnum gender;
  
  /* 국가 */
  @Column(name = "country", nullable = false, length = 50)
  private String country;
  
  /* 유저 상태 */
  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private UserStatusEnum status;
  
  /* 프로필 URL */
  @Column(name = "profile", nullable = true, length = 200)
  private String profile;
  
  /* 소개 */
  @Column(name = "introduce", nullable = true, length = 2000)
  private String introduce;
  
  /* 로그인 일자 */
  @Column(name = "login_date", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date loginDate;
  
  /* 유저 */ 
  @OneToOne(mappedBy = "userBasic")
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;

}
