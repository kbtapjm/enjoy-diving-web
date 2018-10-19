package kr.co.pjm.diving.web.service;

import kr.co.pjm.diving.common.domain.dto.PagingDto;
import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.common.domain.dto.SearchDto;
import kr.co.pjm.diving.web.domain.dto.DiveLogDto;

public interface DiveLogService {
  
  ResourcesDto getAll(SearchDto searchDto, PagingDto pagingDto) throws Exception;
  
  void set(DiveLogDto diveLogDto) throws Exception;
  
  ResourcesDto getById(Long id) throws Exception;
  
  void update(Long id, DiveLogDto diveLogDto) throws Exception;
  
  void delete(Long id) throws Exception;
  
}
