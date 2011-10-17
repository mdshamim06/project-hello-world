package com.gmail.hidekishima.helloworld.format;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
  public static void main(String[] args) {
    new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
  }
}
