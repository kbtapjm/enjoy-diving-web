package kr.co.pjm.diving.web.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.pjm.diving.common.domain.dto.ErrorDto;
import kr.co.pjm.diving.common.domain.entity.User;
import kr.co.pjm.diving.web.api.dto.ApiReponseDto;
import kr.co.pjm.diving.web.api.service.UserApiService;
import kr.co.pjm.diving.web.domain.dto.UserDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserApiServiceImpl implements UserApiService {
  
  @Value("${props.apiBaseUrl}")
  private String apiBaseUrl;
  
  @Autowired
  private RestTemplate restTemplate;
  
  @SuppressWarnings("rawtypes")
  @Override
  public ApiReponseDto getUsers(String sorts, String q) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/users")
          .queryParam("sorts", sorts)
          .queryParam("q", q)
          .queryParam("offset", "0")
          .queryParam("limit", "10")
          .buildAndExpand()
          .toString();
      
      HttpHeaders headers = new HttpHeaders();
      HttpEntity<String> requestEntity = new HttpEntity<String>(headers); 
      
      ResponseEntity<List> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, List.class);
      
      List<User> users = new ObjectMapper().findAndRegisterModules().convertValue(responseEntity.getBody(), new TypeReference<List<User>>() {});
      
      apiReponseDto.setStatus(responseEntity.getStatusCodeValue());
      apiReponseDto.setData(users);
    } catch (HttpStatusCodeException e) {
      log.error("Error : {}", e);
      
      apiReponseDto.setStatus(e.getRawStatusCode());
      apiReponseDto.setData(e.getResponseBodyAsString());
    }
    
    return apiReponseDto;
  }

  @Override
  public ApiReponseDto createUser(UserDto userDto) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/users")
          .buildAndExpand()
          .toString();
      
      String requestBody = new ObjectMapper().writeValueAsString(userDto);
      
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      
      HttpEntity<String> requestEntity = new HttpEntity<String>(requestBody, headers);
      
      ResponseEntity<ErrorDto> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ErrorDto.class);
      
      apiReponseDto.setStatus(responseEntity.getStatusCodeValue());
      apiReponseDto.setData(responseEntity.getBody());
    } catch (JsonProcessingException e) {
      log.error("Error : {}", e);
      
      apiReponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
      apiReponseDto.setData(e.getMessage());
    } catch (HttpStatusCodeException e) {
      log.error("Error : {}", e);
      
      apiReponseDto.setStatus(e.getRawStatusCode());
      apiReponseDto.setData(e.getResponseBodyAsString());
    }

    return apiReponseDto;
  }

  @Override
  public ApiReponseDto getUser(Long id) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/users/{id}")
          .buildAndExpand(id)
          .toString();
      
      HttpHeaders headers = new HttpHeaders();
      HttpEntity<String> requestEntity = new HttpEntity<String>(headers); 
      
      ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, User.class);
      
      apiReponseDto.setStatus(responseEntity.getStatusCodeValue());
      apiReponseDto.setData(responseEntity.getBody());
    } catch (HttpStatusCodeException e) {
      log.error("Error : {}", e);
      
      apiReponseDto.setStatus(e.getRawStatusCode());
      apiReponseDto.setData(e.getResponseBodyAsString());
    }
    
    return apiReponseDto;
  }

  @Override
  public ApiReponseDto updateUser(Long id, UserDto userDto) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/users/{id}")
          .buildAndExpand(id)
          .toString();
      
      String requestBody = new ObjectMapper().writeValueAsString(userDto);
      
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      
      HttpEntity<String> requestEntity = new HttpEntity<String>(requestBody, headers);
      
      ResponseEntity<ErrorDto> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ErrorDto.class);
      
      apiReponseDto.setStatus(responseEntity.getStatusCodeValue());
      apiReponseDto.setData(responseEntity.getBody());
    } catch (JsonProcessingException e) {
      log.error("Error : {}", e);
      
      apiReponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
      apiReponseDto.setData(e.getMessage());
    } catch (HttpStatusCodeException e) {
      log.error("Error : {}", e);
      
      apiReponseDto.setStatus(e.getRawStatusCode());
      apiReponseDto.setData(e.getResponseBodyAsString());
    }

    return apiReponseDto;
  }

  @Override
  public ApiReponseDto deleteUser(Long id) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/users/{id}")
          .buildAndExpand(id)
          .toString();
      
      HttpHeaders headers = new HttpHeaders();
      HttpEntity<String> requestEntity = new HttpEntity<String>(headers); 
      
      ResponseEntity<ErrorDto> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, ErrorDto.class);
      
      apiReponseDto.setStatus(responseEntity.getStatusCodeValue());
      apiReponseDto.setData(responseEntity.getBody());
    } catch (HttpStatusCodeException e) {
      log.error("Error : {}", e);
      
      apiReponseDto.setStatus(e.getRawStatusCode());
      apiReponseDto.setData(e.getResponseBodyAsString());
    }
    
    return apiReponseDto;
  }

  @Override
  public ApiReponseDto updateUserLoginDate(Long id) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/users/{id}/login_date")
          .buildAndExpand(id)
          .toString();
      
      String requestBody = new ObjectMapper().writeValueAsString(null);
      
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      
      HttpEntity<String> requestEntity = new HttpEntity<String>(requestBody, headers);
      
      ResponseEntity<ErrorDto> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ErrorDto.class);
      
      apiReponseDto.setStatus(responseEntity.getStatusCodeValue());
      apiReponseDto.setData(responseEntity.getBody());
    } catch (JsonProcessingException e) {
      log.error("Error : {}", e);
      
      apiReponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
      apiReponseDto.setData(e.getMessage());
    } catch (HttpStatusCodeException e) {
      log.error("Error : {}", e);
      
      apiReponseDto.setStatus(e.getRawStatusCode());
      apiReponseDto.setData(e.getResponseBodyAsString());
    }

    return apiReponseDto;
  }

  @Override
  public ApiReponseDto getUserByEmail(String email) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/users/{email}/email")
          .buildAndExpand(email)
          .toString();
      
      HttpHeaders headers = new HttpHeaders();
      HttpEntity<String> requestEntity = new HttpEntity<String>(headers); 
      
      ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, User.class);
      
      apiReponseDto.setStatus(responseEntity.getStatusCodeValue());
      apiReponseDto.setData(responseEntity.getBody());
    } catch (HttpStatusCodeException e) {
      log.error("Error : {}", e);
      
      apiReponseDto.setStatus(e.getRawStatusCode());
      apiReponseDto.setData(e.getResponseBodyAsString());
    }
    
    return apiReponseDto;
  }

}
