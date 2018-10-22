package kr.co.pjm.diving.web.service;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.Iterator;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.common.domain.entity.UserRole;
import kr.co.pjm.diving.common.domain.enumeration.GenderEnum;
import kr.co.pjm.diving.web.common.security.service.SecurityService;
import kr.co.pjm.diving.web.domain.dto.LoginDto;
import kr.co.pjm.diving.web.domain.dto.UserDto;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.service
 * @Class Name : UserServiceTest.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 9.
 * @Version : 1.0
 * @Description : 유저 서비스 테스트
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
public class UserServiceTest {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private SecurityService securityService;
  
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Test
  @Ignore
  public void testSet() throws Exception {
    UserDto userDto = new UserDto();
    userDto.setEmail("tapjm@naver.com");
    userDto.setPassword("1234");
    userDto.setCountry("Korea");
    userDto.setName("박재명");
    userDto.setNickname("코비");
    userDto.setGender(GenderEnum.MALE);
    userDto.setLoginDate(new Date());
    userDto.setIntroduce("반가워요");
    
    userService.set(userDto);
  }
  
  @Test
  @Ignore
  public void testLogin() throws Exception {
    LoginDto loginDto = new LoginDto();
    loginDto.setEmail("kbtapjm@gmail.com");
    loginDto.setPassword("1234");
    
    securityService.login(loginDto);
    
    String loginUser = securityService.getLoginUser();
    log.debug("loginUser : {}", loginUser);
  }
  
  @Test
  @Transactional(readOnly = true)
  @Ignore
  public void testGetById() throws Exception {
    Long id = (long) 1;
    
    User user = userService.getById(id);
    
    Iterator<UserRole> itr = user.getUserRoles().iterator();
    while (itr.hasNext()) {
      UserRole userRole = itr.next();
      
      log.debug("role : {}", userRole.getRole().getRole().getCode());
    }
    
    assertNotNull(user);
  }
  
  @Test
  @Transactional(readOnly = true)
  @Ignore
  public void testGetByEmail() throws Exception {
    String email = "kbtapjm@gmail.com";
    User user = userService.getByEmail(email);
    
    assertNotNull(user);
  }
  
  @Test
  @Ignore
  public void testUpdate() throws Exception {
    Long id = (long) 1;
    
    UserDto userDto = new UserDto();
    userDto.setId(id);
    userDto.setName("박재명 222");
    userDto.setNickname("검은몽스 222");
    userDto.setCountry("Korea 222");
    userDto.setGender(GenderEnum.FEMALE);
    userDto.setIntroduce("반가워요 222");
    
    userService.update(id, userDto);
  }
  
  @Test
  @Ignore
  public void testDelete() throws Exception {
    Long id = (long) 1;
    userService.delete(id);
  }
  
  @Test
  public void testGetPassword()throws Exception {
    log.debug("passwordEncoder : {}", passwordEncoder.encode("111222"));
  }

}
