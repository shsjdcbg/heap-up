package pers.dyx.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Java 异常
 *
 * @author dyx
 * @version 1.0
 * @date 2020/7/27 9:44
 */
public class ExceptionDemo {

    public static void main(String[] args) {
//        System.out.println(1 / 0);
        System.out.println(getInt());
        System.out.println(getInt1());

//        catchAndThrow();
    }


    private static void readFile(String filePath) throws IOException {
        File file = new File(filePath);
        String result;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((result = reader.readLine()) != null) {
            System.out.println(result);
        }
        reader.close();
    }

    private static void readFile1(String filePath) throws MyException {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            // code
        } catch (IOException e) {
            throw new MyException("read file failed.", e);
        }
    }

    private static void readFile3(String filePath) throws MyException {
        File file = new File(filePath);
        String result;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((result = reader.readLine()) != null) {
                System.out.println(result);
            }
        } catch (IOException e) {
            System.out.println("readFile method catch block.");
            throw new MyException("read file failed.", e);
        } finally {
            System.out.println("readFile method finally block.");
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getInt() {
        int a = 10;
        try {
            System.out.println(a / 0);
            a = 20;
        } catch (ArithmeticException e) {
            a = 30;
            return a;
        } finally {
            a = 40;
        }
        return a;
    }

    public static int getInt1() {
        int a = 10;
        try {
            System.out.println(a / 0);
            a = 20;
        } catch (ArithmeticException e) {
            a = 30;
            return a;
        } finally {
            a = 40;
            return a;
        }
    }

    public void catchMostSpecificExceptionFirst() {
        try {
            // do something
        } catch (IllegalArgumentException e) {
            // do something
        }
    }

    public static void catchAndThrow() {
        try {
            new Long("xyz");
        } catch (NumberFormatException e) {
            // 打印日志
            throw e;
        }
    }

    /**
     * @param input
     * @throws MyException
     */
    public void wrapException(String input) throws MyException {
        try {
            // do something
        } catch (NumberFormatException e) {
            throw new MyException("A message that describes the error.", e);
        }
    }

}
