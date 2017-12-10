package kr.co.pjm.diving.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
   * 접근거부 에러(403)
   * @param model
   * @param principal
   * @return
   */
  @GetMapping(value = "/accessDenied")
  public String accessDenied(Model model, Principal principal) {
      if (log.isDebugEnabled()) {
        log.debug("====== Page : AccessDenied ===== ");
        log.debug("====== principal : {}", principal.getName());
      }
      
      return "common/error/accessDenied";
  }

}
