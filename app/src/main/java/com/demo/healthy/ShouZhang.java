package com.demo.healthy;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;


/**
 * 手账数据库表
 *
 */
@Table(name = "shouzhang")
public class ShouZhang implements Serializable {
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "content")
    private String content;
    @Column(name = "url")
    private String url;
    @Column(name = "stime")
    private String stime;

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
