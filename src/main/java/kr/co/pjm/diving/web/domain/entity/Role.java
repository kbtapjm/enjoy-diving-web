package kr.co.pjm.diving.web.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import kr.co.pjm.diving.common.domain.enumeration.RoleTypeEnum;
import kr.co.pjm.diving.web.common.domain.entity.CommonEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.domain.entity
 * @Class Name : UserAuthority.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 4.
 * @Version : 1.0
 * @Description : 권한 엔티티
 *
 */
@Getter @Setter
@NoArgsConstructor
@Entity(name = "role")
public class Role extends CommonEntity {
  
  @Id
  @GeneratedValue
  private Long id;
  
  /* 롤 */
  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  private RoleTypeEnum role;
  
  /* 롤이름 */
  @Column(name = "role_name", nullable = false)
  private String roleName;
  
  /* 유저 롤 엔티티 */
  @OneToMany(mappedBy = "role")
  private Set<UserRole> userRoles;

}
