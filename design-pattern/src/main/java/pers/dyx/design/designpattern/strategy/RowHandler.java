package pers.dyx.design.designpattern.strategy;

import java.sql.ResultSet;

/**
 * @author: dyx
 * @date: 2018/11/5 17:26
 * @description: RowHandler接口只负责对从数据库中查询出来的结果集进行操作
 * 至于最终成什么样的数据结构，那就需要自己去实现，类似于Runnable接口
 */
public interface RowHandler<T> {
    T handle(ResultSet resultSet);
}
