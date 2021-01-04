package pers.dyx.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import pers.dyx.annotation.SystemControllerLog;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 系统日志切点类
 *
 * @author dyx
 * @date 2020/9/14 14:08
 */
@Aspect
@Component
public class SystemLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    private static final ThreadLocal<Date> beginTimeThreadLocal =
            new NamedThreadLocal<Date>("ThreadLocal beginTime");

    @Autowired(required = false)
    private HttpServletRequest request;

    @Pointcut("@annotation(pers.dyx.annotation.SystemControllerLog)")
    public void controllerAspect() {
    }

    @Before("controllerAspect()")
    public void before(JoinPoint joinPoint) {
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());


        Date beginTime = new Date();
        beginTimeThreadLocal.set(beginTime);
        if (logger.isDebugEnabled()) {
            logger.debug("开始计时：{} URI：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(beginTime),
                    request.getRequestURI());
        }
    }

    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        // 参数
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        // 注解中的信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SystemControllerLog controllerLog = signature.getMethod().getAnnotation(SystemControllerLog.class);
        String description = controllerLog.description();
        System.out.println(description);
        // 请求中的信息
        // IP
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        // URI
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        // 方法类型（post/get）
        String method = request.getMethod();
        System.out.println(method);
        // 请求提交的参数
        Map<String, String[]> params = request.getParameterMap();
        System.out.println(params);
        // 得到线程绑定的局部变量（开始时间）
        long beginTime = beginTimeThreadLocal.get().getTime();
        // 结束时间
        long endTime = System.currentTimeMillis();

        if (logger.isInfoEnabled()) {
            logger.info("计时结束：{}  URI: {}  耗时： {}   最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endTime),
                    requestURI,
                    endTime - beginTime,
                    Runtime.getRuntime().maxMemory() / 1024 / 1024,
                    Runtime.getRuntime().totalMemory() / 1024 / 1024,
                    Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        }
    }
}
