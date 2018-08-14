package kr.co.pjm.diving.web.service;

import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.web.domain.entity.DiveLog;

public interface DiveLogService {
  
  DiveLog set(DiveLog diveLog);
  
  ResourcesDto getById(Long id);
  
  ResourcesDto getAll();
  
  void update(DiveLog diveLog);
  
  void delete(Long id);
  
}
