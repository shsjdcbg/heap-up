package pers.dyx.thread.sychronizedStatic;

/**
 * @author: dyx
 * @date: 2018/11/20 17:53
 * @description:
 */
public class TestStaticSync {
    public static void main(String[] args) {
        Person person = new Person();
        Thread firstThread = new FirstThread(person);
        firstThread.setName("线程一");

        // 因为锁定的是类的class字节码，即便传入不同的对象，静态方法同步仍然不允许多个线程同时执行
        person = new Person();
        Thread secondThread = new SecondThread(person);
        secondThread.setName("线程二");

        //启动线程
        firstThread.start();
        secondThread.start();
    }

}

class Person {

    /**
     * synchronized static修饰方法相当于synchronized(Person.class),锁定的是整个Person字节码信息
     * 当执行该类某个对象其中一个同步方法的时候,必须等待释放完锁才能执行
     */
    public void eat(String name) {
        synchronized (Person.class) {
            for (int i = 0; i <= 10; i++) {
                // 模拟网络延时
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("【" + name + "】-->" + i);
            }
        }
    }

    /**
     * synchronized static修饰方法相当于synchronized(Person.class),锁定的是整个Person字节码信息
     * 当执行该类某个对象其中一个同步方法的时候,必须等待释放完锁才能执行
     */
    public void say(String name) {
        synchronized (Person.class) {
            for (int i = 0; i <= 10; i++) {
                // 模拟网络延时
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("【" + name + "】-->" + i);
            }
        }
    }
}

/**
 * 第一个线程
 */
class FirstThread extends Thread {
    private Person person;

    public FirstThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        // 访问静态的synchronized方法
        person.eat(Thread.currentThread().getName());
    }

}

/**
 * 第二个线程
 */
class SecondThread extends Thread {
    private Person person;

    public SecondThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        // 访问静态的synchronized方法
        person.say(Thread.currentThread().getName());
    }

}