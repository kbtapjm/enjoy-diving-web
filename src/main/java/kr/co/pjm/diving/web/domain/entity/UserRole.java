package kr.co.pjm.diving.web.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.domain.entity
 * @Class Name : UserRole.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 9.
 * @Version : 1.0
 * @Description : 유저 롤 엔티티
 *
 */
@Getter @Setter @NoArgsConstructor
@Entity(name = "user_role")
public class UserRole implements Serializable  {

  private static final long serialVersionUID = 2889050156176005410L;

  /* 유저 */
  @Id
  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private User user;
  
  /* 롤 */
  @Id
  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;
  
  /* 등록일 */
  @Column(name = "reg_date", nullable = false, insertable = true, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
  private Date regDate;
  
  @PrePersist
  public void prePersist() {
      this.regDate = new Date();
  }
  
}
