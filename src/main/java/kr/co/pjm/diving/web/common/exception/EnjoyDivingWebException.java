package kr.co.pjm.diving.web.common.exception;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.pjm.diving.common.domain.dto.ErrorDto;
import kr.co.pjm.diving.web.api.dto.ApiReponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnjoyDivingWebException extends Exception {

  private static final long serialVersionUID = 4617355733643158982L;
  final static String DEFAULT_ERROR_MESSAGE = "처리 중 오류가 발생하였습니다. 관리자에게 문의하세요.";

  private String errCd;
  private String errMsg;

  public EnjoyDivingWebException(String errMsg) {
    this.errMsg = errMsg;
  }

  public EnjoyDivingWebException(Throwable cause) {
    super(cause);
  }

  public EnjoyDivingWebException(ApiReponseDto apiReponseDto) {
    this.errCd = String.valueOf(apiReponseDto.getStatus());
    
    if (apiReponseDto.getData() != null) {
      ErrorDto errorDto = null;
      try {
        errorDto = new ObjectMapper().readValue((String)apiReponseDto.getData(), ErrorDto.class);
      } catch (JsonParseException e) {
        e.printStackTrace();
      } catch (JsonMappingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      this.errMsg = errorDto.getMessage();
    }
  }
  
  public EnjoyDivingWebException(String errCd, String errMsg) {
    this.errCd = errCd;
    this.errMsg = errMsg;
  }
  
  public EnjoyDivingWebException(Object data) {
    if (data != null) {
      ErrorDto errorDto = (ErrorDto) data;
      
      this.errCd = String.valueOf(errorDto.getStatus());
      this.errMsg = errorDto.getMessage();
    }
  }

  public EnjoyDivingWebException(String message, Throwable cause) {
    super(message, cause);
  }

  public static String getExceptionMsg(Exception e) {
    String errCd = StringUtils.EMPTY;
    String errMsg = StringUtils.EMPTY;
    
    if (e instanceof EnjoyDivingWebException) {
      EnjoyDivingWebException exception = (EnjoyDivingWebException) e;

      errCd = !StringUtils.isEmpty(exception.getErrCd()) ? " [".concat(exception.getErrCd()) + "]" : "";
      
      switch (exception.getErrCd()) {
        case "400":
          errMsg = "잘못된 요청입니다.";
          break;
        case "404":
          errMsg = "정보를 찾을 수 없습니다.";
          break;
        case "500":
          errMsg = DEFAULT_ERROR_MESSAGE;
          break;
        default:
          //errMsg = exception.getErrMsg();    
          errMsg = DEFAULT_ERROR_MESSAGE;    
      }
    } else {
      errCd = "[500]";
      errMsg = DEFAULT_ERROR_MESSAGE;
    }

    return errCd.concat(errMsg);
  }

}
