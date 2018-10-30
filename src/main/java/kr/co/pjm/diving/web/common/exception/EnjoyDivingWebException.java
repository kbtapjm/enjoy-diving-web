package kr.co.pjm.diving.web.common.exception;

import org.apache.commons.lang3.StringUtils;

import kr.co.pjm.diving.common.domain.dto.ErrorDto;
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
      errMsg = exception.getErrMsg(); // TODO: http status에 따른 메세지 처리
    } else {
      errCd = "[500]";
      errMsg = DEFAULT_ERROR_MESSAGE;
    }

    return errCd.concat(errMsg);
  }

}
