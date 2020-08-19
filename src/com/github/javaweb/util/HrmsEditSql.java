package com.github.javaweb.util;

import com.github.javaweb.data.Demployee;
import com.github.javaweb.jdbc.Mysql;

import java.sql.SQLException;
import java.sql.Statement;

public class HrmsEditSql {

    public void edit(String sql) throws SQLException, ClassNotFoundException {
        Statement stat = Mysql.getConn().createStatement();
        stat.executeUpdate(sql);
        stat.close();
    }

    public void remove(String sql){

    }

    public void remove_id(String uid) throws SQLException, ClassNotFoundException {
        Statement stat = Mysql.getConn().createStatement();
        StringBuffer sb = new StringBuffer("delete from ");
        sb.append(Demployee.sql).append(" where id=").append(uid).append(";");
        stat.execute(sb.toString());
    }
}
