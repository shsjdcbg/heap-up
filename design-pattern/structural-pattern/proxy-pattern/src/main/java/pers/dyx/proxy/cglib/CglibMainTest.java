package pers.dyx.proxy.cglib;

/**
 * 测试方法
 *
 * @author hing
 *
 */
public class CglibMainTest {
	public static void main(String[] args) {
		// 生成 Cglib 代理类
		Engineer engineerProxy = (Engineer) CglibProxy.getProxy(new Engineer());

		// 调用相关方法
		engineerProxy.eat();

		engineerProxy.work();
	}
}
