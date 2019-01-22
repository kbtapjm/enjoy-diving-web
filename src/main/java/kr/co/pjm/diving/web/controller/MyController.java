package kr.co.pjm.diving.web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.common.domain.enumeration.UserStatusEnum;
import kr.co.pjm.diving.web.common.enumeration.Result;
import kr.co.pjm.diving.web.common.security.component.CertificationUser;
import kr.co.pjm.diving.web.domain.dto.UserDto;
import kr.co.pjm.diving.web.service.UserService;

@Controller
@RequestMapping(value = MyController.RESOURCE_PATH)
public class MyController {
  
static final String RESOURCE_PATH = "/my";
  
  @Autowired
  private UserService userService;
  
  @GetMapping(value = "/profile", consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String readPage(Model model, Principal principal) throws Exception {
    User user = userService.getByEmail(principal.getName());
    model.addAttribute("user", user);
    
    return "content/my-profile";
  }
  
  @GetMapping(value = "/pwd", consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String myPwd(Model model) throws Exception {
    return "content/my-pwd";
  }
  
  @PutMapping(value = "/profile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  public Map<String, Object> updateProfile(@PathVariable("id") Long id, @RequestBody UserDto userDto) throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    try {
      userService.update(id, userDto);
      
      resultMap.put("resultCd", Result.SUCCESS.getCd());
    } catch (Exception e) {
      e.printStackTrace();
      
      resultMap.put("resultCd", Result.FAIL.getCd());
      resultMap.put("resultMsg", e.getMessage());
    }
    
    return resultMap;
  }
  
  @DeleteMapping(value = "/profile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  public Map<String, Object> deleteProfile(@PathVariable("id") Long id) {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    try {
      UserDto dto = UserDto.builder().status(UserStatusEnum.WITHDRAWAL).build();
      userService.updateStatus(id, dto);
      
      resultMap.put("resultCd", Result.SUCCESS.getCd());
    } catch (Exception e) {
      e.printStackTrace();
      
      resultMap.put("resultCd", Result.FAIL.getCd());
      resultMap.put("resultMsg", e.getMessage());
    }
    
    return resultMap;
  }
  
  @PutMapping(value = "/pwd", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  public Map<String, Object> updatePassword(@RequestBody UserDto.Password dto) throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    try {
      userService.updatePassword(CertificationUser.getLongId(), dto);
      
      resultMap.put("resultCd", Result.SUCCESS.getCd());
    } catch (Exception e) {
      e.printStackTrace();
      
      resultMap.put("resultCd", Result.FAIL.getCd());
      resultMap.put("resultMsg", e.getMessage());
    }
    
    return resultMap;
  }

}
