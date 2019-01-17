package kr.co.pjm.diving.web.common.recapcha.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import kr.co.pjm.diving.web.common.recapcha.util.RecaptchaUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecaptchaService {
    
    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    
    @Value("${props.google.recaptcha.secret}") 
    private String recaptchaSecret;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @SuppressWarnings("unchecked")
    public String verifyRecaptcha(String ip, String recaptchaResponse) {
        Map<String, String> body = new HashMap<>();
        body.put("secret", recaptchaSecret);
        body.put("response", recaptchaResponse);
        body.put("remoteip", ip);
        
        ResponseEntity<Map> recaptchaResponseEntity = restTemplate
                .postForEntity(GOOGLE_RECAPTCHA_VERIFY_URL + "?secret={secret}&response={response}&remoteip={remoteip}", body, Map.class, body);
                       
        log.debug("Response from recaptcha: {}", recaptchaResponseEntity);
        Map<String, Object> responseBody = recaptchaResponseEntity.getBody();
        
        boolean recaptchaSucess = (Boolean)responseBody.get("success");
        if (!recaptchaSucess) {
            List<String> errorCodes =  (List<String>) responseBody.get("error-codes");
           
            String errorMessage = errorCodes.stream().map(s -> RecaptchaUtil.RECAPTCHA_ERROR_CODE.get(s))
              .collect(Collectors.joining(", "));
               
          return errorMessage;
        } else {
          return StringUtils.EMPTY;
        }
    }

}
