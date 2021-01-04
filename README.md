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
## 5 distributed-limiter
限流组件：限流的目的是通过对并发访问/请求进行限速，或者对一个时间窗口内的请求进行限速来保护系统，一旦达到限制速率则可以拒绝服务、排队或等待、降级等处理
## 6 nlp-china-demo
中国自然语言处理，包含ansj_seg的分词代码示例
## 7 pdf-demo
绘制PDf demo，使用itextpdf框架【子项目单独导入，作为子项目找不到resource中的文件】
## 8 web-security
Web安全相关
