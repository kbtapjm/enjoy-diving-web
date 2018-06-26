package kr.co.pjm.diving.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.pjm.diving.web.domain.entity.User;
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

}
