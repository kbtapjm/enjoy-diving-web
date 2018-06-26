package kr.co.pjm.diving.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.co.pjm.diving.web.domain.dto.UserDto;
import kr.co.pjm.diving.web.domain.entity.User;
import kr.co.pjm.diving.web.service.UserService;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Controller
@RequestMapping(value = UserController.RESOURCE_PATH)
public class UserController {
  
  static final String RESOURCE_PATH = "/users";
  
  @Autowired
  private UserService userService;
  
  @GetMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String readPage(Model model) {
    return "content/user-list";
  }
  
  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  //@ResponseStatus(HttpStatus.CREATED)
  public User create(@RequestBody UserDto userDto) throws Exception {
    return userService.set(userDto);
  }
  
  @RequestMapping(value = "{id}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String createPage(@PathVariable("id") String id, Model model, Principal principal) {
    if (log.isDebugEnabled()) {
      log.debug("id : {}", id);
    }
    
    return "content/user-create";
  }
  
  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  @ResponseStatus(HttpStatus.OK)
  public void update(@RequestBody UserDto userDto) throws Exception {
    userService.update(userDto);
  }
  
  @RequestMapping(value = "{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") long id) {
    userService.delete(id);
  }
  
}
