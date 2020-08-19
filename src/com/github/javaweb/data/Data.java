package com.github.javaweb.data;

import java.io.Serializable;

/**
 *    可以指向
 * 员工类->职位->部门
 */
public abstract class Data  implements Serializable {

    private int id;
    private String name;

    public Data(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
