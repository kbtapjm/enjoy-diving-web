package kr.co.pjm.diving.web.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pjm.diving.common.domain.dto.UserBasicDto;
import kr.co.pjm.diving.common.domain.dto.UserDiveDto;
import kr.co.pjm.diving.common.domain.dto.UserDto;
import kr.co.pjm.diving.common.domain.entity.Role;
import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.common.domain.entity.UserBasic;
import kr.co.pjm.diving.common.domain.entity.UserDive;
import kr.co.pjm.diving.common.domain.entity.UserRole;
import kr.co.pjm.diving.common.domain.enumeration.RoleTypeEnum;
import kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum;
import kr.co.pjm.diving.common.repository.RoleRepository;
import kr.co.pjm.diving.common.repository.UserBasicRepository;
import kr.co.pjm.diving.common.repository.UserConnectionRepasitory;
import kr.co.pjm.diving.common.repository.UserDiveRepository;
import kr.co.pjm.diving.common.repository.UserRepository;
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
  private UserConnectionRepasitory userConnectionRepasitory;
  
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
    
    /* 유저 다이브 등록 */
    UserDive userDive = new UserDive();
    userDive.setDiveLevel(StringUtils.EMPTY);
    userDive.setDiveGroup(StringUtils.EMPTY);
    userDive.setTeam(StringUtils.EMPTY);
    userDive.setSignature(StringUtils.EMPTY);
    
    userDiveRepository.save(userDive);
    
    user.setUserDive(userDive);
    
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
    
    Long updateUserBasic = userBasicRepository.updateUserBasic(userBasicDto);
    log.debug("===> update updateUserBasic : {}", updateUserBasic);
    
    /* 유저 다이브 수정 */
    UserDiveDto userDiveDto = new UserDiveDto();
    userDiveDto.setId(userDto.getId());
    userDiveDto.setDiveGroup(userDto.getDiveGroup());
    userDiveDto.setDiveLevel(userDto.getDiveLevel());
    userDiveDto.setTeam(userDto.getTeam());
    userDiveDto.setSignature(userDto.getSignature());
    
    Long updateUserDive = userDiveRepository.updateUserDive(userDiveDto);
    log.debug("===> update updateUserDive : {}", updateUserDive);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    User user = userRepository.findOne(id);
    
    userBasicRepository.delete(id);
    userDiveRepository.delete(id);
    userRepository.delete(id);
    userConnectionRepasitory.deleteUserConnection(user.getEmail());
  }

  @Override
  @Transactional
  public void updateLoginDate(UserBasicDto userBasicDto) {
    userBasicRepository.updateLoginDate(userBasicDto);
  }

}
