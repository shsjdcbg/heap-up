package pers.dyx.thread;

/**
 * @author: dyx
 * @date: 2018/11/6 14:21
 * @description:
 */
public class ThreadGroupCreator {
    public static void main(String args[]) {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup);
        ThreadGroup threadGroup1 = new ThreadGroup("group1");
        System.out.println(threadGroup1.getParent());
        System.out.println(threadGroup1.getParent() == threadGroup);
        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup1,"group2");
        System.out.println(threadGroup2.getParent() == threadGroup1);
    }
}
