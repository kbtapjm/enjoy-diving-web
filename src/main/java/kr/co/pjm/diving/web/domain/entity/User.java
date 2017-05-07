package kr.co.pjm.diving.web.domain.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
 *
 */
@Getter @Setter
@NoArgsConstructor
@Entity(name = "user")
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
  private String confirmPassword;
  
  /* 유저 엔티티, 롤 엔티티 테이블 맵핑 */
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;
  
  /* 유저 기본 엔티티 1:1 맵핑 */
  @OneToOne
  @JoinColumn(name = "user_basic_id")
  private UserBasic userBasic;
 
}
