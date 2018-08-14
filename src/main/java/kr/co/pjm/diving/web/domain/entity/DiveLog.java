package kr.co.pjm.diving.web.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.co.pjm.diving.common.domain.enumeration.DiveCurrentEnum;
import kr.co.pjm.diving.common.domain.enumeration.DivePlanExrPtnEnum;
import kr.co.pjm.diving.common.domain.enumeration.DivePlanToolEnum;
import kr.co.pjm.diving.common.domain.enumeration.DiveTypeEnum;
import kr.co.pjm.diving.common.domain.enumeration.DiveWaterEnum;
import kr.co.pjm.diving.common.domain.enumeration.DiveWaveEnum;
import kr.co.pjm.diving.web.common.domain.entity.CommonSysEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor 
@Entity(name = "dive_log")
public class DiveLog extends CommonSysEntity {
  
  private static final long serialVersionUID = 7636566383016370649L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  /* 번호 */
  @Column(name = "dive_no", nullable = false, unique = true)
  private Long diveNo;
  
  /* 다이브 날짜 */
  @Column(name = "dive_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern="yyyy-MM-dd")
  private Date diveDate;
  
  /* 다이브 장소 */
  @Column(name = "dive_place", nullable = false, length = 200)
  private String divePlace;
  
  /* 다이브 포인트 */
  @Column(name = "dive_point", nullable = false, length = 200)
  private String divePoint;
  
  /* 다이브 입수 시간(시) */
  @Column(name = "dive_in_hour", nullable = false, length = 10)
  private String diveInHour;
  
  /* 다이브 입수 시간(분) */
  @Column(name = "dive_in_minute", nullable = false, length = 10)
  private String diveInMinute;
  
  /* 다이브 출수 시간(시) */
  @Column(name = "dive_out_hour", nullable = false, length = 10)
  private String diveOutHour;
  
  /* 다이브 출수 시간(분) */
  @Column(name = "dive_out_minute", nullable = false, length = 10)
  private String diveOutMinute;
  
  /* 탱크 압력 시작(bar) */
  @Column(name = "dive_tank_start", nullable = false, length = 10)
  private String diveTankStart;
  
  /* 탱크 압력 종료(bar) */
  @Column(name = "dive_tank_end", nullable = false, length = 10)
  private String diveTankEnd;
  
  /* 수면 휴식 시간(시) */
  @Column(name = "ground_rest_hour", nullable = true, length = 10)
  private String groundRestHour;
  
  /* 수면 휴식 시간(분) */
  @Column(name = "ground_rest_minute", nullable = true, length = 10)
  private String groundRestMinute;
  
  /* 최대 수심 */
  @Column(name = "max_depth", nullable = false, length = 10)
  private String maxDepth;
  
  /* 평균 수심 */
  @Column(name = "avg_depth", nullable = false, length = 10)
  private String avgDepth;
  
  /* 다이브 시간(min) */
  @Column(name = "dive_time", nullable = false, length = 10)
  private String diveTime;
  
  /* 안전정지 시간 */
  @Column(name = "dive_safety_time", nullable = false, length = 10)
  private String diveSafetyTime;
  
  /* 다이빙 계획 도구 */
  @Column(name = "dive_plan_tool", nullable = false)
  @Enumerated(EnumType.STRING)
  private DivePlanToolEnum divePlanTool;
  
  /* 웨이트(Kg) */
  @Column(name = "dive_plan_weight", nullable = true, length = 10)
  private String divePlanWeight;
  
  /* Eanx(나이트록스) */
  @Column(name = "dive_plan_eanx", nullable = true, length = 10)
  private String divePlanEanx;
  
  /* 노출 보호 */
  @Column(name = "dive_plan_exr_ptn", nullable = false)
  @Enumerated(EnumType.STRING)
  private DivePlanExrPtnEnum divePlanExrPtn;
  
  /* 다이브 후드 여부 */
  @Column(name = "dive_plan_hood_yn", nullable = true, length = 1)
  private String divePlanHoodYn;
  
  /* 다이브 장갑 여부 */
  @Column(name = "dive_plan_gloves_yn", nullable = true, length = 1)
  private String divePlanGlovesYn;
  
  /* 다이브 부츠 여부 */
  @Column(name = "dive_plan_boots_yn", nullable = true, length = 1)
  private String divePlanBootsYn;
  
  /* 다이브 라이트 여부 */
  @Column(name = "dive_plan_light_yn", nullable = true, length = 1)
  private String divePlanLightYn;
  
  /* 다이브 SMB 여부 */
  @Column(name = "dive_plan_smb_yn", nullable = true, length = 1)
  private String divePlanSmbYn;
  
  /* 다이브 나이프 여부 */
  @Column(name = "dive_plan_knife_yn", nullable = true, length = 1)
  private String divePlanKnifeYn;
  
  /* 다이브 카메라 여부 */
  @Column(name = "dive_plan_camera_yn", nullable = true, length = 1)
  private String divePlanCameraYn;
  
  /* 시야 */
  @Column(name = "visibility", nullable = false, length = 10)
  private String visibility;
  
  /* 수온 */
  @Column(name = "temperature", nullable = false, length = 10)
  private String temperature;
  
  /* 다이브 유형 */
  @Column(name = "dive_type", nullable = true)
  @Enumerated(EnumType.STRING)
  private DiveTypeEnum diveType;
  
  /* 다이브 워터 */
  @Column(name = "dive_water", nullable = true)
  @Enumerated(EnumType.STRING)
  private DiveWaterEnum diveWater;
  
  /* 다이브 파도 */
  @Column(name = "dive_wave", nullable = true)
  @Enumerated(EnumType.STRING)
  private DiveWaveEnum diveWave;
  
  /* 다이브 조류  */
  @Column(name = "dive_current", nullable = true)
  @Enumerated(EnumType.STRING)
  private DiveCurrentEnum diveCurrent;
  
  /* 다이브 활동 */
  @Column(name = "dive_activity", nullable = true, length = 1000)
  private String diveActivity;
  
  /* 다이브 노트 */
  @Column(name = "dive_note", nullable = true, length = 4000)
  private String diveNote;
  
  @PrePersist
  public void prePersist() {
      this.divePlanHoodYn = this.divePlanHoodYn != null ? this.divePlanHoodYn : "N";
      this.divePlanGlovesYn = this.divePlanGlovesYn != null ? this.divePlanGlovesYn : "N";
      this.divePlanBootsYn = this.divePlanBootsYn != null ? this.divePlanBootsYn : "N";
      this.divePlanLightYn = this.divePlanLightYn != null ? this.divePlanLightYn : "N";
      this.divePlanSmbYn = this.divePlanSmbYn != null ? this.divePlanSmbYn : "N";
      this.divePlanKnifeYn = this.divePlanKnifeYn != null ? this.divePlanKnifeYn : "N";
      this.divePlanCameraYn = this.divePlanCameraYn != null ? this.divePlanCameraYn : "N";
  }
  
}
