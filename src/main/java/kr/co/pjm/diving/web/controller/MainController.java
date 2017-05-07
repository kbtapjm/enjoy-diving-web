package kr.co.pjm.diving.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.controller
 * @Class Name : MainController.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 5. 6.
 * @Version : 1.0
 * @Description : 
 *
 */
@Controller
@RequestMapping(value = MainController.RESOURCE_PATH)
public class MainController {
  
  static final String RESOURCE_PATH = "/";

  @GetMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String index() {
    return "content/main";
  }

}
