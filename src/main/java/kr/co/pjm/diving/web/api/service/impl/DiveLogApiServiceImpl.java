package kr.co.pjm.diving.web.api.service.impl;

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
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.pjm.diving.common.domain.dto.ErrorDto;
import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.common.domain.entity.DiveLog;
import kr.co.pjm.diving.web.api.dto.ApiReponseDto;
import kr.co.pjm.diving.web.api.service.DiveLogApiService;
import kr.co.pjm.diving.web.domain.dto.DiveLogDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiveLogApiServiceImpl implements DiveLogApiService {
  
  @Value("${props.apiBaseUrl}")
  private String apiBaseUrl;
  
  @Autowired
  private RestTemplate restTemplate;

  @Override
  public ApiReponseDto getDiveLogs(String sorts, String q) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/divelogs")
          .queryParam("sorts", sorts)
          .queryParam("q", q)
          .queryParam("offset", "0")
          .queryParam("limit", "10")
          .buildAndExpand()
          .toString();
      log.info("===> Request Url : {}", url);
      
      HttpHeaders headers = new HttpHeaders();
      HttpEntity<String> requestEntity = new HttpEntity<String>(headers); 
      
      ResponseEntity<ResourcesDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ResourcesDto.class);
      log.info("===> Response http status : {}", responseEntity.getStatusCodeValue());
      log.info("===> Response getBody : {}", responseEntity.getBody());
      
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
  public ApiReponseDto createDiveLog(DiveLogDto diveLogDto) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/divelogs")
          .buildAndExpand()
          .toString();
      
      String requestBody = new ObjectMapper().writeValueAsString(diveLogDto);
      
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      
      HttpEntity<String> requestEntity = new HttpEntity<String>(requestBody, headers);
      ResponseEntity<?> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
      
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
  public ApiReponseDto getDiveLog(Long id) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/divelogs/{id}")
          .buildAndExpand(id)
          .toString();
      
      HttpHeaders headers = new HttpHeaders();
      HttpEntity<String> requestEntity = new HttpEntity<String>(headers); 
      
      ResponseEntity<DiveLog> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, DiveLog.class);
      
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
  public ApiReponseDto updateDiveLog(Long id, DiveLogDto diveLogDto) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/divelogs/{id}")
          .buildAndExpand(id)
          .toString();
      
      String requestBody = new ObjectMapper().writeValueAsString(diveLogDto);
      
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
  public ApiReponseDto deleteDiveLog(Long id) {
    ApiReponseDto apiReponseDto = new ApiReponseDto();
    
    try {
      String url = UriComponentsBuilder.fromUriString(apiBaseUrl)
          .path("/v1/divelogs/{id}")
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

}
