package kr.co.pjm.diving.web.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import kr.co.pjm.diving.common.domain.dto.PagingDto;
import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.common.domain.dto.SearchDto;
import kr.co.pjm.diving.web.api.dto.ApiReponseDto;
import kr.co.pjm.diving.web.api.service.DiveLogApiService;
import kr.co.pjm.diving.web.common.exception.EnjoyDivingWebException;
import kr.co.pjm.diving.web.common.security.component.CertificationUser;
import kr.co.pjm.diving.web.domain.dto.DiveLogDto;
import kr.co.pjm.diving.web.service.DiveLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class DiveLogServiceImpl implements DiveLogService {
  
  private DiveLogApiService diveLogApiService;
  
  @Override
  public ResourcesDto getAll(SearchDto searchDto, PagingDto pagingDto) throws Exception {
    String sorts = "-diveNo";
    String q = "regId=".concat(CertificationUser.getId());
    
    ApiReponseDto apiReponseDto = diveLogApiService.getDiveLogs(sorts, q);
    if (apiReponseDto.getStatus() != HttpStatus.OK.value()) {
      throw new EnjoyDivingWebException(apiReponseDto);
    };
    
    return (ResourcesDto) apiReponseDto.getData();
  }

  @Override
  public void set(DiveLogDto diveLogDto) throws Exception {
    diveLogDto.setRegId(CertificationUser.getId());
    
    ApiReponseDto apiReponseDto = diveLogApiService.createDiveLog(diveLogDto);
    if (apiReponseDto.getStatus() != HttpStatus.CREATED.value()) {
      throw new EnjoyDivingWebException(apiReponseDto);
    }
  }

  @Override
  public ResourcesDto getById(Long id) throws Exception {
    if (log.isInfoEnabled()) {
      log.info("id : {}", id);
    }
    
    ApiReponseDto apiReponseDto = diveLogApiService.getDiveLog(id);
    if (apiReponseDto.getStatus() != HttpStatus.OK.value()) {
      throw new EnjoyDivingWebException(apiReponseDto);
    };
    
    return new ResourcesDto(apiReponseDto.getData());
  }
  
  @Override
  public void update(Long id, DiveLogDto diveLogDto) throws Exception {
    diveLogDto.setUpdateId(CertificationUser.getId());
    
    ApiReponseDto apiReponseDto = diveLogApiService.updateDiveLog(id, diveLogDto);
    if (apiReponseDto.getStatus() != HttpStatus.OK.value()) {
      throw new EnjoyDivingWebException(apiReponseDto);
    };
  }

  @Override
  public void delete(Long id) throws Exception {
    ApiReponseDto apiReponseDto = diveLogApiService.deleteDiveLog(id);
    
    if (apiReponseDto.getStatus() != HttpStatus.NO_CONTENT.value()) {
      throw new EnjoyDivingWebException(apiReponseDto);
    }
  }
  
}
