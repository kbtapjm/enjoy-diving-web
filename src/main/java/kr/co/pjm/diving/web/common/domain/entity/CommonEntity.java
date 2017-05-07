package kr.co.pjm.diving.web.common.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.common.domain.entity
 * @Class Name : CommonEntity.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 4.
 * @Version : 1.0
 * @Description : 공통 필드 엔티티
 *
 */
@Getter @Setter
public class CommonEntity {
  
  /* 등록일 */
  @Column(name = "reg_date", nullable = false, insertable = true, updatable = false)
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
  private Date regDate;

  /* 수정일 */
  @Column(name = "update_date", nullable = true, insertable = true, updatable = false)
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
  private Date updateDate;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
  
  @PrePersist
  public void prePersist() {
      this.regDate = new Date();
  }
  
  @PreUpdate
  public void preUpdate() {
      this.updateDate = new Date();
  }
}
