package com.demo.healthy;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 步骤表
 */
@Table(name = "step")
public class Step {

    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "url")    //url地址
    private String url;
    @Column(name = "content")//内容
    private String content;
    @Column(name = "fid")    //id
    private int fid;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
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


}
