package kr.co.pjm.diving.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.controller
 * @Class Name : AdminController.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 12. 4.
 * @Version : 1.0
 * @Description : 어드민 컨트롤러(관리자 요청 처리)
 *
 */
@Slf4j
@Controller
@RequestMapping(value = AdminController.RESOURCE_PATH)
public class AdminController {
  
  static final String RESOURCE_PATH = "/admin";
  
  /**
   * 사용자 목록 관리 페이지
   * @param model
   * @return
   */
  @GetMapping(value = "/user", consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String accessDenied(Model model) {
    return "content/admin/user-list";
  }

}
