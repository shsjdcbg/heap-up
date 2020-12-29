package pers.dyx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: dyx
 * @date: 2018/8/27 17:06
 * @description: MySQL 事务
 */
public class TestTranscaction {

    //数据库连接地址
    public final static String URL = "jdbc:mysql://192.168.2.135:3306/apolloportaldb";
    //用户名
    public final static String USERNAME = "root";
    //密码
    public final static String PASSWORD = "123456";
    //驱动类
    public final static String DRIVER = "com.mysql.jdbc.Driver";


    public static void main(String[] args) {
        //insert(p);
        //update(p);
        //delete(3);
        insertAndQuery();
    }


    //方法：使用PreparedStatement插入数据、更新数据
    public static void insertAndQuery() {
        Connection conn = null;
        String sql1 = "INSERT INTO user(name,pwd)VALUES(?,?)";
        String sql2 = "UPDATE user SET pwd=? WHERE name=?";
        PreparedStatement ps = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql1);

            ps.setString(1, "smyhvae");
            ps.setString(2, "007");
            ps.executeUpdate();

            ps = conn.prepareStatement(sql2);
            ps.setString(1, "008");
            ps.setString(2, "smyh");
            ps.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            try {
                conn.rollback();
                System.out.println("回滚" + e.getMessage());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
