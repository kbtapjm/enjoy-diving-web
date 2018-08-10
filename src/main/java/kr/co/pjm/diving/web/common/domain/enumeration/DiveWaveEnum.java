package kr.co.pjm.diving.web.common.domain.enumeration;

public enum DiveWaveEnum {
  NONE(0, "없음"), GENERALLY(1, "보통"), STRONG(2, "강함");

  private int code;
  private String description;

  private DiveWaveEnum(int code, String description) {
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
