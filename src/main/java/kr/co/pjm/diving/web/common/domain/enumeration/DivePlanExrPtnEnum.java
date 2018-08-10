package kr.co.pjm.diving.web.common.domain.enumeration;

public enum DivePlanExrPtnEnum {
  SKIN(0, "스킨"), WETSUIT(1, "웻슈트"), DRYSUIT(2, "드라이슈트");

  private int code;
  private String description;

  private DivePlanExrPtnEnum(int code, String description) {
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
