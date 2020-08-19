package com.github.javaweb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {
    static {
        try {
            conn = init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("初始化失败,类加载器报错,可能缺失数据库依赖");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection conn;

    public static Connection getConn() throws SQLException, ClassNotFoundException {
        if(conn.isClosed()){
            conn = init();
        }
        return conn;
    }

    private static Connection init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysql?serverTimezone=UTC&characterEncoding=GBK","root","");
    }


}
