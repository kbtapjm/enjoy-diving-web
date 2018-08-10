package kr.co.pjm.diving.web.common.domain.enumeration;

public enum DiveTypeEnum {
  BEACH(0, "비치"), BOAT(1, "보트");
  
  private int code;
  private String description;

  private DiveTypeEnum(int code, String description) {
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
