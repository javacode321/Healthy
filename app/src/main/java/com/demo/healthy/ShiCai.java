package com.demo.healthy;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;


/**
 * 食材数据库表
 */
@Table(name = "shicai")
public class ShiCai {

    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "url")
    private String url;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private int type;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
