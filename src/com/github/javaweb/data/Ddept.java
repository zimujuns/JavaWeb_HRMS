package com.github.javaweb.data;

import com.github.javaweb.util.HrmsFindSql;

import java.sql.SQLException;
import java.util.Map;

/**
 * 部门类
 *
 * 数据库表为 dept
 */
public class Ddept extends Data {

    static {
        try {
            init();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer,Ddept> map;



    public static void init() throws SQLException, ClassNotFoundException {
        map = new HrmsFindSql().findDeptBySql("select * from dept");
    }
    /**
     * @param id 部门id
     * @param name 部门名称
     */
    public Ddept(int id, String name) {
        super(id, name);
    }

    public static Ddept getDept(int i){
        return map.get(i);
    }

    public static Map<Integer, Ddept> getMap() {
        return map;
    }

}
