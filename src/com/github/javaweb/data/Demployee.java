package com.github.javaweb.data;

import com.github.javaweb.util.Condition;
import com.github.javaweb.util.HrmsFindSql;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

/**
 * 员工类
 *
 * 数据库名称是: employee
 *
 */
public class Demployee extends Data {

    public static final String sql = "employee";

    static {
        init();
    }


    private static Map<Integer,Demployee> map;

    public static Map<Integer, Demployee> getMap() {
        return map;
    }

    public static Map<Integer,Demployee> getMap(Condition cd){
        if(cd!=null || cd.isData()){
            try {
                Map<Integer,Demployee> map = new HrmsFindSql().findEmpBySql("select * from employee where 1=1 " + cd.getWhere());
                if(cd.getDeptId()!=null&&!cd.getDeptId().isEmpty()){
                    Iterator<Map.Entry<Integer, Demployee>> iterable = map.entrySet().iterator();
                    while (iterable.hasNext()){
                        Map.Entry<Integer,Demployee> data =  iterable.next();
                        if(data.getValue().getDjob().getDdept().getId()!=Integer.parseInt(cd.getDeptId())){
                            iterable.remove();
                        }
                    }
                }
                return map;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return getMap();
    }


    public static void init(){
        try {
            map = new HrmsFindSql().findEmpBySql("select * from employee");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private Djob djob;
    private double money;
    private String msg;
    private Integer adminId;

    /**
     *
     * @param id 工号
     * @param name 名字
     * @param money 额外涨薪
     * @param msg  备注
     */
    public Demployee(int id, String name, String jobId, String adminId, String money, String msg) {
        super(id,name);
        Integer a = null;
        double d = 0;
        if(!adminId.isEmpty()){
            a = Integer.parseInt(adminId);
        }
        if(!money.isEmpty()){
            d = Double.parseDouble(money);
        }
        this.djob = Djob.getJob(jobId);
        this.money = d;
        this.adminId = a;
        this.msg = msg;
    }

    public Demployee(int id, String name, Djob djob,Integer adminId, double money, String msg) {
        super(id, name);
        this.adminId = adminId;
        this.djob = djob;
        this.money = money;
        this.msg = msg;
    }

    public Djob getDjob() {
        return djob;
    }

    public double getMoney(){
        return money+djob.getMoney();
    }


    public int getAdminId() {
        return adminId==null?-1:adminId;
    }

    public Demployee getAdmin(){
        if(adminId!=-1){
            return map.get(adminId);
        }
        return null;
    }

    public String getMsg() {
        return msg;
    }

    public String getAddSql(){
        if(getName()==null||getName().isEmpty()||getDjob()==null){
            throw new NullPointerException("重要数据缺失 请查看以下什么为Null Name>" + getName() +" Job> " + getDjob());
        }
        StringBuffer sb = new StringBuffer();
        sb.append("insert into ").append(sql).append("(name,jobId,jobAdminId,extraMoney,note) value('").append(getName()).append("',").append(getDjob().getId()).append(",").append(getAdminId()==-1?null:getAdminId()).append(",").append(money).append(",'").append(getMsg()).append("');");
        return sb.toString();
    }

    public String getEditSql(String name, String jobId, String adminId, String money, String msg) {
        if(name ==null||name.isEmpty()||jobId==null){
            throw new NullPointerException("重要数据缺失 请查看以下什么为Null Name>" + getName() +" Job> " + getDjob());
        }
        StringBuffer sb = new StringBuffer("update ").append(sql).append(" Set ").append("name = '").append(name.isEmpty()?getName():name).append("',jobId = ").append(jobId.isEmpty()?getDjob().getId():jobId).append(",jobAdminId = ").append(adminId.isEmpty()?getAdminId()==0?null:getAdminId():adminId).append(",extraMoney = ").append(money.isEmpty()?getMoney():money).append(",note = '").append(msg).append("' where id =").append(getId()).append(";");
        return sb.toString();
    }
}
