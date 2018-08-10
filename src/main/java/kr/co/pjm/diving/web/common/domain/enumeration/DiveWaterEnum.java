package kr.co.pjm.diving.web.common.domain.enumeration;

public enum DiveWaterEnum {
  SEA_WATER(0, "바다"), FRESH_WATER(1, "민물");
  
  private int code;
  private String description;

  private DiveWaterEnum(int code, String description) {
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
