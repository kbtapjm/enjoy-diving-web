package kr.co.pjm.diving.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.controller
 * @Class Name : LogController.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 11. 27.
 * @Version : 1.0
 * @Description : 다이빙 로그 컨트롤러
 *
 */
@Slf4j
@Controller
@RequestMapping(value = LogController.RESOURCE_PATH)
public class LogController {
  
  static final String RESOURCE_PATH = "/log";
  
  @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String readPage(Model model) {
    
    return "content/log-list";
  }

}
