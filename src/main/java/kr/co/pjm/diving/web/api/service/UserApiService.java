package kr.co.pjm.diving.web.api.service;

import kr.co.pjm.diving.web.api.dto.ApiReponseDto;
import kr.co.pjm.diving.web.domain.dto.UserDto;

public interface UserApiService {
  
  ApiReponseDto createUser(UserDto userDto);
  
  ApiReponseDto getUser(Long id);
  
  ApiReponseDto updateUser(Long id, UserDto userDto);
  
  ApiReponseDto deleteUser(Long id);
  
}
