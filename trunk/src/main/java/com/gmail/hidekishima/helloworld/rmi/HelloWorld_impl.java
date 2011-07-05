package com.gmail.hidekishima.helloworld.rmi;

import java.rmi.RemoteException;

public class HelloWorld_impl implements HelloWorldInterface {  
  public String sayHelloWorld() throws RemoteException {
    return "Hello World";
  }
}
