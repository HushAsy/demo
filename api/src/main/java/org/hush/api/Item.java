package org.hush.api;

import java.io.Serializable;

/**
 * @description:
 * @author: hewater
 * @create: 2018-09-28 14:16
 **/
public class Item implements Serializable {

    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
