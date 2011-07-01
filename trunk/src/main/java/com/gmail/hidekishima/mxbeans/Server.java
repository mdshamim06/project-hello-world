package com.gmail.hidekishima.mxbeans;

import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

public class Server {

  public static String ADDRESS = "service:jmx:rmi:///jndi/rmi://localhost/hello";
  
  public static void main(String[] args) throws Exception {
    LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
    MBeanServer server = ManagementFactory.getPlatformMBeanServer(); 
    ObjectName name = new ObjectName("com.gmail.hidekishima.mxbean:type=HelloWorld");
    server.registerMBean(new HelloWorld_impl(), name);

    JMXServiceURL url = new JMXServiceURL(ADDRESS);
    JMXConnectorServer connector = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
    connector.start();
  }
  
}
