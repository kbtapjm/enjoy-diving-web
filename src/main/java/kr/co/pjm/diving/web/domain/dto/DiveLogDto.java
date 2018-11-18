package kr.co.pjm.diving.web.domain.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.co.pjm.diving.common.domain.enumeration.DiveCurrentEnum;
import kr.co.pjm.diving.common.domain.enumeration.DivePlanExrPtnEnum;
import kr.co.pjm.diving.common.domain.enumeration.DivePlanToolEnum;
import kr.co.pjm.diving.common.domain.enumeration.DiveTypeEnum;
import kr.co.pjm.diving.common.domain.enumeration.DiveWaterEnum;
import kr.co.pjm.diving.common.domain.enumeration.DiveWaveEnum;
import kr.co.pjm.diving.common.domain.enumeration.YnEnum;
import kr.co.pjm.diving.web.common.domain.dto.CommonDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiveLogDto extends CommonDto {
  
  private Long id;

  private String diveNo;
  
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate diveDate;
  
  private String divePlace;
  
  private String divePoint;
  
  private String diveInHour;
  
  private String diveInMinute;
  
  private String diveOutHour;
  
  private String diveOutMinute;
  
  private String diveTankStart;
  
  private String diveTankEnd;
  
  private String groundRestHour;
  
  private String groundRestMinute;
  
  private String maxDepth;
  
  private String avgDepth;
  
  private String diveTime;
  
  private String diveSafetyTime;
  
  @Enumerated(EnumType.STRING)
  private DivePlanToolEnum divePlanTool;
  
  private String divePlanWeight;
  
  private String divePlanEanx;
  
  @Enumerated(EnumType.STRING)
  private DivePlanExrPtnEnum divePlanExrPtn;

  @Enumerated(EnumType.STRING)
  private YnEnum divePlanHoodYn;
  
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanGlovesYn;
  
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanBootsYn;
  
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanLightYn;
  
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanSmbYn;
  
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanKnifeYn;
  
  @Enumerated(EnumType.STRING)
  private YnEnum divePlanCameraYn;
  
  private String visibility;
  
  private String temperature;
  
  @Enumerated(EnumType.STRING)
  private DiveTypeEnum diveType;
  
  @Enumerated(EnumType.STRING)
  private DiveWaterEnum diveWater;
  
  @Enumerated(EnumType.STRING)
  private DiveWaveEnum diveWave;
  
  @Enumerated(EnumType.STRING)
  private DiveCurrentEnum diveCurrent;
  
  private String diveActivity;
  
  private String diveNote;

}
