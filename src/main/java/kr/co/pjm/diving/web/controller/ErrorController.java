package kr.co.pjm.diving.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = ErrorController.RESOURCE_PATH)
public class ErrorController {
  
  static final String RESOURCE_PATH = "/error";
  
  @GetMapping(value = "/accessDenied")
  public String accessDenied() {
      return "common/error/accessDenied";
  }
  
  @GetMapping(value = "/notFound")
  public String notFound() {
      return "common/error/notFound";
  }
  
  @GetMapping(value = "/serverError")
  public String serverError() {
      return "common/error/serverError";
  }

}
