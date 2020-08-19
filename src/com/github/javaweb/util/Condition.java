package com.github.javaweb.util;

public class Condition {
    private String id;
    private String name;
    private String job;
    private String adminId;
    private String deptId;

    public Condition(String id, String name, String job,String adminId,String deptId) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.adminId = adminId;
        this.deptId = deptId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getDeptId() {
        return deptId;
    }

    public String getWhere() {
        String where = " && ";
        StringBuffer sb = new StringBuffer();
        if(getId()!=null&&!getId().isEmpty()){
            sb.append(where);
            sb.append("id = ");
            sb.append(getId());
        }
        if(getName()!=null&&!getName().isEmpty()){
            sb.append(where);
            sb.append("name = '");
            sb.append(getName()+"'");
        }
        if(getJob()!=null && !getJob().isEmpty()){
            sb.append(where);
            sb.append("jobId = ");
            sb.append(getJob());
        }
        if(getAdminId()!=null && !getAdminId().isEmpty()){
            sb.append(where).append("jobAdminId = ").append(getAdminId());
        }
        sb.append(";");
        return sb.toString();
    }

    public boolean isData() {
        if(getId()!=null||getJob()!=null||getName()!=null||!getId().isEmpty()||!getJob().isEmpty()||!getName().isEmpty()){
            return true;
        }
        return false;
    }
}
