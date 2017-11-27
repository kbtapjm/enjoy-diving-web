package kr.co.pjm.diving.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.controller
 * @Class Name : StoryController.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 11. 27.
 * @Version : 1.0
 * @Description : 다이빙 이야기 컨트롤러
 *
 */
@Slf4j
@Controller
@RequestMapping(value = StoryController.RESOURCE_PATH)
public class StoryController {
  
  static final String RESOURCE_PATH = "/story";
  
  @RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String readPage(Model model) {
    
    return "content/story-list";
  }

}
