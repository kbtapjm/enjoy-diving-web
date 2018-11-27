package kr.co.pjm.diving.web.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EnjoyDivingTest {

  public static void main(String[] args) {
    StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
    pbeEnc.setAlgorithm("PBEWithMD5AndDES");
    pbeEnc.setPassword("test"); //2번 설정의 암호화 키를 입력
    
    //String enc = pbeEnc.encrypt("jdbc:mysql://localhost:3306/diving"); //암호화 할 내용
    String enc = pbeEnc.encrypt("dae737184554557de33db0cb4d8c1923"); //암호화 할 내용
    System.out.println("enc = " + enc); //암호화 한 내용을 출력
    
    //테스트용 복호화
    String des = pbeEnc.decrypt(enc);
    System.out.println("des = " + des);

  }

}
