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
 * 
 * <참조>
 * - JSON으로 변경할때 조인 컬럼들이 변환에러 해결 방법
 * JsonIdentityInfo : 객체의 ID 속성을 이용해 한번만 serialize 되도록 해준다. 
 * 따라서 A.B.A.B … 의 객체 구조는 A.B 까지만 변환이 되고, B.A는 변환되지 않는다.
 * 
 * Jackson-module-hiberate : Jpa Entity의 lazy loading 되는 부분은 json 변환에서 제외해준다. 
 * 상황에 따라서 다르겠지만 사실 나는 B도 불필요했기 때문에 유용하게 쓸 수 있을 것 같다.
 * 
 * @JsonManagedReference : json 부모
 * @JsonBackReference : json 자식
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
 
}
