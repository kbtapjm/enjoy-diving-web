package kr.co.pjm.diving.web.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.pjm.diving.common.domain.dto.ErrorDto;
import kr.co.pjm.diving.common.domain.entity.DiveLog;
import kr.co.pjm.diving.web.api.dto.ApiReponseDto;
import kr.co.pjm.diving.web.api.service.UserApiService;
import kr.co.pjm.diving.web.domain.dto.UserDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserApiServiceImpl implements UserApiService {
  
private String apiBaseUrl = "http://api.enjoydiving.io:8081";
  
  @Autowired
  private RestTemplate restTemplate;

  @Override
  public ApiReponseDto createUser(UserDto userDto) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/users")
          .buildAndExpand()
          .toString();
      log.info("===> Request Url : {}", url);
      
      String requestBody = new ObjectMapper().writeValueAsString(userDto);
      log.info("===> Request body : {}", requestBody);
      
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      
      HttpEntity<String> requestEntity = new HttpEntity<String>(requestBody, headers);
      
      ResponseEntity<ErrorDto> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ErrorDto.class);
      log.info("===> Response http status : {}", responseEntity.getStatusCodeValue());
      log.info("===> Response getBody : {}", responseEntity.getBody());
      
      apiReponseDto.setStatus(responseEntity.getStatusCodeValue());
      apiReponseDto.setData(responseEntity.getBody());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      log.error("Error : {}", e.getMessage());
    } catch (RestClientException e) {
      e.printStackTrace();
      log.error("Error : {}", e.getMessage());
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
      log.info("===> Request Url : {}", url);
      
      HttpHeaders headers = new HttpHeaders();
      HttpEntity<String> requestEntity = new HttpEntity<String>(headers); 
      
      ResponseEntity<DiveLog> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, DiveLog.class);
      log.info("===> Response http status : {}", responseEntity.getStatusCodeValue());
      log.info("===> Response getBody : {}", responseEntity.getBody());
      
      apiReponseDto.setStatus(responseEntity.getStatusCodeValue());
      apiReponseDto.setData(responseEntity.getBody());
    } catch (RestClientException e) {
      e.printStackTrace();
      log.error("Error : {}", e.getMessage());
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
      log.info("===> Request Url : {}", url);
      
      String requestBody = new ObjectMapper().writeValueAsString(userDto);
      log.info("===> Request body : {}", requestBody);
      
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      
      HttpEntity<String> requestEntity = new HttpEntity<String>(requestBody, headers);
      
      ResponseEntity<ErrorDto> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ErrorDto.class);
      log.info("===> Response http status : {}", responseEntity.getStatusCodeValue());
      log.info("===> Response getBody : {}", responseEntity.getBody());
      
      apiReponseDto.setStatus(responseEntity.getStatusCodeValue());
      apiReponseDto.setData(responseEntity.getBody());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      log.error("Error : {}", e.getMessage());
    } catch (RestClientException e) {
      e.printStackTrace();
      log.error("Error : {}", e.getMessage());
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
      log.info("===> Request Url : {}", url);
      
      HttpHeaders headers = new HttpHeaders();
      HttpEntity<String> requestEntity = new HttpEntity<String>(headers); 
      
      ResponseEntity<ErrorDto> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, ErrorDto.class);
      log.info("===> Response http status : {}", responseEntity.getStatusCodeValue());
      log.info("===> Response getBody : {}", responseEntity.getBody());
      
      apiReponseDto.setStatus(responseEntity.getStatusCodeValue());
      apiReponseDto.setData(responseEntity.getBody());
    } catch (RestClientException e) {
      e.printStackTrace();
      log.error("Error : {}", e.getMessage());
    }
    
    return apiReponseDto;
  }

}
