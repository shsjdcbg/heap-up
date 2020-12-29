package pers.dyx.java;

/**
 * Description：总结：
 * （1）在try，cache里面return，finally会执行
 * 当try语句退出时肯定会执行finally语句。这确保了即使发了一个意想不到的异常也会执行finally语句块。但是finally的用处不仅是用来处理异常——它可以让程序员不会因为return、continue、或者break语句而忽略了清理代码。把清理代码放在finally语句块里是一个很好的做法，即便可能不会有异常发生也要这样做。
 * 注意，当try或者catch的代码在运行的时候，JVM退出了。那么finally语句块就不会执行。同样，如果线程在运行try或者catch的代码时被中断了或者被杀死了(killed)，那么finally语句可能也不会执行了，即使整个运用还会继续执行。
 * （2）为什么是2不是3
 * 如果try语句里有return，那么代码的行为如下：
 * 1.如果有返回值，就把返回值保存到局部变量中
 * 2.执行jsr指令跳到finally语句里执行
 * 3.执行完finally语句后，返回之前保存在局部变量表里的值
 * 根据上面的说明就可以轻易地解释为什么是2了。
 * 当执行到return ++x;时，jvm在执行完++x后会在局部变量表里另外分配一个空间来保存当前x的值。
 * 注意，现在还没把值返回给y，而是继续执行finally语句里的语句。等执行完后再把之前保存的值（是2不是x）返回给y。
 * 所以就有了y是2不是3的情况。
 * 其实这里还有一点要注意的是，如果你在finally里也用了return语句，比如return +xx。那么y会是3。
 * 因为规范规定了，当try和finally里都有return时，会忽略try的return，而使用finally的return。
 *
 * @author dyx
 * @date 2019/3/28 13:08
 */
public class TryReturnFinally {
    private int aaa() {
        int x = 1;
        try {
            return ++x;
        } catch (Exception e) {

        } finally {
            ++x;
        }
        return x;
    }

    public static void main(String[] args) {
        TryReturnFinally t = new TryReturnFinally();
        int y = t.aaa();
        System.out.println(y);
    }
}
