package kr.co.pjm.diving.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.controller
 * @Class Name : ErrorController.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 12. 4.
 * @Version : 1.0
 * @Description : 에러처리 컨트롤러
 *
 */
@Slf4j
@Controller
@RequestMapping(value = ErrorController.RESOURCE_PATH)
public class ErrorController {
  
  static final String RESOURCE_PATH = "/error";
  
  /**
   * 401 unauthorized
   * @return
   */
  @GetMapping(value = "/unauthorized")
  public String unauthorized() {
      return "common/error/unauthorized";
  }
  
  /**
   * 403 access denied
   * @return
   */
  @GetMapping(value = "/accessDenied")
  public String accessDenied() {
      return "common/error/accessDenied";
  }
  
  /**
   * 404 not fount
   * @return
   */
  @GetMapping(value = "/notFound")
  public String notFound() {
      return "common/error/notFound";
  }
  
  /**
   * 500 internal server error
   * @return
   */
  @GetMapping(value = "/serverError")
  public String serverError() {
      return "common/error/serverError";
  }

}
