package com.gaoqing.gaoqingblog.pojo;

import java.util.ArrayList;
import java.util.List;

public class TypePassing {
    //分类id
    private int id;

    //分类的总量
    private int Collection;

    private String name;

    public int getCollection() {
        return Collection;
    }

    public void setCollection(int collection) {
        Collection = collection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TypePassing{" +
                "Collection=" + Collection +
                ", name='" + name+
        ",id ='" + id
                + '\'' +
                '}';
    }
}
