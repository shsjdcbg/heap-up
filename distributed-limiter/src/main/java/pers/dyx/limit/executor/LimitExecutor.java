package pers.dyx.limit.executor;

import pers.dyx.limit.entity.LimitEntity;
import pers.dyx.limit.entity.LimitResult;

/**
 * 限流执行器接口
 *
 * @author dyx
 * @date 2020/9/9 10:20
 */
public interface LimitExecutor {

    /**
     * 尝试访问
     *
     * @param limitEntity 限流实体类
     * @return 访问结果
     */
    LimitResult tryAccess(LimitEntity limitEntity);
}
