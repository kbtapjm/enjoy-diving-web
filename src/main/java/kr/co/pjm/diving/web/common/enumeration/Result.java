package kr.co.pjm.diving.web.common.enumeration;

public enum Result {
    SUCCESS("0000", "SUCCESS"), FAIL("9999", "FAIL");
    
    private Result(String cd, String msg) {
        this.cd = cd;
        this.msg = msg;
    }

    private String cd;
    private String msg;

    public String getCd() {
        return cd;
    }

    public String getMsg() {
        return msg;
    }
}
