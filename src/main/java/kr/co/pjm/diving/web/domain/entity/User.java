package kr.co.pjm.diving.web.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import kr.co.pjm.diving.web.common.domain.entity.CommonEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.domain.entity
 * @Class Name : User.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 4.
 * @Version : 1.0
 * @Description : 유저 엔티티
 */
@Getter @Setter @NoArgsConstructor
@Entity(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User extends CommonEntity {
  
  @Id
  @GeneratedValue
  private Long id;
  
  /* 이메일 */
  @Column(name = "email", nullable = false, unique = true)
  private String email; 
  
  /* 비밀번호 */
  @Column(name = "password", nullable = false)
  @JsonIgnore
  private String password;
  
  /* 비밀번호 확인 */
  @Transient
  @JsonIgnore
  private String confirmPassword;
  
  /* 유저 롤 */
  @JsonManagedReference // JSON
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserRole> userRoles = new HashSet<UserRole>();
  
  /* 유저 기본 */
  @OneToOne
  @JoinColumn(name = "user_basic_id")
  private UserBasic userBasic;
  
  /* 유저 다이브 */
  @OneToOne
  @JoinColumn(name = "user_dive_id")
  private UserDive userDive;
}
