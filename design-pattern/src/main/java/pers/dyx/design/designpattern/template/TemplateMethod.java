package pers.dyx.design.designpattern.template;

/**
 * @author: dyx
 * @date: 2018/11/5 17:12
 * @description: 模板设计模式：
 * 定义一个操作中算法的骨架，而将一些步骤延迟到子类中，模板方法使得子类可以不改变算法的结构即可重定义该算法的某些特定步骤。
 * 通俗点的理解就是：完成一件事情，有固定的数个步骤，但是每个步骤根据对象的不同，而实现细节不同；
 * 就可以在父类中定义一个完成该事情的总方法，按照完成事件需要的步骤去调用其每个步骤的实现方法。每个步骤的具体实现，由子类完成。
 *
 * 例子中print方法类似于Thread的start方法，而wrapPrint则类似于run方法。
 * 这样做的好处是，程序结构由父类控制，并且是final修饰的，不允许重写，子类只需要事先想要的逻辑任务即可。
 */
public abstract class TemplateMethod {

    public final void print(String message) {
        System.out.println("#################");
        wrapPrint(message);
        System.out.println("#################");
    }

    protected void wrapPrint(String message) {

    }

    public static void main(String args[]) {
        TemplateMethod templateMethod = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*" + message + "*");
            }
        };
        templateMethod.print("Hello Thread");
        TemplateMethod templateMethod1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("+" + message + "+");
            }
        };

        templateMethod1.print("Hello Thread");
    }
}
