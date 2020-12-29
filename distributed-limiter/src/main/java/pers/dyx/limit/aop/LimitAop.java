package pers.dyx.limit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import pers.dyx.limit.annotation.Limit;
import pers.dyx.limit.constant.ResultType;
import pers.dyx.limit.entity.LimitEntity;
import pers.dyx.limit.exception.LimitException;
import pers.dyx.limit.executor.LimitExecutor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author dyx
 * @date 2020/9/9 9:26
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LimitAop {

    private final static Logger logger = LoggerFactory.getLogger(LimitAop.class);

    @Autowired
    private LimitExecutor limitExecutor;

    @Pointcut("@annotation(pers.dyx.limit.annotation.Limit)")
    private void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 方法注解
        Annotation[] annotations = method.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Limit) {
                Limit limit = (Limit) annotation;

                LimitEntity limitEntity = new LimitEntity();
                limitEntity.setKey(limit.key());
                limitEntity.setLimit(limit.limit());
                limitEntity.setSecond(limit.second());
                logger.debug("limit is {}", limitEntity);
                if (ResultType.FAIL == limitExecutor.tryAccess(limitEntity).getResultType()) {
                    throw new LimitException("you are not access!");
                }
            }
        }
    }
}
