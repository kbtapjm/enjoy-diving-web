package kr.co.pjm.diving.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import kr.co.pjm.diving.web.domain.dto.UserDto;
import kr.co.pjm.diving.web.domain.entity.User;
import kr.co.pjm.diving.web.service.UserService;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.controller
 * @Class Name : UserController.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 15.
 * @Version : 1.0
 * @Description : 유저 컨트롤러 
 *
 */
@Controller
@RequestMapping(value = UserController.RESOURCE_PATH)
public class UserController {
  
  static final String RESOURCE_PATH = "/users";
  
  @Autowired
  private UserService userService;
  
  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public User create(@RequestBody UserDto userDto, UriComponentsBuilder uriComponentsBuilder) {
    // TODO: validation check
    
    // TODO: HTTP STATUS 302로 리턴
    
    User user = userService.set(userDto);
    
    // TODO: JSON 변환시 에러 처리
    
    return user;
  }
  
  @RequestMapping(value = "{id}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String createPage(@PathVariable("id") String id, Model model) {
    
    if ("new".equals(id)) {
     
    } else {
      
    }

    return "content/user-create";
  }

}
