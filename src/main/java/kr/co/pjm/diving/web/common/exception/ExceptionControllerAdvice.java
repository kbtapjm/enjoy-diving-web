package kr.co.pjm.diving.web.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(value = EnjoyDivingWebException.class)
  public ModelAndView handlerException(HttpServletRequest req, EnjoyDivingWebException e) {
      if (log.isInfoEnabled()) {
          log.info(" ***************************************************************************** ");
          log.info("url : {}", req.getRequestURL());
          log.info("ErrCd : {}", e.getErrCd());
          log.info("ErrMsg : {}", e.getErrMsg());
          log.info(" ***************************************************************************** ");
      }
      
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("msg", EnjoyDivingWebException.getExceptionMsg(e));
      modelAndView.setViewName("common/error/serverError");
      
      return modelAndView;
  }
}
