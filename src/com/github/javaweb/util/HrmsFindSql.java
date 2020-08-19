package com.github.javaweb.util;

import com.github.javaweb.jdbc.Mysql;
import com.github.javaweb.data.Ddept;
import com.github.javaweb.data.Demployee;
import com.github.javaweb.data.Djob;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * 用来将数据库的字段转为
 * 对象
 */
public class HrmsFindSql {

    /**
     * 只是用于初始化和更新
     * @param sql
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Map<Integer,Ddept> findDeptBySql(String sql) throws SQLException, ClassNotFoundException {
        Map<Integer,Ddept> map = new HashMap<>();
        ResultSet rs = getRs(sql);
        while (rs.next()){
            map.put(rs.getInt(1),new Ddept(rs.getInt(1),rs.getString(2)));
        }
        return map;
    }

    /**
     * 只是用于初始化和更新
     * @param sql
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Map<Integer, Djob> findJobBySql(String sql) throws SQLException, ClassNotFoundException {
        Map<Integer,Djob> map = new HashMap<>();
        ResultSet rs = getRs(sql);
        while (rs.next()){
            map.put(rs.getInt(1),new Djob(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),Ddept.getDept(rs.getInt(5))));
        }
        return map;
    }

    /**
     * 只用于 初始化 和更新
     * @param sql
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Map<Integer, Demployee> findEmpBySql(String sql) throws SQLException, ClassNotFoundException {
        Map<Integer, Demployee> map = new HashMap<>();
        ResultSet rs = getRs(sql);
        while (rs.next()){
            map.put(rs.getInt(1),new Demployee(
                    rs.getInt(1),
                    rs.getString(2),
                    Djob.getJob(rs.getInt(3)),
                    rs.getInt(4),
                    rs.getDouble(5),
                    rs.getString(6)
                    )
            );

        }
        return map;

    }


    public ResultSet getRs(String sql) throws SQLException, ClassNotFoundException {
        Connection conn = Mysql.getConn();
        Statement state = conn.createStatement();
        return state.executeQuery(sql);
    }

}
