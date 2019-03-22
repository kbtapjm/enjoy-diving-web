package kr.co.pjm.diving.web.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EnjoyDivingTest {

  public static void main(String[] args) {
    StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
    pbeEnc.setAlgorithm("PBEWithMD5AndDES");
    pbeEnc.setPassword("Cloudzcp!23$");
    
    String enc = pbeEnc.encrypt("cloudzcp-admin");
    System.out.println("enc = " + enc);
    
    String des = pbeEnc.decrypt(enc);
    System.out.println("des = " + des);
  }

}
