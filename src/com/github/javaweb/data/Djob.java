package com.github.javaweb.data;

import com.github.javaweb.util.HrmsFindSql;

import java.sql.SQLException;
import java.util.Map;

/**
 * 职位类
 *
 * 数据库表为job
 */
public class Djob extends Data {


    static {
        try {
            init();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer,Djob> map;


    public static void init() throws SQLException, ClassNotFoundException {
        map = new HrmsFindSql().findJobBySql("select * from job");
    }

    public static Map<Integer, Djob> getMap() {
        return map;
    }

    public static Djob getJob(int i){
        return map.get(i);
    }
    public static Djob getJob(String i){
        if(i.isEmpty()){
           return null;
        }
        int a = Integer.parseInt(i);
        return  map.get(a);
    }

    private double money;
    private int width;
    private Ddept ddept;

    /**
     *
     * @param id 职位id
     * @param name 职位名称
     * @param money 基础工资
     * @param width 职位权重
     * @param ddept 所属部门
     */
    public Djob(int id, String name, double money, int width, Ddept ddept) {
        super(id, name);
        this.money = money;
        this.width = width;
        this.ddept = ddept;
    }

    public double getMoney() {
        return money;
    }

    public int getWidth() {
        return width;
    }

    public Ddept getDdept() {
        return ddept;
    }
}
