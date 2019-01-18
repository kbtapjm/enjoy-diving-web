package kr.co.pjm.diving.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.common.domain.entity.UserRole;
import kr.co.pjm.diving.common.util.CommonUtil;
import kr.co.pjm.diving.web.common.enumeration.Result;
import kr.co.pjm.diving.web.common.recapcha.service.RecaptchaService;
import kr.co.pjm.diving.web.common.security.model.SocialUserDetail;
import kr.co.pjm.diving.web.domain.dto.UserDto;
import kr.co.pjm.diving.web.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SignUpController {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private RecaptchaService recaptchaService;
  
  @Autowired
  private ProviderSignInUtils providerSignInUtils;

  @GetMapping(value = "/signup", consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String redirectRequestToRegistrationPage(WebRequest request, Model model,
      @RequestParam(value = "type", required = false) String type) throws Exception {
    
    Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
    if (!StringUtils.isEmpty(connection) && type == null) {
      UserProfile userProfile = connection.fetchUserProfile();
      
      if (log.isDebugEnabled()) {
        log.debug("getDisplayName : {}" + connection.getDisplayName());
        log.debug("getApi : {}", connection.getApi());
        log.debug("getProviderId : {}", connection.getKey().getProviderId());
        log.debug("getProviderUserId : {}", connection.getKey().getProviderUserId());  
      }
      
      /** user duplicate check */
      User user = userService.getByEmail(userProfile.getEmail());
      if (user != null) {
        return "redirect:/login?error=duplicate";
      }
      
      String email = !StringUtils.isEmpty(userProfile.getEmail()) ? userProfile.getEmail() : "";
      String firstName = !StringUtils.isEmpty(userProfile.getFirstName()) ? userProfile.getFirstName() : "";
      String lastName = !StringUtils.isEmpty(userProfile.getLastName()) ? userProfile.getLastName() : "";
      
      UserDto userDto = UserDto.builder()
          .email(email)
          .name(firstName + lastName)
          .provider(connection.getKey().getProviderId())
          .build(); 
      
      model.addAttribute("user", userDto);  
    } else {
      model.addAttribute("user", new UserDto());
    }
    
    return "content/signup";
  }
  
  @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Map<String, Object> signupAndLogin(@RequestBody UserDto userDto, WebRequest request) throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    try {
      /** recapcha check */
      String recapchaResult = recaptchaService.verifyRecaptcha(CommonUtil.getInstance().getRemoteIp(), userDto.getToken());
      if (!StringUtils.isEmpty(recapchaResult)) {
        throw new Exception("잘못된 접근입니다. (" + recapchaResult + ")");
      }
        
      /** user duplicate check */
      User isEmailUser = userService.getByEmail(userDto.getEmail());
      if (isEmailUser != null) {
        throw new Exception(userDto.getEmail() + "은(는) 중복되어 사용 할 수가 없습니다.");
      }
      
      /** user create */
      userService.set(userDto);
      
      /** get user */
      User user = userService.getByEmail(userDto.getEmail());
      
      /** social signUp */
      if (!StringUtils.isEmpty(userDto.getProvider())) {
        providerSignInUtils.doPostSignUp(user.getEmail(), request);  
      }
      
      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
      Iterator<UserRole> itr = user.getUserRoles().iterator();
      while (itr.hasNext()) {
        UserRole userRole = itr.next();
        
        authorities.add(new SimpleGrantedAuthority(userRole.getRole().getRole().name()));
      }
      
      /** social signIn */
      SocialUserDetail socialUserDetail = new SocialUserDetail(user);
      socialUserDetail.setAuthorities(authorities);
      
      Authentication authentication = new UsernamePasswordAuthenticationToken(socialUserDetail, null, socialUserDetail.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
      
      resultMap.put("resultCd", Result.SUCCESS.getCd());
    } catch (Exception e) {
      e.printStackTrace();
      
      resultMap.put("resultCd", Result.FAIL.getCd());
      resultMap.put("resultMsg", e.getMessage());
    }
    
    return resultMap;
  }

}
