package kr.co.pjm.diving.web.controller;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@Controller
@RequestMapping(value = LoginController.RESOURCE_PATH)
public class LoginController {
  
  static final String RESOURCE_PATH = "/login";
  
  @GetMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String index(final Principal principal, @RequestParam(value = "error", required = false, defaultValue = "none") String error, Model model) {
    if (null == principal || null == principal.getName()) {
      model.addAttribute("error", error);
      
      return "content/login";
    }

    return "redirect:/";
  }

}
