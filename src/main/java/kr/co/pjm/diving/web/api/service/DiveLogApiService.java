package kr.co.pjm.diving.web.api.service;

import kr.co.pjm.diving.web.api.dto.ApiReponseDto;
import kr.co.pjm.diving.web.domain.dto.DiveLogDto;

public interface DiveLogApiService {
  
  ApiReponseDto getDiveLogs(String sorts, String q);
  
  ApiReponseDto createDiveLog(DiveLogDto diveLogDto);
  
  ApiReponseDto getDiveLog(Long id);
  
  ApiReponseDto updateDiveLog(Long id, DiveLogDto diveLogDto);
  
  ApiReponseDto deleteDiveLog(Long id);
  
  ApiReponseDto deleteDiveLogByUser(Long userId);
  
}
