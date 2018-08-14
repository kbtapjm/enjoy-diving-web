package kr.co.pjm.diving.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.co.pjm.diving.common.domain.dto.ResourcesDto;
import kr.co.pjm.diving.common.util.NumberUtil;
import kr.co.pjm.diving.web.domain.dto.DiveLogDto;
import kr.co.pjm.diving.web.domain.entity.DiveLog;
import kr.co.pjm.diving.web.service.DiveLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = DiveLogController.RESOURCE_PATH)
public class DiveLogController {
  
  static final String RESOURCE_PATH = "/log";
  static final String LOG_CREATE_PAGE_NAME = "create";
  
  @Autowired
  private DiveLogService diveLogService;
  
  @GetMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String list(Model model) {
    ResourcesDto resourcesDto = diveLogService.getAll();
    
    model.addAllAttributes(resourcesDto.getMap());
    
    return "content/log-list";
  }
  
  @GetMapping(value = "{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.TEXT_HTML_VALUE)
  public String create(@PathVariable("id") String id, Model model) {
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
  public DiveLog create(@RequestBody DiveLog diveLog) {
    return diveLogService.set(diveLog);
  }
  
  @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  @ResponseStatus(HttpStatus.OK)
  public void update(@RequestBody DiveLog diveLog) {
    diveLogService.update(diveLog);
  }
  
  @DeleteMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody 
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") String id) {
    diveLogService.delete(NumberUtil.getInstance().isLong(id));
  }

}
