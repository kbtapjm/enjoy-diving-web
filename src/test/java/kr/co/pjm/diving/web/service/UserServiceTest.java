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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.co.pjm.diving.common.domain.enumeration.GenderEnum;
import kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum;
import kr.co.pjm.diving.web.domain.dto.UserDto;
import kr.co.pjm.diving.web.domain.entity.User;
import kr.co.pjm.diving.web.domain.entity.UserRole;
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
  
  @Test
  public void testSet() throws Exception {
    UserDto userDto = new UserDto();
    userDto.setEmail("kbtapjm@gmail.com");
    userDto.setPassword("1234");
    userDto.setCountry("Korea");
    userDto.setName("박재명");
    userDto.setNickname("검은몽스");
    userDto.setGender(GenderEnum.MALE);
    userDto.setStatus(UserStatusEnum.NORMAL);
    userDto.setLoginDate(new Date());
    userDto.setIntroduce("반가워요");
    
    userService.set(userDto);
  }
  
  @Test
  @Transactional(readOnly = true)
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
  public void testGetByEmail() throws Exception {
    String email = "kbtapjm@gmail.com";
    User user = userService.getByEmail(email);
    
    assertNotNull(user);
  }
  
  @Test
  public void testUpdate() throws Exception {
    Long id = (long) 1;
    
    UserDto userDto = new UserDto();
    userDto.setId(id);
    userDto.setName("박재명 222");
    userDto.setNickname("검은몽스 222");
    userDto.setCountry("Korea 222");
    userDto.setGender(GenderEnum.FEMALE);
    userDto.setIntroduce("반가워요 222");
    
    userService.update(userDto);
  }
  
  @Test
  @Ignore
  public void testDelete() throws Exception {
    Long id = (long) 1;
    userService.delete(id);
  }
  

}
