package kr.co.pjm.diving.web.service;

import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.web.domain.dto.UserDto;

public interface UserService {
  
  void set(UserDto userDto) throws Exception;
  
  User getById(Long id) throws Exception;
  
  User getByEmail(String email) throws Exception;
  
  void update(Long id, UserDto userDto) throws Exception;
  
  void delete(Long id) throws Exception;
  
  void updateLoginDate(Long id) throws Exception;
  
}
