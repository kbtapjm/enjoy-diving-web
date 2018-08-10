package kr.co.pjm.diving.web.common.domain.enumeration;

public enum DivePlanToolEnum {
  COUMPUTER(0, "컴퓨터"), TABLE(1, "테이블"), ETC(2, "기타");

  private int code;
  private String description;

  private DivePlanToolEnum(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }
}
