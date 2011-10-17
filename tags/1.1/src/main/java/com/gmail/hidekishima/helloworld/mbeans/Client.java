package com.gmail.hidekishima.helloworld.mbeans;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class Client {
    public static void main(String[] args) throws Exception {
        JMXServiceURL url = new JMXServiceURL(Server.ADDRESS);
        JMXConnector connector = JMXConnectorFactory.connect(url);
        MBeanServerConnection conn = connector.getMBeanServerConnection(); // get a connection

        ObjectName obj = new ObjectName("com.gmail.hidekishima.mxbean:type=HelloWorld");
        ObjectInstance oi = conn.getObjectInstance(obj);
        System.out.println(oi.getClass().getCanonicalName());
        System.out.println(oi.getClassName());
        System.out.println(oi.getObjectName());
        
        System.out.println(conn.getMBeanInfo(obj));
        
        String retval = (String) conn.invoke(obj, "helloWorld", null, null); // call sayHello
        System.out.println(retval); // HelloWorld
    }

} 