package kr.co.pjm.diving.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
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
  public ResourcesDto getById(Long id) {
    if (log.isInfoEnabled()) {
      log.info("id : {}", id);
    }
    DiveLog diveLog = diveLogRepository.findOne(id);
    
    return new ResourcesDto(diveLog);
  }
  
  @Override
  public ResourcesDto getAll() {
    List<DiveLog> list = diveLogRepository.findAll();
    
    // TODO querDsl paging
    
    return new ResourcesDto(list);
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
