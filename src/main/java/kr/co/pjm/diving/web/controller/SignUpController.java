package kr.co.pjm.diving.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import kr.co.pjm.diving.web.common.security.social.SocialUserDetail;
import kr.co.pjm.diving.web.domain.dto.UserDto;
import kr.co.pjm.diving.web.domain.entity.User;
import kr.co.pjm.diving.web.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SignUpController {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private ProviderSignInUtils providerSignInUtils;

  @GetMapping(value = "/signup", consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String redirectRequestToRegistrationPage(WebRequest request, Model model) {
    Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
    if (!StringUtils.isEmpty(connection)) {
      UserProfile userProfile = connection.fetchUserProfile();
      
      /* TODO: 이메일로 가입된 회원인지 중복 체크 로직 필요 */

      UserDto userDto = UserDto.fromSocialUserProfile(userProfile);
      model.addAttribute("user", userDto);  
    } else {
      model.addAttribute("user", new UserDto());
    }
    
    return "content/signup";
  }
  
  @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public void registrationUserAndLogin(@RequestBody UserDto userDto, WebRequest request) throws Exception {
    User user = userService.set(userDto);
    providerSignInUtils.doPostSignUp(user.getEmail(), request);
    
    SocialUserDetail socialUserDetail = new SocialUserDetail(user);
    
    Authentication authentication = new UsernamePasswordAuthenticationToken(socialUserDetail, null, socialUserDetail.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

}
