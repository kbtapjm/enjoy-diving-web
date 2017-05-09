package kr.co.pjm.diving.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pjm.diving.common.domain.enumeration.RoleTypeEnum;
import kr.co.pjm.diving.web.domain.dto.UserBasicDto;
import kr.co.pjm.diving.web.domain.dto.UserDto;
import kr.co.pjm.diving.web.domain.entity.Role;
import kr.co.pjm.diving.web.domain.entity.User;
import kr.co.pjm.diving.web.domain.entity.UserBasic;
import kr.co.pjm.diving.web.domain.entity.UserRole;
import kr.co.pjm.diving.web.repasitory.RoleRepository;
import kr.co.pjm.diving.web.repasitory.UserBasicRepository;
import kr.co.pjm.diving.web.repasitory.UserRepository;
import kr.co.pjm.diving.web.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.service.impl
 * @Class Name : UserServiceImpl.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 8.
 * @Version : 1.0
 * @Description : 유저 서비스 구현 클래스
 *
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private UserBasicRepository userBasicRepository;
  
  @Autowired
  private RoleRepository roleRepository;
  
  @Autowired
  private ShaPasswordEncoder shaPasswordEncoder;

  @Override
  @Transactional
  public void set(UserDto userDto) throws Exception {
    User user = new User();
    user.setEmail(userDto.getEmail());
    
    String encodePassword = shaPasswordEncoder.encodePassword(userDto.getPassword(), null); // TODO salt 추가 확인 필요
    user.setPassword(encodePassword);
    
    /* 유저 기본 등록 */
    UserBasic userBasic = new UserBasic();
    userBasic.setName(userDto.getName());
    userBasic.setNickname(userDto.getNickname());
    userBasic.setGender(userDto.getGender());
    userBasic.setCountry(userDto.getCountry());
    userBasic.setStatus(userDto.getStatus());
    userBasic.setIntroduce(userDto.getIntroduce());
    
    userBasicRepository.save(userBasic);
    
    user.setUserBasic(userBasic);
    
    /* 롤 정보 조회 */
    Role role = roleRepository.findByRole(RoleTypeEnum.USER);
    
    UserRole userRole = new UserRole();
    userRole.setUser(user);
    userRole.setRole(role);
    
    user.getUserRoles().add(userRole);
    
    /* 유저 등록 */
    userRepository.save(user);
  }

  @Override
  public User getById(Long id) throws Exception {
    return userRepository.findOne(id);
  }

  @Override
  public User getByEmail(String email) throws Exception {
    return userRepository.findByEmail(email);
  }

  @Override
  @Transactional
  public void update(UserDto userDto) throws Exception {
    
    UserBasicDto userBasicDto = new UserBasicDto();
    userBasicDto.setId(userDto.getId());
    userBasicDto.setName(userDto.getName());
    userBasicDto.setNickname(userDto.getNickname());
    userBasicDto.setCountry(userDto.getCountry());
    userBasicDto.setGender(userDto.getGender());
    userBasicDto.setIntroduce(userDto.getIntroduce());
    
    Long result = userBasicRepository.updateUserBasic(userBasicDto);
    
    // TODO 유저 다이브 정보 수정
    
    log.debug("===> update result : {}", result);
  }

  @Override
  @Transactional
  public void delete(Long id) throws Exception {
    userRepository.delete(id);
    userBasicRepository.delete(id);
  }

}
