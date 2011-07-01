package com.gmail.hidekishima.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIManager {

  private final static int RMI_PORT = Registry.REGISTRY_PORT;
  
  private static Remote createObject() throws RemoteException {
    HelloWorldInterface obj = new HelloWorld_impl();
    return UnicastRemoteObject.exportObject(obj, 0);
  }
  
  private static void regist(Remote r)throws RemoteException {
    Registry registry = LocateRegistry.createRegistry(RMI_PORT);
    registry.rebind("MyObj", r);   
  }
  
  public static void main(String args[]) throws Exception {
    Remote r = createObject();
    regist(r);
  }
  
}
