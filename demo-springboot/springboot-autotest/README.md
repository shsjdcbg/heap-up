@SpringBootTest注解底层实际上还是使用了Junit框架
 
Junit基本注解介绍

@BeforeClass 在所有测试方法前执行一次，一般在其中写上整体初始化的代码

@AfterClass 在所有测试方法后执行一次，一般在其中写上销毁和释放资源的代码

@Before 在每个测试方法前执行，一般用来初始化方法

@After 在每个测试方法后执行，在方法执行完成后要做的事情

@Test(timeout = 1000) 测试方法执行超过1000毫秒后算超时，测试将失败

@Test(expected = Exception.class) 测试方法期望得到的异常类，如果方法执行没有抛出指定的异常，则测试失败

@Test 编写一般测试用例
