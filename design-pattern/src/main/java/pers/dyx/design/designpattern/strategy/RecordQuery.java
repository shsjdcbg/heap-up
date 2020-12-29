package pers.dyx.design.designpattern.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: dyx
 * @date: 2018/11/5 17:28
 * @description: query只负责将数据查询出来，然后调用RowHandler进行数据封装
 * 至于将封装成什么数据结构，那就得看怎么处理了。
 * 这样做的好处是可以用query方法应对任何数据库的查询，返回结果的不同只会因为你传入RowHandler
 * 的不同而不同，同样RecordQuery只负责数据的获取，而RowHandler则负责数据的加工，职责分明，每个类均功能单一。
 */
public class RecordQuery {
    private final Connection connection;

    public RecordQuery(Connection connection) {
        this.connection = connection;
    }

    public <T> T query(RowHandler<T> handler, String sql, Object... params) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            for(Object param:params){
                statement.setObject(index++,param);
            }
            ResultSet resultSet = statement.executeQuery();
            return handler.handle(resultSet);
        }
    }
}
