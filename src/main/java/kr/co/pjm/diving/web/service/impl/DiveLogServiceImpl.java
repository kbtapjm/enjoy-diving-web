package kr.co.pjm.diving.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pjm.diving.web.domain.entity.DiveLog;
import kr.co.pjm.diving.web.repasitory.DiveLogRepository;
import kr.co.pjm.diving.web.service.DiveLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiveLogServiceImpl implements DiveLogService {

  @Autowired
  private DiveLogRepository diveLogRepository;

  @Override
  public DiveLog set(DiveLog diveLog) {
    return diveLogRepository.save(diveLog);
  }

  @Override
  public DiveLog getById(Long id) {
    if (log.isInfoEnabled()) {
      log.info("id : {}", id);
    }
    
    return diveLogRepository.findOne(id);
  }

  @Override
  public void update(DiveLog diveLog) {
    diveLogRepository.saveAndFlush(diveLog);
  }

  @Override
  public void delete(Long id) {
    diveLogRepository.delete(id);
  }
  
  
  
}
