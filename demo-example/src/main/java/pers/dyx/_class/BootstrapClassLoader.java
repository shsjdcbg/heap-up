package pers.dyx._class;

/**
 * @author: dyx
 * @date: 2018/11/7 12:47
 * @description:
 */
public class BootstrapClassLoader {
    public static void main(String args[]) {
        System.out.println("Bootstrap:" + String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }
}
