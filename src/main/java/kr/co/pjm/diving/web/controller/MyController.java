package kr.co.pjm.diving.web.controller;

import java.security.Principal;

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
import kr.co.pjm.diving.web.domain.dto.UserDto;
import kr.co.pjm.diving.web.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = MyController.RESOURCE_PATH)
public class MyController {
  
static final String RESOURCE_PATH = "/my";
  
  @Autowired
  private UserService userService;
  
  @GetMapping(value = "/profile", consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String readPage(Model model, Principal principal) {
    
    User user = userService.getByEmail(principal.getName());
    model.addAttribute("user", user);
    
    return "content/my-profile";
  }
  
  @PutMapping(value = "/updateProfile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  @ResponseStatus(HttpStatus.OK)
  public void updateProfile(@RequestBody UserDto userDto) throws Exception {
    userService.update(userDto);
  }
  
  @DeleteMapping(value = "/profile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removeProfile(@PathVariable("id") long id) {
    userService.delete(id);
  }

}
