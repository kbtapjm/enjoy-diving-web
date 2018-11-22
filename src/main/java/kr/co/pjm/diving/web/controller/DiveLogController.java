package kr.co.pjm.diving.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.co.pjm.diving.common.domain.dto.PagingDto;
import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.common.domain.dto.SearchDto;
import kr.co.pjm.diving.common.util.NumberUtil;
import kr.co.pjm.diving.web.common.enumeration.Result;
import kr.co.pjm.diving.web.common.exception.EnjoyDivingWebException;
import kr.co.pjm.diving.web.domain.dto.DiveLogDto;
import kr.co.pjm.diving.web.service.DiveLogService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping(value = DiveLogController.RESOURCE_PATH)
public class DiveLogController {
  
  static final String RESOURCE_PATH = "/log";
  static final String LOG_CREATE_PAGE_NAME = "create";
  
  private DiveLogService diveLogService;
  
  @GetMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String list(@ModelAttribute SearchDto searchDto, @ModelAttribute PagingDto pagingDto, Model model) throws Exception {
    ResourcesDto resourcesDto = diveLogService.getAll(searchDto, pagingDto);
    
    model.addAllAttributes(resourcesDto.getMap());
    
    return "content/log-list";
  }
  
  @GetMapping(value = "{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String create(@PathVariable("id") String id, Model model) throws Exception {
    ResourcesDto resourcesDto = null;
    if (StringUtils.equals(LOG_CREATE_PAGE_NAME, id)) {
      resourcesDto = new ResourcesDto(new DiveLogDto());
    } else {
      resourcesDto = diveLogService.getById(NumberUtil.getInstance().isLong(id));
    }
    
    model.addAllAttributes(resourcesDto.getMap());
    
    return "content/log-create";
  }
  
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Object> create(@RequestBody DiveLogDto diveLogDto) throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    try {
      diveLogService.set(diveLogDto);
      
      resultMap.put("resultCd", Result.SUCCESS.getCd());
    } catch (Exception e) {
      e.printStackTrace();
      
      resultMap.put("resultCd", Result.FAIL.getCd());
      resultMap.put("resultMsg", EnjoyDivingWebException.getExceptionMsg(e));
    }
    
    return resultMap;
  }
  
  @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Object> update(@PathVariable("id") String id, @RequestBody DiveLogDto diveLogDto) throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    try {
      diveLogService.update(NumberUtil.getInstance().isLong(id), diveLogDto);
      
      resultMap.put("resultCd", Result.SUCCESS.getCd());
    } catch (Exception e) {
      e.printStackTrace();
      
      resultMap.put("resultCd", Result.FAIL.getCd());
      resultMap.put("resultMsg", EnjoyDivingWebException.getExceptionMsg(e));
    }
    
    return resultMap;
  }
  
  @DeleteMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Object> delete(@PathVariable("id") String id) throws Exception {
    Map<String, Object> resultMap = new HashMap<String, Object>();
    
    try {
      diveLogService.delete(NumberUtil.getInstance().isLong(id));
      
      resultMap.put("resultCd", Result.SUCCESS.getCd());
    } catch (Exception e) {
      e.printStackTrace();
      
      resultMap.put("resultCd", Result.FAIL.getCd());
      resultMap.put("resultMsg", EnjoyDivingWebException.getExceptionMsg(e));
    }
    
    return resultMap;
  }

}
