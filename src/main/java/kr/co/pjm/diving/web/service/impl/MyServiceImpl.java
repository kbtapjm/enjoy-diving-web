package kr.co.pjm.diving.web.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import kr.co.pjm.diving.web.api.dto.ApiReponseDto;
import kr.co.pjm.diving.web.api.service.DiveLogApiService;
import kr.co.pjm.diving.web.api.service.UserApiService;
import kr.co.pjm.diving.web.common.exception.EnjoyDivingWebException;
import kr.co.pjm.diving.web.service.MyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class MyServiceImpl implements MyService {

  private UserApiService userApiService;
  
  private DiveLogApiService diveLogApiService;

  @Override
  public void deleteUserLeave(Long id) throws Exception {
    // TODO transaction ?
    ApiReponseDto apiReponseDto = diveLogApiService.deleteDiveLogByUser(id);
    if (apiReponseDto.getStatus() != HttpStatus.NO_CONTENT.value()) { 
      throw new EnjoyDivingWebException(apiReponseDto); 
    }
    
    apiReponseDto = userApiService.deleteUser(id);
    if (apiReponseDto.getStatus() != HttpStatus.NO_CONTENT.value()) { 
      throw new EnjoyDivingWebException(apiReponseDto); 
    }
  }
  
}
