package com.gmail.hidekishima.helloworld.format;

public class StringFormat {

  public static void main(String[] args) {
    
    // "   5"
    System.out.println(String.format("%4d", 5));
    
    // "0005"
    System.out.println(String.format("%04d", 5));
    
    // "Hello     "
    System.out.println(String.format("%-10s", "Hello"));
    
    // "0005"
    System.out.println(String.format("%5s", "5"));
  }
  
}
