package kr.co.pjm.diving.web.common.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionTimeoutFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    
    if (log.isDebugEnabled()) {
      log.debug("*** req ajax check : {}, {}", isAjaxRequest(req), req.getHeader("X-Requested-With"));
    }

    if (isAjaxRequest(req)) {
      try {
        log.debug("getMessage qqqqqqqqqqqqqqqqqqqqqqqqqq");
        chain.doFilter(req, res);
      } catch (AccessDeniedException e) {
        log.debug("getMessage : {}", e.getMessage());
        res.sendError(HttpServletResponse.SC_FORBIDDEN);
      } catch (AuthenticationException e) {
        log.debug("getMessage : {}", e.getMessage());
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      }
    } else {
      chain.doFilter(req, res);
    }

  }

  @Override
  public void destroy() {}

  private boolean isAjaxRequest(HttpServletRequest req) {
    return req.getHeader("X-Requested-With") != null && req.getHeader("X-Requested-With").equals("XMLHttpRequest");
  }

}
