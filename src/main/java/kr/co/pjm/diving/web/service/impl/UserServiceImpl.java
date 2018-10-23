package kr.co.pjm.diving.web.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.web.api.dto.ApiReponseDto;
import kr.co.pjm.diving.web.api.service.UserApiService;
import kr.co.pjm.diving.web.common.exception.EnjoyDivingWebException;
import kr.co.pjm.diving.web.domain.dto.UserDto;
import kr.co.pjm.diving.web.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
  
  @Autowired
  private UserApiService userApiService;

  @Override
  public void set(UserDto userDto) throws Exception {
    ApiReponseDto apiReponseDto = userApiService.createUser(userDto);
    if (apiReponseDto.getStatus() != HttpStatus.CREATED.value()) {
      throw new EnjoyDivingWebException(apiReponseDto.getData());
    }
  }

  @Override
  public User getById(Long id) throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("id : {}", id);
    }
    
    ApiReponseDto apiReponseDto = userApiService.getUser(id);
    if (apiReponseDto.getStatus() != HttpStatus.OK.value()) {
      throw new EnjoyDivingWebException(apiReponseDto.getData());
    };
    
    return (User) apiReponseDto.getData();
  }

  @SuppressWarnings("unchecked")
  @Override
  public User getByEmail(String email) throws Exception {
    // TODO : paging, search
    String sorts = StringUtils.EMPTY;
    String q = "email=" + email;
    
    ApiReponseDto apiReponseDto = userApiService.getUsers(sorts, q);
    if (apiReponseDto.getStatus() != HttpStatus.OK.value()) {
      throw new EnjoyDivingWebException(apiReponseDto.getData());
    };
    
    List<User> users = (List<User>) apiReponseDto.getData();
    
    return users.get(0);
  }

  @Override
  public void update(Long id, UserDto userDto) throws Exception {
    ApiReponseDto apiReponseDto = userApiService.updateUser(id, userDto);
    if (apiReponseDto.getStatus() != HttpStatus.OK.value()) {
      throw new EnjoyDivingWebException(apiReponseDto.getData());
    };
  }

  @Override
  public void delete(Long id) throws Exception{
    ApiReponseDto apiReponseDto = userApiService.deleteUser(id);
    if (apiReponseDto.getStatus() != HttpStatus.NO_CONTENT.value()) {
      throw new EnjoyDivingWebException(apiReponseDto.getData());
    }
  }

  @Override
  public void updateLoginDate(Long id) throws Exception {
    ApiReponseDto apiReponseDto = userApiService.updateUserLoginDate(id);
    if (apiReponseDto.getStatus() != HttpStatus.OK.value()) {
      throw new EnjoyDivingWebException(apiReponseDto.getData());
    };
  }

}
