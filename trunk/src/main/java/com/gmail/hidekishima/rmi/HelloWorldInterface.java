package com.gmail.hidekishima.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloWorldInterface extends Remote {
  public String sayHelloWorld() throws RemoteException;
}
