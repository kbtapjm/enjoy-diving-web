package kr.co.pjm.diving.web.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.co.pjm.diving.web.common.domain.entity.CommonEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.domain.entity
 * @Class Name : UserDive.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 5.
 * @Version : 1.0
 * @Description : 유저 다이브
 *
 */
@Getter @Setter
@NoArgsConstructor
@Entity(name = "user_dive")
public class UserDive extends CommonEntity {
  
  private static final long serialVersionUID = -7362740858826005486L;

  @Id
  @GeneratedValue
  private Long id;
  
  /* 다이브 단체 */
  @Column(name = "dive_group", nullable = true, length = 200)
  private String diveGroup;
  
  /* 다이브 레벨 */
  @Column(name = "dive_level", nullable = true, length = 100)
  private String diveLevel;
  
  /* 다이브 소속 */
  @Column(name = "team", nullable = true, length = 150)
  private String team;
  
  /* 서명 */
  @Column(name = "signature", nullable = true, length = 300)
  private String signature;
  
  /* 유저 */ 
  @OneToOne(mappedBy = "userDive")
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;

}
