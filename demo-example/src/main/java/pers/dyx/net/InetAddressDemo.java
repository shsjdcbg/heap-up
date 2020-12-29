package pers.dyx.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: dyx
 * @date: 2018/8/23 09:20
 * @description: java ip地址相关操作，在JAVA中，InetAddress类用于操作与IP地址相关的内容
 */
public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            // Get the first argument as the hostname.
            if (args.length > 0) {
                InetAddress[] inetAddresses = InetAddress.getAllByName(args[0]);
                for (InetAddress ia : inetAddresses) {
                    System.out.println(ia);
                }
            } else {
                //if No argument, get the localhost ip address.
                InetAddress localHostAddress = InetAddress.getLocalHost();
                System.out.println(localHostAddress);
                InetAddress address1 = InetAddress.getByName("www.baidu.com");
                System.out.println(address1);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
