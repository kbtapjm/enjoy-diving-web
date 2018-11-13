package kr.co.pjm.diving.web.common.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    if (isAjaxRequest(request)) {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    } else {
      response.sendRedirect("/login");
    }
  }
  
  private boolean isAjaxRequest(HttpServletRequest req) {
    return req.getHeader("X-Requested-With") != null && req.getHeader("X-Requested-With").equals("XMLHttpRequest");
  }

}
