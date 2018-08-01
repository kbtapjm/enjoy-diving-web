package kr.co.pjm.diving.web.service;

import kr.co.pjm.diving.web.domain.entity.DiveLog;

public interface DiveLogService {
  
  DiveLog set(DiveLog diveLog);
  
  DiveLog getById(Long id);
  
  void update(DiveLog diveLog);
  
  void delete(Long id);
  
}
