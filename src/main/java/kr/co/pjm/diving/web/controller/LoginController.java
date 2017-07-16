package kr.co.pjm.diving.web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.controller
 * @Class Name : LoginController.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 4.
 * @Version : 1.0
 * @Description : 로그인 컨트롤러
 *
 */
@Slf4j
@Validated
@Controller
@RequestMapping(value = LoginController.RESOURCE_PATH)
public class LoginController {
  
  static final String RESOURCE_PATH = "/login";
  
  /**
   * 로그인 페이지
   * @param principal
   * @param error
   * @param model
   * @return
   */
  @GetMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String index(final Principal principal, @RequestParam(value = "error", required = false, defaultValue = "none") String error, Model model) {
    if (null == principal || null == principal.getName()) {
      model.addAttribute("error", error);
      
      return "content/login";
    }

    return "redirect:/";
  }
  
  /**
   * 로그인 처리(API형식)
   * @return
   */
  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody @ResponseStatus(HttpStatus.OK)
  public Map<String, Object> login() {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    return resultMap;
  }

}
