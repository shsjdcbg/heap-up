# 经验之谈
一般情况下，不建议使用第 1 种和第 2 种懒汉方式，建议使用第 3 种饿汉方式。<br>
只有在要明确实现 lazy loading 效果时，才会使用第 5 种登记方式。<br>
如果涉及到反序列化创建对象时，可以尝试使用第 6 种枚举方式。<br>
如果有其他特殊的需求，可以考虑使用第 4 种双检锁方式。<br>
# 定义
单例模式（Singleton Pattern）是 Java 中最简单的设计模式之一。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
Ensure a class has only one instance, and provide a global point of access to it.（确保某一个类
只有一个实例， 而且自行实例化并向整个系统提供这个实例。 ）
这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。
# 注意
1、单例类只能有一个实例。<br>
2、单例类必须自己创建自己的唯一实例。<br>
3、单例类必须给所有其他对象提供这一实例。<br>
# 意图
保证一个类仅有一个实例，并提供一个访问它的全局访问点。
# 主要解决
一个全局使用的类频繁地创建与销毁。
# 何时使用
当您想控制实例数目，节省系统资源的时候。
# 如何解决
判断系统是否已经有这个单例，如果有则返回，如果没有则创建。
# 关键代码
构造函数是私有的。
# 应用实例
1、一个班级只有一个班主任。<br>
2、Windows 是多进程多线程的，在操作一个文件的时候，就不可避免地出现多个进程或线程同时操作一个文件的现象，所以所有文件的处理必须通过唯一的实例来进行。<br>
3、一些设备管理器常常设计为单例模式，比如一个电脑有两台打印机，在输出的时候就要处理不能两台打印机打印同一个文件。<br>
# 优点
1、在内存里只有一个实例，减少了内存的开销，尤其是频繁的创建和销毁实例（比如管理学院首页页面缓存），而且创建和销毁时性能又无法优化，单例模式的优势就非常明显。<br>
2、由于单例模式只生成一个实例， 所以减少了系统的性能开销， 当一个对象的产生需要比较多的资源时， 如读取配置、 产生其他依赖对象时，则可以通过在应用启动时直接产生一个单例对象， 然后用永久驻留内存的方式来解决（在Java EE中采用单例模式时需要注意JVM垃圾回收机制）。<br>
3、单例模式可以避免对资源的多重占用，例如一个写文件动作，由于只有一个实例存在内存中，避免对同一个资源文件的同时写操作。<br>
4、单例模式可以在系统设置全局的访问点，优化和共享资源访问，例如可以设计一个单例类，负责所有数据表的映射处理。<br>
# 缺点
1、单例模式一般没有接口，扩展很困难，若要扩展，除了修改代码基本上没有第二种途径可以实现。单例模式为什么不能增加接口呢？因为接口对单例模式是没有任何意义的，它要求“自行实例化”，并且提供单一实例、接口或抽象类是不可能被实例化的。当然，在特殊情况下，单例模式可以实现接口、被继承等，需要在系统开发中根据环境判断。<br>
2、单例模式对测试是不利的。在并行开发环境中，如果单例模式没有完成，是不能进行测试的，没有接口也不能使用mock的方式虚拟一个对象。<br>
3、单例模式与单一职责原则有冲突。一个类应该只实现一个逻辑，而不关心它是否是单例的，是不是要单例取决于环境，单例模式把“要单例”和业务逻辑融合在一个类中。<br>
# 使用场景
在一个系统中， 要求一个类有且仅有一个对象， 如果出现多个对象就会出现“不良反应”， 可以采用单例模式， 具体的场景如下：<br>
1、要求生产唯一序列号。<br>
2、WEB 中的计数器，不用每次刷新都在数据库里加一次，用单例先缓存起来。<br>
3、创建的一个对象需要消耗的资源过多，比如 I/O 与数据库的连接等。<br>
4、需要定义大量的静态常量和静态方法（如工具类） 的环境， 可以采用单例模式（当然， 也可以直接声明为static的方式）。
# 注意事项
首先，在高并发情况下， 请注意单例模式的线程同步问题。getInstance() 方法中需要使用同步锁 synchronized (Singleton.class) 防止多线程同时进入造成 instance 被多次实例化。<br>
其次，需要考虑对象的复制情况。在Java中，对象默认是不可以被复制的，若实现了Cloneable接口，并实现了clone方法，则可以直接通过对象复制方式创建一个新对象，对象复制是不用调用类的构造函数，因此即使是私有的构造函数，对象仍然可以被复制。在一般情况下， 类复制的情况不需要考虑， 很少会出现一个单例类会主动要求被复制的情况， 解决
该问题的最好方法就是单例类不要实现Cloneable接口。
# 实现方式
## 1 懒汉式，线程不安全
### 是否 Lazy 初始化
是
### 是否多线程安全
否
### 实现难度
易
### 描述
这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作。
```java
public class Singleton {
	private static Singleton instance;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
```
## 2 懒汉式，线程安全
### 是否 Lazy 初始化
是
### 是否多线程安全
是
### 实现难度
易
### 描述
这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
### 优点
第一次调用才初始化，避免内存浪费。
### 缺点
必须加锁 synchronized 才能保证单例，但加锁会影响效率。
getInstance() 的性能对应用程序不是很关键（该方法使用不太频繁）。
```java
public class Singleton {
	private static Singleton instance;

	private Singleton() {
	}

	public static synchronized Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
```
## 3 饿汉式
### 是否 Lazy 初始化
否
### 是否多线程安全
是
### 实现难度
易
### 描述
这种方式比较常用，但容易产生垃圾对象。
### 优点
没有加锁，执行效率会提高。
### 缺点
类加载时就初始化，浪费内存。
它基于 classloader 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，虽然导致类装载的原因有很多种，在单例模式中大多数都是调用 getInstance 方法， 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化 instance 显然没有达到 lazy loading 的效果。
```java
public class Singleton {
	private static Singleton instance = new Singleton();

	private Singleton() {
	}

	public static Singleton getInstance() {
		return instance;
	}
}
```
## 4 双检锁/双重校验锁（DCL，即 double-checked locking）
### 是否 Lazy 初始化
是
### 是否多线程安全
是
### 实现难度
较复杂
### 描述
JDK 版本：JDK1.5 起。<br>这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
getInstance() 的性能对应用程序很关键。
```java
public class Singleton {
	private volatile static Singleton singleton;

	private Singleton() {
	}

	public static Singleton getSingleton() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}
```
## 5 登记式/静态内部类
### 是否 Lazy 初始化
是
### 是否多线程安全
是
### 实现难度
一般
### 描述
这种方式能达到双检锁方式一样的功效，但实现更简单。对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
这种方式同样利用了 classloader 机制来保证初始化 instance 时只有一个线程，它跟第 3 种方式不同的是：第 3 种方式只要 Singleton 类被装载了，那么 instance 就会被实例化（没有达到 lazy loading 效果），而这种方式是 Singleton 类被装载了，instance 不一定被初始化。因为 SingletonHolder 类没有被主动使用，只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，从而实例化 instance。想象一下，如果实例化 instance 很消耗资源，所以想让它延迟加载，另外一方面，又不希望在 Singleton 类加载时就实例化，因为不能确保 Singleton 类还可能在其他的地方被主动使用从而被加载，那么这个时候实例化 instance 显然是不合适的。这个时候，这种方式相比第 3 种方式就显得很合理。
```java
public class Singleton {
	private static class SingletonHolder {
		private static final Singleton INSTANCE = new Singleton();
	}

	private Singleton() {
	}

	public static final Singleton getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
```
## 6 枚举
### 是否 Lazy 初始化
否
### 是否多线程安全
是
### 实现难度
易
### 描述
JDK 版本：JDK1.5 起。<br>
这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。不过，由于 JDK1.5 之后才加入 enum 特性，用这种方式写不免让人感觉生疏，在实际工作中，也很少用。
不能通过 reflection attack 来调用私有构造方法。
```java
public enum Singleton {
	INSTANCE;
	public void whateverMethod() {
		System.out.println("whateverMethod...");
	}
}
```
