package kr.co.pjm.diving.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pjm.diving.common.domain.enumeration.RoleTypeEnum;
import kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum;
import kr.co.pjm.diving.web.domain.dto.UserBasicDto;
import kr.co.pjm.diving.web.domain.dto.UserDiveDto;
import kr.co.pjm.diving.web.domain.dto.UserDto;
import kr.co.pjm.diving.web.domain.entity.Role;
import kr.co.pjm.diving.web.domain.entity.User;
import kr.co.pjm.diving.web.domain.entity.UserBasic;
import kr.co.pjm.diving.web.domain.entity.UserRole;
import kr.co.pjm.diving.web.repasitory.RoleRepository;
import kr.co.pjm.diving.web.repasitory.UserBasicRepository;
import kr.co.pjm.diving.web.repasitory.UserDiveRepository;
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
  private UserDiveRepository userDiveRepository;
  
  @Autowired
  private RoleRepository roleRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public User set(UserDto userDto) {
    User user = new User();
    user.setEmail(userDto.getEmail());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    
    /* 유저 기본 등록 */
    UserBasic userBasic = new UserBasic();
    userBasic.setName(userDto.getName());
    userBasic.setNickname(userDto.getNickname());
    userBasic.setGender(userDto.getGender());
    userBasic.setCountry(userDto.getCountry());
    userBasic.setStatus(UserStatusEnum.NORMAL);
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
    User retUser = userRepository.save(user);
    
    return retUser;
  }

  @Override
  public User getById(Long id) {
    return userRepository.findOne(id);
  }

  @Override
  public User getByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  @Transactional
  public void update(UserDto userDto) {
    
    /* 유저 기본 수정 */
    UserBasicDto userBasicDto = new UserBasicDto();
    userBasicDto.setId(userDto.getId());
    userBasicDto.setName(userDto.getName());
    userBasicDto.setNickname(userDto.getNickname());
    userBasicDto.setCountry(userDto.getCountry());
    userBasicDto.setGender(userDto.getGender());
    userBasicDto.setIntroduce(userDto.getIntroduce());
    
    Long result = userBasicRepository.updateUserBasic(userBasicDto);
    log.debug("===> update result : {}", result);
    
    /* 유저 다이브 수정 */
    UserDiveDto userDiveDto = new UserDiveDto();
    userDiveDto.setId(userDto.getId());
    userDiveDto.setDiveGroup(userDto.getDiveGroup());
    userDiveDto.setDiveLevel(userDto.getDiveLevel());
    userDiveDto.setTeam(userDto.getTeam());
    userDiveDto.setSignature(userDto.getSignature());
    
    Long result2 = userDiveRepository.updateUserDive(userDiveDto);
    log.debug("===> update result2 : {}", result2);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    userRepository.delete(id);
    userBasicRepository.delete(id);
  }

}
