# heap-up
代码积累，代码示例等
## 1 common-tools
工具组件代码，包括验证码、邮件、加解密、http client、造数据工具、本地缓存、mqtt、redis、单位转换、utils
## 2 demo-example
框架应用示例代码
### 2.1 demo-lombok
Lombok 使用demo
### 2.2 demo-fastjson
alibaba fastjson 实例
### 2.3 demo-gson
google gson 示例
### 2.4 demo-jackson
jackson 示例
### 2.5 demo-netty
Netty 4 代码示例demo
### 2.6 example-itextpdf
绘制PDf示例，使用itextpdf框架【子项目单独导入，作为子项目找不到resource中的文件】
## 3 demo-springboot
### 3.1 springboot-aop
springboot aop 代码示例
### 3.2 springboot-autotest
自动测试
### 3.3 springboot-distributed-id
基于zk的分布式ID组件（Snowflake算法）<br>
全局唯一ID/分布式ID解决方案<br>
全局唯一，绝对不会出现重复的ID，且ID整体趋势递增。<br>
高可用，服务完全基于分布式架构，即使MySQL宕机，也能容忍一段时间的数据库不可用。<br>
高并发低延时，在CentOS 4C8G的虚拟机上，远程调用QPS可达5W+，TP99在1ms内。<br>
接入简单，直接通过RPC服务或者HTTP调用即可接入。<br>
### 3.4 springboot-exception
全局异常处理方式（自定义error请求返回结果）
### 3.5 springboot-fastdfs
fastdfs分布式文件系统客户端
### 3.6 springboot-interceptor
拦截器
### 3.7 springboot-schedule
定时任务


## 4 design-pattern
设计模式

### 4.1 creational-pattern
创建型设计模式
#### 4.1.1 singleton-pattern
单例设计模式
#### 4.1.2 factory-pattern
工厂设计模式
#### 4.1.3 abstract-factory-pattern
抽象工厂设计模式
#### 4.1.4 builder-pattern
建造者设计模式
#### 4.1.5 prototype-pattern
原型模式

### 4.2 structural-pattern
结构型设计模式
#### 4.2.1 proxy-pattern
代理设计模式
#### 4.2.2 adapter-pattern
适配器设计模式
#### 4.2.3 bridge-pattern
桥接设计模式
#### 4.2.4 decorator-pattern
装饰器设计模式
#### 4.2.5 flyweight-pattern
享元设计模式
#### 4.2.6 composite-pattern
组合设计模式


### 4.3 behavioral-pattern
行为型设计模式
#### 4.3.1 template-pattern
模板方法模式
#### 4.3.2 strategy-pattern
策略模式
#### 4.3.3 command-pattern
命令模式
#### 4.3.4 chain-of-responsibility-pattern
职责链模式
#### 4.3.5 state-pattern
状态模式
#### 4.3.6 observer-pattern
观察者模式
#### 4.3.7 mediator-pattern
中介者模式
#### 4.3.8 iterator-pattern
迭代器模式
#### 4.3.9 visitor-pattern
访问者模式
#### 4.3.10 memento-pattern
备忘录模式
#### 4.3.11 interpreter-pattern
解释器模式

## 5 distributed-limiter
限流组件：限流的目的是通过对并发访问/请求进行限速，或者对一个时间窗口内的请求进行限速来保护系统，一旦达到限制速率则可以拒绝服务、排队或等待、降级等处理
## 6 nlp-china-demo
中国自然语言处理，包含ansj_seg的分词代码示例
## 7 web-security
Web安全相关
