package kr.co.pjm.diving.web.api.service;

import kr.co.pjm.diving.web.api.dto.ApiReponseDto;
import kr.co.pjm.diving.web.domain.dto.UserDto;

public interface UserApiService {
  
  ApiReponseDto getUsers(String sorts, String q);
  
  ApiReponseDto createUser(UserDto userDto);
  
  ApiReponseDto getUser(Long id);
  
  ApiReponseDto updateUser(Long id, UserDto userDto);
  
  ApiReponseDto deleteUser(Long id);
  
  ApiReponseDto updateUserLoginDate(Long id);
  
  ApiReponseDto getUserByEmail(String email);
  
  ApiReponseDto updateUserPassword(Long id, UserDto.Password dto);
  
  ApiReponseDto updateUserStatus(Long id, UserDto dto);
  
}
