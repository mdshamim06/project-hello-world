package com.gmail.hidekishima.helloworld.encryption;

import java.security.MessageDigest;

public class MD5Digest {
  public String toString(byte[] digest) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < digest.length; i++) {
      int d = digest[i];
      if (d < 0) {
        d += 256;
      }
      if (d < 16) {
        sb.append("0");
      }
      sb.append(Integer.toString(d, 16));
    }
    return (sb.toString());
  }

  public byte[] getStringDigest(String algorithm, String data) throws Exception {
    MessageDigest md = MessageDigest.getInstance(algorithm);
    byte[] dat = data.getBytes();
    md.update(dat);
    return md.digest();
  }

  public String encrypt(String algorithm, String text) {
    try {
      return toString(getStringDigest(algorithm, text));
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  public static void main(String[] args) throws Exception {
    MD5Digest d = new MD5Digest();
    System.out.print("password = " + d.encrypt("MD5", "password"));
  }
}
