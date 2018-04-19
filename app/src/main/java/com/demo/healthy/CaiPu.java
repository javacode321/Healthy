package com.demo.healthy;


import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;


/**
 * 菜谱 数据库类
 */
@Table(name = "caipu")
public class CaiPu  implements Serializable{


    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "url")
    private String url;
    @Column(name = "name")
    private String name;

    @Column(name = "jianjie")
    private String jianjie;
    @Column(name = "timefl")
    private String timeAndfl;
    @Column(name = "zl")
    private String zl;
    @Column(name = "fl")
    private String fl;
    @Column(name = "type")
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getTimeAndfl() {
        return timeAndfl;
    }

    public void setTimeAndfl(String timeAndfl) {
        this.timeAndfl = timeAndfl;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }
}
