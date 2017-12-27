package kr.co.pjm.diving.web.common.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Package Name : kr.co.pjm.diving.web.common.security.filter
 * @Class Name : CORSFilter.java
 * </pre>
 * 
 * @author : jmpark
 * @Date : 2017. 12. 27.
 * @Version : 1.0
 * @Description : 
 *
 */
@Slf4j
public class CORSFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    
    response.addHeader("Access-Control-Allow-Origin", "*");
    
    if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
      log.trace("Sending Header....");
      // CORS "pre-flight" request
      response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
      //response.addHeader("Access-Control-Allow-Headers", "Authorization");
      response.addHeader("Access-Control-Allow-Headers", "Content-Type");
      response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
      response.addHeader("Access-Control-Max-Age", "1");
    }
  
    filterChain.doFilter(request, response);
  }
  
}
