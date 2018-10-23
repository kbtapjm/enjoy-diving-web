package kr.co.pjm.diving.web.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import kr.co.pjm.diving.common.domain.dto.PagingDto;
import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.common.domain.dto.SearchDto;
import kr.co.pjm.diving.web.api.dto.ApiReponseDto;
import kr.co.pjm.diving.web.api.service.DiveLogApiService;
import kr.co.pjm.diving.web.common.exception.EnjoyDivingWebException;
import kr.co.pjm.diving.web.common.security.service.CertificationService;
import kr.co.pjm.diving.web.domain.dto.DiveLogDto;
import kr.co.pjm.diving.web.service.DiveLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiveLogServiceImpl implements DiveLogService {
  
  @Autowired
  private DiveLogApiService diveLogApiService;
  
  @Override
  public ResourcesDto getAll(SearchDto searchDto, PagingDto pagingDto) throws Exception {
    // TODO : paging, search
    String sorts = StringUtils.EMPTY;
    String q = StringUtils.EMPTY;
    
    ApiReponseDto apiReponseDto = diveLogApiService.getDiveLogs(sorts, q);
    if (apiReponseDto.getStatus() != HttpStatus.OK.value()) {
      throw new EnjoyDivingWebException(apiReponseDto.getData());
    };
    
    return (ResourcesDto) apiReponseDto.getData();
  }

  @Override
  public void set(DiveLogDto diveLogDto) throws Exception {
    diveLogDto.setRegId(CertificationService.getId());
    
    ApiReponseDto apiReponseDto = diveLogApiService.createDiveLog(diveLogDto);
    if (apiReponseDto.getStatus() != HttpStatus.CREATED.value()) {
      throw new EnjoyDivingWebException(apiReponseDto.getData());
    }
  }

  @Override
  public ResourcesDto getById(Long id) throws Exception {
    if (log.isInfoEnabled()) {
      log.info("id : {}", id);
    }
    
    ApiReponseDto apiReponseDto = diveLogApiService.getDiveLog(id);
    if (apiReponseDto.getStatus() != HttpStatus.OK.value()) {
      throw new EnjoyDivingWebException(apiReponseDto.getData());
    };
    
    return new ResourcesDto(apiReponseDto.getData());
  }
  
  @Override
  public void update(Long id, DiveLogDto diveLogDto) throws Exception {
    diveLogDto.setUpdateId(CertificationService.getId());
    
    ApiReponseDto apiReponseDto = diveLogApiService.updateDiveLog(id, diveLogDto);
    if (apiReponseDto.getStatus() != HttpStatus.OK.value()) {
      throw new EnjoyDivingWebException(apiReponseDto.getData());
    };
  }

  @Override
  public void delete(Long id) throws Exception {
    ApiReponseDto apiReponseDto = diveLogApiService.deleteDiveLog(id);
    if (apiReponseDto.getStatus() != HttpStatus.NO_CONTENT.value()) {
      throw new EnjoyDivingWebException(apiReponseDto.getData());
    }
  }
  
}
