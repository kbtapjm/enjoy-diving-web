package kr.co.pjm.diving.web.common.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass 
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonSysEntity implements Serializable {

  private static final long serialVersionUID = 5302749373989416956L;
  
  @Column(name = "reg_id", nullable = false, insertable = true, updatable = false)
  @CreatedBy
  private String regId;
  
  @Column(name = "update_id", nullable = true, insertable = false, updatable = true)
  @LastModifiedBy
  private String updateId;
  
  @Column(name = "reg_date", nullable = false, insertable = true, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
  @CreatedDate
  private Date regDate;

  @Column(name = "update_date", nullable = true, insertable = false, updatable = true)
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
  @LastModifiedDate
  private Date updateDate;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}
