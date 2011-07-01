package com.gmail.hidekishima.rmi;

import java.rmi.Naming;
import java.rmi.Remote;

public class Client {

  public static void main(String[] args) throws Exception {
    HelloWorldInterface a = lookup();
    System.out.println( a.sayHelloWorld() );
  }
  
  private static HelloWorldInterface lookup() throws Exception {
    Remote r = Naming.lookup("MyObj");
    return (HelloWorldInterface)r;
  }
  
}
