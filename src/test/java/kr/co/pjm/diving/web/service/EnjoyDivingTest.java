package kr.co.pjm.diving.web.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EnjoyDivingTest {

  public static void main(String[] args) {
    StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
    pbeEnc.setAlgorithm("PBEWithMD5AndDES");
    pbeEnc.setPassword("test");
    
    String enc = pbeEnc.encrypt("XFHtOW7PctfzfH9dbt6C6H-x");
    System.out.println("enc = " + enc);
    
    String des = pbeEnc.decrypt(enc);
    System.out.println("des = " + des);
  }

}
