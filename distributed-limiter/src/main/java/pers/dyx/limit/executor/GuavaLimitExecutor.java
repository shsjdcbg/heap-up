package pers.dyx.limit.executor;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pers.dyx.limit.constant.ResultType;
import pers.dyx.limit.entity.LimitEntity;
import pers.dyx.limit.entity.LimitResult;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 令牌桶算法限流
 *
 * @author dyx
 * @date 2020/9/9 10:27
 */
@Component
public class GuavaLimitExecutor implements LimitExecutor {

    private final static Logger logger = LoggerFactory.getLogger(GuavaLimitExecutor.class);

    private Map<String, RateLimiter> rateLimiterMap = Maps.newConcurrentMap();

    @Override
    public LimitResult tryAccess(LimitEntity limitEntity) {
        RateLimiter rateLimiter = getRateLimiter(limitEntity);
        if (rateLimiter == null) {
            return null;
        }
        LimitResult limitResult = new LimitResult();
        limitResult.setUrl(limitEntity.getKey());
        boolean access = rateLimiter.tryAcquire(3, 5000, TimeUnit.MILLISECONDS);
        logger.debug("url:{} access:{}", limitResult.getUrl(), access);
        if (access) {
            limitResult.setResultType(ResultType.SUCCESS);
        } else {
            limitResult.setResultType(ResultType.FAIL);
        }
        return limitResult;
    }

    private RateLimiter getRateLimiter(LimitEntity limitEntity) {
        if (limitEntity == null) {
            return null;
        }
        String key = limitEntity.getKey();
        RateLimiter rateLimiter;
        synchronized (this) {
            rateLimiter = rateLimiterMap.get(key);
            double limit = Double.parseDouble(String.valueOf(limitEntity.getLimit()));
            double perSecond = limit / limitEntity.getSecond();
            if (rateLimiter == null) {
                rateLimiter = RateLimiter.create(perSecond);
                rateLimiter = rateLimiterMap.putIfAbsent(key, rateLimiter);
            } else {
                if (rateLimiter.getRate() != perSecond) {
                    rateLimiter = RateLimiter.create(perSecond);
                    rateLimiter = rateLimiterMap.putIfAbsent(key, rateLimiter);
                }
            }
        }
        return rateLimiter;
    }
}
