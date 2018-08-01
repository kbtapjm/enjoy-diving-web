package kr.co.pjm.diving.web.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.co.pjm.diving.web.common.domain.entity.CommonEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor 
@Entity(name = "dive_log")
public class DiveLog extends CommonEntity {
  
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
  @Column(name = "dive_in_hour", nullable = false)
  private Long diveInHour;
  
  /* 다이브 입수 시간(분) */
  @Column(name = "dive_in_minute", nullable = false)
  private Long diveInMinute;
  
  /* 다이브 출수 시간(시) */
  @Column(name = "dive_out_hour", nullable = false)
  private Long diveOutHour;
  
  /* 다이브 출수 시간(분) */
  @Column(name = "dive_out_minute", nullable = false)
  private Long diveOutMinute;
  
  /* 탱크 압력 시작(bar) */
  @Column(name = "dive_tank_start", nullable = false)
  private Long diveTankStart;
  
  /* 탱크 압력 종료(bar) */
  @Column(name = "dive_tank_end", nullable = false)
  private Long diveTankEnd;
  
  /* 수면 휴식 시간(시) */
  @Column(name = "ground_rest_hour", nullable = false)
  private Long groundRestHour;
  
  /* 수면 휴식 시간(분) */
  @Column(name = "ground_rest_minute", nullable = false)
  private Long groundRestMinute;
  
  /* 최대 수심 */
  @Column(name = "max_depth", nullable = false)
  private Long maxDepth;
  
  /* 평균 수심 */
  @Column(name = "avg_depth", nullable = false)
  private Long avgDepth;
  
  /* 다이브 시간(min) */
  @Column(name = "dive_time", nullable = false)
  private Long diveTime;
  
  /* 안전정지 시간 */
  @Column(name = "dive_safety_time", nullable = false)
  private Long diveSafetyTime;
  
  /* 다이빙 계획 도구(0:컴퓨터, 1:테이블, 2:기타) */
  @Column(name = "dive_plan_tool", nullable = false, length = 1)
  private String divePlanTool;
  
  /* 웨이트(Kg) */
  @Column(name = "dive_plan_weight", nullable = false)
  private Long divePlanWeight;
  
  /* Eanx(나이트록스) */
  @Column(name = "dive_plan_eanx")
  private Long divePlanEanx;
  
  /* 노출 보호(0:스킨, 1:웻슈트, 2:드라이슈트) */
  @Column(name = "dive_plan_exr_ptn", length = 1)
  private String divePlanExrPtn;
  
  /* 다이브 후드 여부 */
  @Column(name = "dive_plan_hood_yn", length = 1)
  private String divePlanHoodYn;
  
  /* 다이브 장갑 여부 */
  @Column(name = "dive_plan_gloves_yn", length = 1)
  private String divePlanGlovesYn;
  
  /* 다이브 부츠 여부 */
  @Column(name = "dive_plan_boots_yn", length = 1)
  private String divePlanBootsYn;
  
  /* 다이브 라이트 여부 */
  @Column(name = "dive_plan_light_yn", length = 1)
  private String divePlanLightYn;
  
  /* 다이브 SMB 여부 */
  @Column(name = "dive_plan_smb_yn", length = 1)
  private String divePlanSmbYn;
  
  /* 다이브 나이프 여부 */
  @Column(name = "dive_plan_knife_yn", length = 1)
  private String divePlanKnifeYn;
  
  /* 다이브 카메라 여부 */
  @Column(name = "dive_plan_camera_yn", length = 1)
  private String divePlanCameraYn;
  
  /* 시야 */
  @Column(name = "visibility", nullable = false)
  private Long visibility;
  
  /* 수온 */
  @Column(name = "temperature", nullable = false)
  private Long temperature;
  
  /* 다이브 유형(0:비치, 1:보트) */
  @Column(name = "dive_type", nullable = true, length = 1)
  private String diveType;
  
  /* 다이브 워터(0:바다, 1:민물) */
  @Column(name = "dive_water", nullable = true, length = 1)
  private String diveWater;
  
  /* 다이브 파도 (0:없음, 1:보통, 2:강함) */
  @Column(name = "dive_wave", nullable = true, length = 1)
  private String diveWave;
  
  /* 다이브 조류 (0:없음, 1:보통, 2:강함) */
  @Column(name = "dive_current", nullable = true, length = 1)
  private String diveCurrent;
  
  /* 다이브 활동 */
  @Column(name = "dive_activity", nullable = true, length = 1000)
  private String diveActivity;
  
  /* 다이브 노트 */
  @Column(name = "dive_note", nullable = true, length = 4000)
  private String diveNote;
  
  
}
